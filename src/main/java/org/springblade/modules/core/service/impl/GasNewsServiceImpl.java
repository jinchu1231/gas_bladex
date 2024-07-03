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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.enums.NewsEnum;
import org.springblade.modules.core.dto.GasNewsDTO;
import org.springblade.modules.core.entity.GasNewsEntity;
import org.springblade.modules.core.vo.GasNewsVO;
import org.springblade.modules.core.excel.GasNewsExcel;
import org.springblade.modules.core.mapper.GasNewsMapper;
import org.springblade.modules.core.service.IGasNewsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import java.util.List;

/**
 * 新闻动态 服务实现类
 *
 * @author BladeX
 * @since 2024-07-01
 */
@Service
public class GasNewsServiceImpl extends ServiceImpl<GasNewsMapper, GasNewsEntity> implements IGasNewsService {

	@Override
	public IPage<GasNewsVO> selectGasNewsPage(IPage<GasNewsVO> page, GasNewsVO gasnews) {
		IPage<GasNewsVO> gasNewsVOIPage = page.setRecords(baseMapper.selectGasNewsPage(page, gasnews));
		gasNewsVOIPage.getRecords().forEach(record -> {
			record.setTypeName(NewsEnum.getStatus(record.getType().toString()).getName());
		});
		return gasNewsVOIPage;
	}


	@Override
	public List<GasNewsExcel> exportGasNews(Wrapper<GasNewsEntity> queryWrapper) {
		List<GasNewsExcel> gasnewsList = baseMapper.exportGasNews(queryWrapper);
		//gasnewsList.forEach(gasnews -> {
		//	gasnews.setTypeName(DictCache.getValue(DictEnum.YES_NO, GasNews.getType()));
		//});
		return gasnewsList;
	}

	@Override
	public int insertNews(GasNewsDTO gasnews) {
		GasNewsEntity gasNewsEntity = new GasNewsEntity(gasnews);
		return baseMapper.insertNews(gasNewsEntity);
	}

	@Override
	public int deleteById(String ids) {
		return baseMapper.deleteNewsById(ids);
	}

	@Override
	public int updateNews(GasNewsDTO gasnews) {
		GasNewsEntity gasNewsEntity = new GasNewsEntity(gasnews);
		return baseMapper.updateNews(gasNewsEntity);
	}

}
