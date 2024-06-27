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

import org.springblade.modules.core.entity.FieldOrderEntity;
import org.springblade.modules.core.vo.FieldOrderVO;
import org.springblade.modules.core.excel.FieldOrderExcel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.core.vo.OrderVO;

import java.util.List;

/**
 * 液厂采购订单 Mapper 接口
 *
 * @author BladeX
 * @since 2024-06-19
 */
public interface FieldOrderMapper extends BaseMapper<FieldOrderEntity> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param fieldOrder
	 * @return
	 */
	List<FieldOrderVO> selectFieldOrderPage(IPage page, FieldOrderVO fieldOrder);


	/**
	 * 获取导出数据
	 *
	 * @param queryWrapper
	 * @return
	 */
	List<FieldOrderExcel> exportFieldOrder(@Param("ew") Wrapper<FieldOrderEntity> queryWrapper);

	OrderVO selectOrderCount();
}
