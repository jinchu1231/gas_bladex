package org.springblade.modules.core.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Workbook;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.GasDeviceRecordDto;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.service.GasDeviceRecordService;
import org.springblade.modules.core.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 特种设备安全检查记录Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping(AppConstant.DEV_CODE + "/gas-device")
@Api(value = "特种设备安全检查记录", tags = "特种设备安全检查记录接口")
public class GasDeviceRecordController {

    @Autowired
    private GasDeviceRecordService gasDeviceRecordService;

	/**
	 * 查询特种设备安全检查记录详情
	 */
	@GetMapping("/detail")
	public R selectById(@RequestParam("id") String id) {
		GasDeviceRecordDto gasDeviceRecord = gasDeviceRecordService.selectGasDeviceRecordById(Long.valueOf(id));
		return R.data(gasDeviceRecord);
	}

	/**
     * 查询特种设备安全检查记录列表
     */
    @PostMapping("/list")
    public R<IPage<GasDeviceRecord>> list(@RequestBody GasDeviceRecord gasDeviceRecord,@RequestBody Query query) {
		IPage<GasDeviceRecord> gasDeviceRecordIPage =
			gasDeviceRecordService.selectGasDeviceRecordList(Condition.getPage(query), gasDeviceRecord);
		return R.data(gasDeviceRecordIPage);
    }


    /**
     * 新增保存特种设备安全检查记录
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasDeviceRecordDto gasDeviceRecordDto) {
        return R.data(gasDeviceRecordService.insertGasDevice(gasDeviceRecordDto));
    }


    /**
     * 修改保存特种设备安全检查记录
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasDeviceRecordDto gasDeviceRecordDto)
    {
        return R.data(gasDeviceRecordService.updateGasDevice(gasDeviceRecordDto));
    }

    /**
     * 删除特种设备安全检查记录
     */
    @GetMapping( "/delete")
    public R delete(@RequestParam("ids") String ids) {
        return R.data(gasDeviceRecordService.deleteGasDeviceRecordById(ids));
    }

	/**
	 * 下载Excel模板
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate")
//	@ApiOperation(value = "下载模板", httpMethod = "GET")
//	@ApiOperation(value = "导出用户阶段详情", notes = "export", produces = "application/octet-stream")
	public void downloadTemplate(HttpServletResponse response) {
		ExcelUtil.download(response, "特种设备安全检查.xlsx");
	}

	/**
	 * 导入特种设备安全检查记录列表
	 */
	@PostMapping("write-notice")
	public R writeNotice(MultipartFile file) {
		gasDeviceRecordService.writeNotice(file);
		return R.success("成功");
	}

	/**
	 * 导出特种设备安全检查记录列表
	 */
	@SneakyThrows
	@GetMapping("/export")
	public ResponseEntity<byte[]> export(HttpServletResponse response) {
		List<GasDeviceRecordDto> list = gasDeviceRecordService.selectGasDeviceRecordAllList();
		// 创建导出参数
		ExportParams exportParams = new ExportParams("特种设备安全检查记录列表", "记录列表");
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, GasDeviceRecordDto.class, list);

		// 将Workbook转换为字节数组
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		byte[] content = outputStream.toByteArray();

		// 设置响应头，通知浏览器以附件形式下载文件
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "output.xlsx");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		// 返回响应实体
		return new ResponseEntity<>(content, headers, HttpStatus.OK);
	}
}
