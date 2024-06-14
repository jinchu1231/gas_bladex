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

import org.springblade.modules.core.entity.GasListedPriceEntity;
import org.springblade.modules.core.excel.GasListedPriceExcel;
import org.springblade.modules.core.mapper.GasListedPriceMapper;
import org.springblade.modules.core.service.GasListedPriceService;
import org.springblade.modules.core.vo.GasListedPriceVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import java.util.List;

/**
 * 加气站挂牌价格 服务实现类
 *
 * @author BladeX
 * @since 2024-06-14
 */
@Service
public class GasListedPriceServiceImpl extends BaseServiceImpl<GasListedPriceMapper, GasListedPriceEntity> implements GasListedPriceService {

	@Override
	public IPage<GasListedPriceVO> selectListedPricePage(IPage<GasListedPriceVO> page, GasListedPriceVO listedPrice) {
		return page.setRecords(baseMapper.selectListedPricePage(page, listedPrice));
	}


	@Override
	public List<GasListedPriceExcel> exportListedPrice(Wrapper<GasListedPriceEntity> queryWrapper) {
		List<GasListedPriceExcel> listedPriceList = baseMapper.exportListedPrice(queryWrapper);
		//listedPriceList.forEach(listedPrice -> {
		//	listedPrice.setTypeName(DictCache.getValue(DictEnum.YES_NO, ListedPrice.getType()));
		//});
		return listedPriceList;
	}

}
