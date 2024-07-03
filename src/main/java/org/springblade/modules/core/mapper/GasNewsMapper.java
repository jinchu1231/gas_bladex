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

import org.springblade.modules.core.dto.GasNewsDTO;
import org.springblade.modules.core.entity.GasNewsEntity;
import org.springblade.modules.core.vo.GasNewsVO;
import org.springblade.modules.core.excel.GasNewsExcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 新闻动态 Mapper 接口
 *
 * @author BladeX
 * @since 2024-07-01
 */
public interface GasNewsMapper extends BaseMapper<GasNewsEntity> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param gasnews
	 * @return
	 */
	List<GasNewsVO> selectGasNewsPage(IPage page,@Param("vo") GasNewsVO gasnews);


	/**
	 * 获取导出数据
	 *
	 * @param queryWrapper
	 * @return
	 */
	List<GasNewsExcel> exportGasNews(@Param("ew") Wrapper<GasNewsEntity> queryWrapper);

	int insertNews(GasNewsEntity gasnews);

	int updateNews(@Param("gas") GasNewsEntity gasNewsEntity);

	int deleteNewsById(String ids);
}
