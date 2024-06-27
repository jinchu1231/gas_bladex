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
 * 液厂采购订单 Excel实体类
 *
 * @author BladeX
 * @since 2024-06-19
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class FieldOrderExcel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@ColumnWidth(20)
	@ExcelProperty("订单id")
	private String orderId;
	/**
	 * 站点id
	 */
	@ColumnWidth(20)
	@ExcelProperty("站点id")
	private String gasId;
	/**
	 * 站点名称
	 */
	@ColumnWidth(20)
	@ExcelProperty("站点名称")
	private String gasName;
	/**
	 * 订单日期
	 */
	@ColumnWidth(20)
	@ExcelProperty("订单日期")
	private String orderData;
	/**
	 * 车号
	 */
	@ColumnWidth(20)
	@ExcelProperty("车号")
	private String carNumber;
	/**
	 * 承运单位
	 */
	@ColumnWidth(20)
	@ExcelProperty("承运单位")
	private String unitOfCarriage;
	/**
	 * 发货单位
	 */
	@ColumnWidth(20)
	@ExcelProperty("发货单位")
	private String forwardingUnit;
	/**
	 * 收货单位
	 */
	@ColumnWidth(20)
	@ExcelProperty("收货单位")
	private String consigneeUnit;
	/**
	 * 到站量/吨
	 */
	@ColumnWidth(20)
	@ExcelProperty("到站量/吨")
	private String numberOfArrivals;
	/**
	 * 实卸/吨
	 */
	@ColumnWidth(20)
	@ExcelProperty("实卸/吨")
	private String solidDischarge;
	/**
	 * 每吨单价
	 */
	@ColumnWidth(20)
	@ExcelProperty("每吨单价")
	private String price;
	/**
	 * 采购人
	 */
	@ColumnWidth(20)
	@ExcelProperty("采购人")
	private String buyer;
	/**
	 * 验收人
	 */
	@ColumnWidth(20)
	@ExcelProperty("验收人")
	private String accepter;
	/**
	 * 购液负责人
	 */
	@ColumnWidth(20)
	@ExcelProperty("购液负责人")
	private String buyFieldPrincipal;
	/**
	 * 是否刪除
	 */
	@ColumnWidth(20)
	@ExcelProperty("是否刪除")
	private Integer isDeleted;

}
