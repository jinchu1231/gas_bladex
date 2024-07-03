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

import org.springblade.modules.core.dto.dapin.DayPriceDto;
import org.springblade.modules.core.dto.dapin.OrderTrendDto;
import org.springblade.modules.core.dto.dapin.PriceTrendDto;
import org.springblade.modules.core.entity.ListedPriceEntity;
import org.springblade.modules.core.vo.ListedPriceVO;
import org.springblade.modules.core.excel.ListedPriceExcel;
import org.springblade.modules.core.mapper.ListedPriceMapper;
import org.springblade.modules.core.service.IListedPriceService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import java.util.List;

/**
 * 液厂挂牌价格 服务实现类
 *
 * @author BladeX
 * @since 2024-06-14
 */
@Service
public class ListedPriceServiceImpl extends BaseServiceImpl<ListedPriceMapper, ListedPriceEntity> implements IListedPriceService {

	@Override
	public IPage<ListedPriceVO> selectListedPricePage(IPage<ListedPriceVO> page, ListedPriceVO listedPrice) {
		return page.setRecords(baseMapper.selectListedPricePage(page, listedPrice));
	}


	@Override
	public List<ListedPriceExcel> exportListedPrice(Wrapper<ListedPriceEntity> queryWrapper) {
		List<ListedPriceExcel> listedPriceList = baseMapper.exportListedPrice(queryWrapper);
		//listedPriceList.forEach(listedPrice -> {
		//	listedPrice.setTypeName(DictCache.getValue(DictEnum.YES_NO, ListedPrice.getType()));
		//});
		return listedPriceList;
	}

	@Override
	public PriceTrendDto priceTrend(String id) {
		PriceTrendDto dto = new PriceTrendDto();
		int minValue = Integer.MAX_VALUE;
		int maxValue = Integer.MIN_VALUE;
		List<DayPriceDto> dayPriceDtos = baseMapper.priceTrend(id);
		dto.setPriceList(dayPriceDtos);
		for (DayPriceDto dayPriceDto : dayPriceDtos) {
			if (dayPriceDto.getPrice() < minValue){
				minValue = dayPriceDto.getPrice();
			}
			if (dayPriceDto.getPrice() > maxValue) {
				maxValue = dayPriceDto.getPrice();
			}
		}
		dto.setMax(maxValue);
		dto.setMin(minValue);
		return dto;
	}

	@Override
	public Double recentQuotation(String fluid) {
		return baseMapper.recentQuotation(fluid);
	}


}
