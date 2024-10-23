package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.esotericsoftware.kryo.kryo5.minlog.Log;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.RandomType;
import org.springblade.modules.core.dto.FluidFieldBaseInfoDto;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;
import org.springblade.modules.core.entity.GasBaseInfo;
import org.springblade.modules.core.entity.GasListedPriceEntity;
import org.springblade.modules.core.service.FluidFieldBaseInfoService;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(AppConstant.DEV_CODE + "/fluid_field-service/baseinfo")
@Api(value = "液厂基本信息表", tags = "液厂基本信息表接口")
public class FluidFieldBaseInfoController extends BladeController {

    @Autowired
    private FluidFieldBaseInfoService fluidFieldBaseInfoService;

	/**
	 * 加液厂详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入fieldOrder")
	public R<FluidFieldBaseInfoDto> detail(String id) {
		return R.data(fluidFieldBaseInfoService.selectInfoById(id));
	}

    /**
     * 获取液厂基础信息
     */
    @ApiOperation("获取液厂基础信息")
    @PostMapping("/getBaseInfoList")
    public R getBaseInfoList(){
        return R.data(fluidFieldBaseInfoService.getBaseInfoList());
    }

	/**
	 * 获取液厂list
	 */
	@ApiOperation("获取液厂list")
	@PostMapping("/getFluList")
	public R getFluList(){
		return R.data(fluidFieldBaseInfoService.getFluList());
	}

	/**
	 * 查询加气站巡查记录列表
	 */
	@PostMapping("/pageList")
	public R<IPage<FluidFieldBaseInfo>> list(@RequestBody FluidFieldBaseInfo fluidFieldBaseInfo, @Valid @RequestBody Query query) {
		IPage<FluidFieldBaseInfo> list =
			fluidFieldBaseInfoService.selectFluidFieldBaseInfoList(Condition.getPage(query),fluidFieldBaseInfo);
		return R.data(list);
	}

	/**
	 * 液厂基础信息 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入listedPrice")
	public R save(@Valid @RequestBody FluidFieldBaseInfo baseInfo) {
		String random = Func.random(15, RandomType.INT);
		baseInfo.setFluNumber(random);
		return R.status(fluidFieldBaseInfoService.save(baseInfo));
	}

	/**
	 * 液厂基础信息 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入listedPrice")
	public R update(@Valid @RequestBody FluidFieldBaseInfo baseInfo) {
		return R.status(fluidFieldBaseInfoService.updateBaseInfoById(baseInfo));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除", notes = "传入GasBaseInfo")
	public R delete(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(fluidFieldBaseInfoService.deleteLogic(Func.toLongList(ids)));
	}
}
