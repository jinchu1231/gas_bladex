package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.entity.GasPatrolRecord;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.service.GasTourReconcileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
     * 导出交班对账列表
     */
    /*@PostMapping("/export")
    public R export(GasTourReconcile gasTourReconcile)
    {
        List<GasTourReconcileDto> list = gasTourReconcileService.selectGasTourReconcileList(gasTourReconcile);
        ExcelUtil<GasTourReconcileDto> util = new ExcelUtil<GasTourReconcileDto>(GasTourReconcileDto.class);
        return util.exportExcel(list, "交班对账数据");
    }*/

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
}
