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
package org.springblade.modules.core.mapper;

import org.springblade.modules.core.dto.dapin.DayPriceDto;
import org.springblade.modules.core.dto.dapin.OrderTrendDto;
import org.springblade.modules.core.dto.dapin.PriceTrendDto;
import org.springblade.modules.core.entity.ListedPriceEntity;
import org.springblade.modules.core.vo.ListedPriceVO;
import org.springblade.modules.core.excel.ListedPriceExcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 液厂挂牌价格 Mapper 接口
 *
 * @author BladeX
 * @since 2024-06-14
 */
public interface ListedPriceMapper extends BaseMapper<ListedPriceEntity> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param listedPrice
	 * @return
	 */
	List<ListedPriceVO> selectListedPricePage(IPage page,@Param("vo") ListedPriceVO listedPrice);


	/**
	 * 获取导出数据
	 *
	 * @param queryWrapper
	 * @return
	 */
	List<ListedPriceExcel> exportListedPrice(@Param("ew") Wrapper<ListedPriceEntity> queryWrapper);

	List<DayPriceDto> priceTrend(String id);


	Double recentQuotation(String fluid);
}
