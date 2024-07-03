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

import java.util.Date;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import java.io.Serializable;


/**
 * 新闻动态 Excel实体类
 *
 * @author BladeX
 * @since 2024-07-01
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class GasNewsExcel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 新闻类型(1-公司动态;2-行业资讯;3-行业新闻;4-其他)
	 */
	@ColumnWidth(20)
	@ExcelProperty("新闻类型(1-公司动态;2-行业资讯;3-行业新闻;4-其他)")
	private Integer type;
	/**
	 * 标题
	 */
	@ColumnWidth(20)
	@ExcelProperty("标题")
	private String headline;
	/**
	 * 内容
	 */
	@ColumnWidth(20)
	@ExcelProperty("内容")
	private String content;
	/**
	 * 所属站点
	 */
	@ColumnWidth(20)
	@ExcelProperty("所属站点")
	private Long theirId;
	/**
	 * 是否刪除
	 */
	@ColumnWidth(20)
	@ExcelProperty("是否刪除")
	private Integer isDeleted;
	/**
	 *
	 */
	@ColumnWidth(20)
	@ExcelProperty("")
	private String tenantId;

}
