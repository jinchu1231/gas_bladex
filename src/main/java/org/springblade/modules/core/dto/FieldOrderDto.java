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
package org.springblade.modules.core.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import org.springblade.modules.core.entity.FieldOrderEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 液厂采购订单 数据传输对象实体类
 *
 * @author BladeX
 * @since 2024-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FieldOrderDto extends FieldOrderEntity {
	private static final long serialVersionUID = 1L;


	private Long id;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 站点id
	 */
	private String gasId;
	/**
	 * 站点名称
	 */
	private String gasName;
	/**
	 * 订单日期
	 */
	private String orderData;
	/**
	 * 数量
	 */
	private double num;
	/**
	 * 车号
	 */
	private String carNumber;
	/**
	 * 承运单位
	 */
	private String unitOfCarriage;
	/**
	 * 发货单位
	 */
	private String forwardingUnit;
	/**
	 * 收货单位
	 */
	private String consigneeUnit;
	/**
	 * 到站量/吨
	 */
	private String numberOfArrivals;
	/**
	 * 实卸/吨
	 */
	private String solidDischarge;
	/**
	 * 每吨单价
	 */
	private BigDecimal price;
	/**
	 * 总价
	 */
	private BigDecimal totalPrices;
	/**
	 * 订单状态(0-已创建未审核;1-已审核;2-已支付;3-已完成)
	 */
	private String orderStatus;
	/**
	 * 采购人
	 */
	private String buyer;
	/**
	 * 验收人
	 */
	private String accepter;
	/**
	 * 购液负责人
	 */
	private String buyFieldPrincipal;

	/**
	 * 文件url
	 */
	private String fileUrl;

	/**
	 * 订单内部pdf文件
	 */
	private String pdfUrl;

	private List<String> pdfUrlList;
}
