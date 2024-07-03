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

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.entity.ListedPriceEntity;
import org.springblade.modules.core.vo.ListedPriceVO;
import org.springblade.modules.core.excel.ListedPriceExcel;
import org.springblade.modules.core.wrapper.ListedPriceWrapper;
import org.springblade.modules.core.service.IListedPriceService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.tool.constant.BladeConstant;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * 液厂挂牌价格 控制器
 *
 * @author BladeX
 * @since 2024-06-14
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.DEV_CODE + "/blade-listedPrice/listedPrice")
@Api(value = "液厂挂牌价格", tags = "液厂挂牌价格接口")
public class ListedPriceController extends BladeController {

	private final IListedPriceService listedPriceService;

	/**
	 * 液厂挂牌价格 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入listedPrice")
	public R<ListedPriceVO> detail(ListedPriceEntity listedPrice) {
		ListedPriceEntity detail = listedPriceService.getOne(Condition.getQueryWrapper(listedPrice));
		return R.data(ListedPriceWrapper.build().entityVO(detail));
	}
	/**
	 * 液厂挂牌价格 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入listedPrice")
	public R<IPage<ListedPriceVO>> list(@ApiIgnore @RequestParam Map<String, Object> listedPrice, Query query) {
		IPage<ListedPriceEntity> pages = listedPriceService.page(Condition.getPage(query), Condition.getQueryWrapper(listedPrice, ListedPriceEntity.class));
		return R.data(ListedPriceWrapper.build().pageVO(pages));
	}

	/**
	 * 液厂挂牌价格 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入listedPrice")
	public R<IPage<ListedPriceVO>> page(ListedPriceVO listedPrice, Query query) {
		IPage<ListedPriceVO> pages = listedPriceService.selectListedPricePage(Condition.getPage(query), listedPrice);
		return R.data(pages);
	}

	/**
	 * 液厂挂牌价格 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入listedPrice")
	public R save(@Valid @RequestBody ListedPriceEntity listedPrice) {
		return R.status(listedPriceService.save(listedPrice));
	}

	/**
	 * 液厂挂牌价格 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入listedPrice")
	public R update(@Valid @RequestBody ListedPriceEntity listedPrice) {
		return R.status(listedPriceService.updateById(listedPrice));
	}

	/**
	 * 液厂挂牌价格 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入listedPrice")
	public R submit(@Valid @RequestBody ListedPriceEntity listedPrice) {
		return R.status(listedPriceService.saveOrUpdate(listedPrice));
	}

	/**
	 * 液厂挂牌价格 删除
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
		QueryWrapper<ListedPriceEntity> queryWrapper = Condition.getQueryWrapper(listedPrice, ListedPriceEntity.class);
		//if (!AuthUtil.isAdministrator()) {
		//	queryWrapper.lambda().eq(ListedPrice::getTenantId, bladeUser.getTenantId());
		//}
		queryWrapper.lambda().eq(ListedPriceEntity::getIsDeleted, BladeConstant.DB_NOT_DELETED);
		List<ListedPriceExcel> list = listedPriceService.exportListedPrice(queryWrapper);
		ExcelUtil.export(response, "液厂挂牌价格数据" + DateUtil.time(), "液厂挂牌价格数据表", list, ListedPriceExcel.class);
	}


	/**
	 * 液厂价格趋势统计图
	 */
	@PostMapping("/price-trend")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "大屏-液厂价格趋势统计图", notes = "传入液厂id")
	public R priceTrend(@ApiParam(value = "液厂id", required = true) @RequestParam String id) {
		return R.data(listedPriceService.priceTrend(id));
	}

	/**
	 * 获取液厂最新价格
	 */
	@PostMapping("/recent-quotation")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "获取液厂最新价格", notes = "传入液厂id")
	public R recentQuotation(@ApiParam(value = "液厂id", required = true) @RequestParam String fluid) {
		return R.data(listedPriceService.recentQuotation(fluid));
	}

}
