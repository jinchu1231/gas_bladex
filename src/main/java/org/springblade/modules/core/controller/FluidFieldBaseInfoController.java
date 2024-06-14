package org.springblade.modules.core.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;
import org.springblade.modules.core.entity.GasListedPriceEntity;
import org.springblade.modules.core.service.FluidFieldBaseInfoService;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/fluid_field-service/baseinfo")
@Api(value = "加气站基本信息表", tags = "加气站基本信息表接口")
public class FluidFieldBaseInfoController {

    @Autowired
    private FluidFieldBaseInfoService fluidFieldBaseInfoService;

    /**
     * 获取液厂基础信息
     */
    @ApiOperation("获取液厂基础信息")
    @PostMapping("/getBaseInfoList")
    public R getBaseInfoList(){
        return R.data(fluidFieldBaseInfoService.getBaseInfoList());
    }

	/**
	 * 液厂基础信息 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入listedPrice")
	public R save(@Valid @RequestBody FluidFieldBaseInfo baseInfo) {
		return R.status(fluidFieldBaseInfoService.save(baseInfo));
	}

	/**
	 * 液厂基础信息 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入listedPrice")
	public R update(@Valid @RequestBody FluidFieldBaseInfo baseInfo) {
		return R.status(fluidFieldBaseInfoService.updateById(baseInfo));
	}
}
