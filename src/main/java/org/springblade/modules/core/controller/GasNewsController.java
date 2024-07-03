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

import org.springblade.common.enums.NewsEnum;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.core.dto.GasNewsDTO;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.entity.GasNewsEntity;
import org.springblade.modules.core.vo.GasNewsVO;
import org.springblade.modules.core.excel.GasNewsExcel;
import org.springblade.modules.core.wrapper.GasNewsWrapper;
import org.springblade.modules.core.service.IGasNewsService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.tool.constant.BladeConstant;
import springfox.documentation.annotations.ApiIgnore;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * 新闻动态 控制器
 *
 * @author BladeX
 * @since 2024-07-01
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.DEV_CODE + "/blade-gasnews")
@Api(value = "新闻动态", tags = "新闻动态接口")
public class GasNewsController extends BladeController {

	private final IGasNewsService gasnewsService;

	/**
	 * 新闻动态 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入id")
	public R<GasNewsVO> detail(@RequestParam("id") String id) {
		GasNewsEntity detail = gasnewsService.getById(id);
		GasNewsVO gasNewsVO = GasNewsWrapper.build().entityVO(detail);
		gasNewsVO.setTypeName(NewsEnum.getStatus(detail.getType().toString()).getName());
		return R.data(gasNewsVO);
	}

	/**
	 * 新闻动态 自定义分页
	 */
	@PostMapping("/list")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入gasnews")
	public R<IPage<GasNewsVO>> page(@RequestBody GasNewsVO gasnews, @RequestBody Query query) {
		IPage<GasNewsVO> pages = gasnewsService.selectGasNewsPage(Condition.getPage(query), gasnews);
		return R.data(pages);
	}

	/**
	 * 新闻动态 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入gasnews")
	public R save(@Valid @RequestBody GasNewsDTO gasnews) {
		return R.data(gasnewsService.insertNews(gasnews));
	}

	/**
	 * 新闻动态 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入gasnews")
	public R update(@Valid @RequestBody GasNewsDTO gasnews) {
		return R.data(gasnewsService.updateNews(gasnews));
	}


	/**
	 * 新闻动态 删除
	 */
	@GetMapping( "/delete")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(gasnewsService.deleteById(ids));
	}


	/**
	 * 导出数据
	 */
	@GetMapping("/export-gasnews")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "导出数据", notes = "传入gasnews")
	public void exportGasNews(@ApiIgnore @RequestParam Map<String, Object> gasnews, BladeUser bladeUser, HttpServletResponse response) {
		QueryWrapper<GasNewsEntity> queryWrapper = Condition.getQueryWrapper(gasnews, GasNewsEntity.class);
		//if (!AuthUtil.isAdministrator()) {
		//	queryWrapper.lambda().eq(GasNews::getTenantId, bladeUser.getTenantId());
		//}
		queryWrapper.lambda().eq(GasNewsEntity::getIsDeleted, BladeConstant.DB_NOT_DELETED);
		List<GasNewsExcel> list = gasnewsService.exportGasNews(queryWrapper);
		ExcelUtil.export(response, "新闻动态数据" + DateUtil.time(), "新闻动态数据表", list, GasNewsExcel.class);
	}

}
