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
package org.springblade.modules.core.excel;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import java.io.Serializable;


/**
 * 加气站挂牌价格 Excel实体类
 *
 * @author BladeX
 * @since 2024-06-14
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class GasListedPriceExcel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 液厂id
	 */
	@ColumnWidth(20)
	@ExcelProperty("液厂id")
	private String gasId;
	/**
	 * 液厂名称
	 */
	@ColumnWidth(20)
	@ExcelProperty("液厂名称")
	private String gasName;
	/**
	 * 日期
	 */
	@ColumnWidth(20)
	@ExcelProperty("日期")
	private String day;
	/**
	 * 挂牌价格
	 */
	@ColumnWidth(20)
	@ExcelProperty("挂牌价格")
	private String listedPrice;
	/**
	 * 是否删除
	 */
	@ColumnWidth(20)
	@ExcelProperty("是否删除")
	private Integer isDeleted;

}
