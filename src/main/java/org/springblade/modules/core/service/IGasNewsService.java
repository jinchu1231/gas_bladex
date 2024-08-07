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
package org.springblade.modules.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.core.dto.GasNewsDTO;
import org.springblade.modules.core.entity.GasNewsEntity;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.vo.GasNewsVO;
import org.springblade.modules.core.excel.GasNewsExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import java.util.List;

/**
 * 新闻动态 服务类
 *
 * @author BladeX
 * @since 2024-07-01
 */
public interface IGasNewsService extends IService<GasNewsEntity> {
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param gasnews
	 * @return
	 */
	IPage<GasNewsVO> selectGasNewsPage(IPage<GasNewsVO> page, GasNewsVO gasnews);


	/**
	 * 导出数据
	 *
	 * @param queryWrapper
	 * @return
	 */
	List<GasNewsExcel> exportGasNews(Wrapper<GasNewsEntity> queryWrapper);

	int insertNews(GasNewsDTO gasnews);

	int deleteById(String ids);

	int updateNews(GasNewsDTO gasnews);
}
