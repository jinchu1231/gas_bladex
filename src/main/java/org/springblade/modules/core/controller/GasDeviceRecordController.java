package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.cache.DictCache;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.service.GasDeviceRecordService;
import org.springblade.modules.core.util.ExcelUtil;
import org.springblade.modules.desk.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 特种设备安全检查记录Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/gas-device")
@Api(value = "特种设备安全检查记录", tags = "特种设备安全检查记录接口")
public class GasDeviceRecordController {

    @Autowired
    private GasDeviceRecordService gasDeviceRecordService;

    /**
     * 查询特种设备安全检查记录列表
     */
    @PostMapping("/list")
    public R<IPage<GasDeviceRecord>> list(@RequestBody @Valid GasDeviceRecord gasDeviceRecord,
						@Valid @RequestBody Query query) {
		IPage<GasDeviceRecord> gasDeviceRecordIPage =
			gasDeviceRecordService.selectGasDeviceRecordList(Condition.getPage(query), gasDeviceRecord);
		return R.data(gasDeviceRecordIPage);
    }

	/**
	 * 导出特种设备安全检查记录列表
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) {
		List<GasDeviceRecord> list = gasDeviceRecordService.selectGasDeviceRecordAllList();
		ExcelUtil.export(response, "特种设备安全检查记录", "特种设备安全检查记录表", list, GasDeviceRecord.class);
	}


    /**
     * 新增保存特种设备安全检查记录
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasDeviceRecord gasDeviceRecord)
    {
        return R.data(gasDeviceRecordService.insertGasDeviceRecord(gasDeviceRecord));
    }


    /**
     * 修改保存特种设备安全检查记录
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasDeviceRecord gasDeviceRecord)
    {
        return R.data(gasDeviceRecordService.updateGasDeviceRecord(gasDeviceRecord));
    }

    /**
     * 删除特种设备安全检查记录
     */
    @PostMapping( "/remove")
    public R remove(String ids)
    {
        return R.data(gasDeviceRecordService.deleteGasDeviceRecordByIds(ids));
    }

	/**
	 * 下载Excel模板
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "下载模板", httpMethod = "GET")
	public void downloadTemplate(HttpServletResponse response) {
		ExcelUtil.download(response, "temp" + File.separator + "特种设备安全检查.xlsx");
	}

	@PostMapping("write-notice")
	public R writeNotice(MultipartFile file) {
		List<GasDeviceRecord> list = ExcelUtil.read(file, GasDeviceRecord.class);
		return R.data(gasDeviceRecordService.saveBatch(list));
	}
}
