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

import org.springblade.core.secure.BladeUser;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.RandomType;
import org.springblade.modules.core.vo.OrderVO;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.entity.FieldOrderEntity;
import org.springblade.modules.core.vo.FieldOrderVO;
import org.springblade.modules.core.excel.FieldOrderExcel;
import org.springblade.modules.core.wrapper.FieldOrderWrapper;
import org.springblade.modules.core.service.IFieldOrderService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.tool.constant.BladeConstant;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * 液厂采购订单 控制器
 *
 * @author BladeX
 * @since 2024-06-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dev/blade-fieldOrder/fieldOrder")
@Api(value = "液厂采购订单", tags = "液厂采购订单接口")
public class FieldOrderController extends BladeController {

	private final IFieldOrderService fieldOrderService;

	/**
	 * 液厂采购订单 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入fieldOrder")
	public R<FieldOrderVO> detail(String id) {
		FieldOrderEntity detail = fieldOrderService.getById(id);
		return R.data(FieldOrderWrapper.build().entityVO(detail));
	}
/*
	*/
/**
	 * 液厂采购订单 分页
	 *//*
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入fieldOrder")
	public R<IPage<FieldOrderVO>> list(@ApiIgnore @RequestParam Map<String, Object> fieldOrder, Query query) {
		IPage<FieldOrderEntity> pages = fieldOrderService.page(Condition.getPage(query), Condition.getQueryWrapper(fieldOrder, FieldOrderEntity.class));
		return R.data(FieldOrderWrapper.build().pageVO(pages));
	}*/

	/**
	 * 液厂采购订单 自定义分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入fieldOrder")
	public R<IPage<FieldOrderVO>> page(FieldOrderVO fieldOrder,@RequestBody Query query) {
		IPage<FieldOrderVO> pages = fieldOrderService.selectFieldOrderPage(Condition.getPage(query), fieldOrder);
		return R.data(pages);
	}

	/**
	 * 订单信息统计
	 */
	@GetMapping("/countOrder")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "订单信息统计")
	public R<OrderVO> countOrder() {
		OrderVO vo = fieldOrderService.selectOrderCount();
		return R.data(vo);
	}

	/**
	 * 液厂采购订单 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入fieldOrder")
	public R save(@Valid @RequestBody FieldOrderEntity fieldOrder) {
		String random = Func.random(15, RandomType.INT);
		fieldOrder.setOrderId(random);
		BigDecimal num = new BigDecimal(fieldOrder.getNum());
		BigDecimal price = fieldOrder.getPrice();
		fieldOrder.setTotalPrices(num.multiply(price));
		return R.status(fieldOrderService.save(fieldOrder));
	}

	/**
	 * 液厂采购订单 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入fieldOrder")
	public R update(@Valid @RequestBody FieldOrderEntity fieldOrder) {
		return R.status(fieldOrderService.updateById(fieldOrder));
	}

	/**
	 * 液厂采购订单 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入fieldOrder")
	public R submit(@Valid @RequestBody FieldOrderEntity fieldOrder) {
		return R.status(fieldOrderService.saveOrUpdate(fieldOrder));
	}

	/**
	 * 液厂采购订单 删除
	 */
	@GetMapping("/delete")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(fieldOrderService.deleteLogic(Func.toLongList(ids)));
	}


	/**
	 * 导出数据
	 */
	@GetMapping("/export-fieldOrder")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "导出数据", notes = "传入fieldOrder")
	public void exportFieldOrder(@ApiIgnore @RequestParam Map<String, Object> fieldOrder, BladeUser bladeUser, HttpServletResponse response) {
		QueryWrapper<FieldOrderEntity> queryWrapper = Condition.getQueryWrapper(fieldOrder, FieldOrderEntity.class);
		//if (!AuthUtil.isAdministrator()) {
		//	queryWrapper.lambda().eq(FieldOrder::getTenantId, bladeUser.getTenantId());
		//}
		queryWrapper.lambda().eq(FieldOrderEntity::getIsDeleted, BladeConstant.DB_NOT_DELETED);
		List<FieldOrderExcel> list = fieldOrderService.exportFieldOrder(queryWrapper);
		ExcelUtil.export(response, "液厂采购订单数据" + DateUtil.time(), "液厂采购订单数据表", list, FieldOrderExcel.class);
	}

}
