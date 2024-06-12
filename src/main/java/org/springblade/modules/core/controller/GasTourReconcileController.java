package org.springblade.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.entity.GasPatrolRecord;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.service.GasTourReconcileService;
import org.springblade.modules.core.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

/**
 * 交班对账Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/gas-reconcile")
@Api(value = "交班对账", tags = "交班对账接口")
public class GasTourReconcileController {

    @Autowired
    private GasTourReconcileService gasTourReconcileService;

    /**
     * 查询交班对账列表
     */
    @PostMapping("/list")
    public R list(@RequestBody @Valid GasTourReconcile gasTourReconcile,
				  @Valid @RequestBody Query query)
    {
		List<GasTourReconcileDto> list =
			gasTourReconcileService.selectGasTourReconcileList(Condition.getPage(query),gasTourReconcile);
        return R.data(list);
    }

    /**
     * 新增保存交班对账
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasTourReconcileDto dto)
    {
        return R.data(gasTourReconcileService.insertGasTourReconcile(dto));
    }


    /**
     * 修改保存交班对账
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasTourReconcileDto dto)
    {
        return R.data(gasTourReconcileService.updateGasTourReconcile(dto));
    }

    /**
     * 删除交班对账
     */
    @PostMapping( "/remove")
    public R remove(String ids)
    {
        return R.data(gasTourReconcileService.deleteGasTourReconcileByIds(ids));
    }

	/**
	 * 下载Excel模板
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "下载模板", httpMethod = "GET")
	public void downloadTemplate(HttpServletResponse response) {
		ExcelUtil.download(response, "temp" + File.separator + "交班对账.xlsx");
	}

	/**
	 * 导出交班对账列表
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) {
		List<GasTourReconcileExcelDto> dtos = gasTourReconcileService.selectAllGasTourReconcileList();
		ExcelUtil.export(response, "交班对账", "交班对账数据表", dtos, GasTourReconcileExcelDto.class);
	}

/*	@PostMapping("write-notice")
	public R writeNotice(MultipartFile file) {
		List<GasTourReconcileExcelDto> list = ExcelUtil.read(file, GasTourReconcileExcelDto.class);
		//需要将dto中的汇总数据转换成json格式后再进行保存
		return R.data(gasTourReconcileService.saveBatch(list));
	}*/
}
