/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.core.entity.GasListedPriceEntity;
import org.springblade.modules.core.excel.GasListedPriceExcel;
import org.springblade.modules.core.service.GasListedPriceService;
import org.springblade.modules.core.vo.GasListedPriceVO;
import org.springblade.modules.core.wrapper.GasListedPriceWrapper;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.tool.constant.BladeConstant;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * 加气站挂牌价格 控制器
 *
 * @author BladeX
 * @since 2024-06-14
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.DEV_CODE + "/gas-listedPrice")
@Api(value = "加气站挂牌价格", tags = "加气站挂牌价格接口")
public class GasListedPriceController extends BladeController {

	private final GasListedPriceService listedPriceService;

	/**
	 * 加气站挂牌价格 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入listedPrice")
	public R<GasListedPriceVO> detail(GasListedPriceEntity listedPrice) {
		GasListedPriceEntity detail = listedPriceService.getOne(Condition.getQueryWrapper(listedPrice));
		return R.data(GasListedPriceWrapper.build().entityVO(detail));
	}
	/**
	 * 加气站挂牌价格 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入listedPrice")
	public R<IPage<GasListedPriceVO>> list(@ApiIgnore @RequestParam Map<String, Object> listedPrice, Query query) {
		IPage<GasListedPriceEntity> pages = listedPriceService.page(Condition.getPage(query), Condition.getQueryWrapper(listedPrice, GasListedPriceEntity.class));
		return R.data(GasListedPriceWrapper.build().pageVO(pages));
	}

	/**
	 * 加气站挂牌价格 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入listedPrice")
	public R<IPage<GasListedPriceVO>> page(GasListedPriceVO listedPrice, Query query) {
		IPage<GasListedPriceVO> pages = listedPriceService.selectListedPricePage(Condition.getPage(query), listedPrice);
		return R.data(pages);
	}

	/**
	 * 加气站挂牌价格 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入listedPrice")
	public R save(@Valid @RequestBody GasListedPriceEntity listedPrice) {
		return R.status(listedPriceService.save(listedPrice));
	}

	/**
	 * 加气站挂牌价格 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入listedPrice")
	public R update(@Valid @RequestBody GasListedPriceEntity listedPrice) {
		return R.status(listedPriceService.updateById(listedPrice));
	}

	/**
	 * 加气站挂牌价格 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入listedPrice")
	public R submit(@Valid @RequestBody GasListedPriceEntity listedPrice) {
		return R.status(listedPriceService.saveOrUpdate(listedPrice));
	}

	/**
	 * 加气站挂牌价格 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(listedPriceService.deleteLogic(Func.toLongList(ids)));
	}


	/**
	 * 导出数据
	 */
	@GetMapping("/export-listedPrice")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "导出数据", notes = "传入listedPrice")
	public void exportListedPrice(@ApiIgnore @RequestParam Map<String, Object> listedPrice, BladeUser bladeUser, HttpServletResponse response) {
		QueryWrapper<GasListedPriceEntity> queryWrapper = Condition.getQueryWrapper(listedPrice, GasListedPriceEntity.class);
		//if (!AuthUtil.isAdministrator()) {
		//	queryWrapper.lambda().eq(ListedPrice::getTenantId, bladeUser.getTenantId());
		//}
		queryWrapper.lambda().eq(GasListedPriceEntity::getIsDeleted, BladeConstant.DB_NOT_DELETED);
		List<GasListedPriceExcel> list = listedPriceService.exportListedPrice(queryWrapper);
		ExcelUtil.export(response, "加气站挂牌价格数据" + DateUtil.time(), "加气站挂牌价格数据表", list, GasListedPriceExcel.class);
	}

}
