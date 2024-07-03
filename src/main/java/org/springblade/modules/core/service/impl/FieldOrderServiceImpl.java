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
package org.springblade.modules.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springblade.common.cache.DictCache;
import org.springblade.common.enums.DictEnum;
import org.springblade.common.enums.OrderEnum;
import org.springblade.modules.core.dto.dapin.OrderTrendDto;
import org.springblade.modules.core.entity.FieldOrderEntity;
import org.springblade.modules.core.vo.FieldOrderVO;
import org.springblade.modules.core.excel.FieldOrderExcel;
import org.springblade.modules.core.mapper.FieldOrderMapper;
import org.springblade.modules.core.service.IFieldOrderService;
import org.springblade.modules.core.vo.OrderVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * 液厂采购订单 服务实现类
 *
 * @author BladeX
 * @since 2024-06-19
 */
@Service
public class FieldOrderServiceImpl extends BaseServiceImpl<FieldOrderMapper, FieldOrderEntity> implements IFieldOrderService {

	@Override
	public IPage<FieldOrderVO> selectFieldOrderPage(IPage<FieldOrderVO> page, FieldOrderVO fieldOrder) {
		IPage<FieldOrderVO> fieldOrderVOIPage = page.setRecords(baseMapper.selectFieldOrderPage(page, fieldOrder));
		fieldOrderVOIPage.getRecords().forEach(fieldOrders -> {
			fieldOrders.setOrderStatusName(OrderEnum.getStatus(fieldOrders.getOrderStatus()).getName());
			if(!StringUtils.isEmpty(fieldOrders.getPdfUrl())){
				ObjectMapper mapper = new ObjectMapper();
				List<String> list = null;
				try {
					list = mapper.readValue(fieldOrders.getPdfUrl(), new TypeReference<List<String>>() {
					});
				}catch (IOException e) {
					e.printStackTrace();
				}
				fieldOrders.setPdfUrlList(list);
			}
		});
		return fieldOrderVOIPage;
	}


	@Override
	public List<FieldOrderExcel> exportFieldOrder(Wrapper<FieldOrderEntity> queryWrapper) {
		List<FieldOrderExcel> fieldOrderList = baseMapper.exportFieldOrder(queryWrapper);
		//fieldOrderList.forEach(fieldOrder -> {
		//	fieldOrder.setTypeName(DictCache.getValue(DictEnum.YES_NO, FieldOrder.getType()));
		//});
		return fieldOrderList;
	}

	@Override
	public OrderVO selectOrderCount() {
		return baseMapper.selectOrderCount();
	}

	@Override
	public List<OrderTrendDto> orderTrend(String id) {
		return baseMapper.orderTrend(id);
	}

	@Override
	public FieldOrderVO getOrderById(String id) {
		return baseMapper.getOrderById(id);
	}

}
