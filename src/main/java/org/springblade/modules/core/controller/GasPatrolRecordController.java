package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.entity.GasPatrolRecord;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.service.GasPatrolRecordService;
import org.springblade.modules.core.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

/**
 * 加气站巡查记录Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/gas-patrol")
@Api(value = "加气站巡查记录", tags = "加气站巡查记录接口")
public class GasPatrolRecordController {

    @Autowired
    private GasPatrolRecordService gasPatrolRecordService;

    /**
     * 查询加气站巡查记录列表
     */
    @PostMapping("/list")
    public R<IPage<GasPatrolRecord>> list(@RequestBody @Valid GasPatrolRecord gasPatrolRecord,
				  @Valid @RequestBody Query query)
    {
		IPage<GasPatrolRecord> list =
			gasPatrolRecordService.selectGasPatrolRecordList(Condition.getPage(query),gasPatrolRecord);
        return R.data(list);
    }


    /**
     * 新增保存加气站巡查记录
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasPatrolRecord gasPatrolRecord)
    {
        return R.data(gasPatrolRecordService.insertGasPatrolRecord(gasPatrolRecord));
    }


    /**
     * 修改保存加气站巡查记录
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasPatrolRecord gasPatrolRecord)
    {
        return R.data(gasPatrolRecordService.updateGasPatrolRecord(gasPatrolRecord));
    }

    /**
     * 删除加气站巡查记录
     */
    @PostMapping( "/remove")
    public R remove(String ids)
    {
        return R.data(gasPatrolRecordService.deleteGasPatrolRecordByIds(ids));
    }

	/**
	 * 导出加气站巡查记录列表
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) {
		List<GasPatrolRecord> list = gasPatrolRecordService.selectGasPatrolRecordAllList();
		ExcelUtil.export(response, "巡查记录", "巡查记录数据表", list, GasPatrolRecord.class);
	}

	/**
	 * 下载Excel模板
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "下载模板", httpMethod = "GET")
	public void downloadTemplate(HttpServletResponse response) {
		ExcelUtil.download(response, "temp" + File.separator + "巡查记录.xlsx");
	}

	@PostMapping("write-notice")
	public R writeNotice(MultipartFile file) {
		List<GasPatrolRecord> list = ExcelUtil.read(file, GasPatrolRecord.class);
		return R.data(gasPatrolRecordService.saveBatch(list));
	}
}
