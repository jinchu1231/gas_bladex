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
package org.springblade.modules.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import org.apache.poi.hpsf.Decimal;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tenant.mp.TenantEntity;
import org.springblade.modules.core.dto.FieldOrderDto;

/**
 * 液厂采购订单 实体类
 *
 * @author BladeX
 * @since 2024-06-19
 */
@Data
@TableName("fluid_field_order")
@ApiModel(value = "FieldOrder对象", description = "液厂采购订单")
@EqualsAndHashCode(callSuper = true)
public class FieldOrderEntity extends BaseEntity {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 订单id
	 */
	@ApiModelProperty(value = "订单id")
	private String orderId;
	/**
	 * 站点id
	 */
	@ApiModelProperty(value = "站点id")
	private String gasId;
	/**
	 * 站点名称
	 */
	@ApiModelProperty(value = "站点名称")
	private String gasName;
	/**
	 * 订单日期
	 */
	@ApiModelProperty(value = "订单日期")
	private String orderData;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private double num;
	/**
	 * 车号
	 */
	@ApiModelProperty(value = "车号")
	private String carNumber;
	/**
	 * 承运单位
	 */
	@ApiModelProperty(value = "承运单位")
	private String unitOfCarriage;
	/**
	 * 发货单位
	 */
	@ApiModelProperty(value = "发货单位")
	private String forwardingUnit;
	/**
	 * 收货单位
	 */
	@ApiModelProperty(value = "收货单位")
	private String consigneeUnit;
	/**
	 * 到站量/吨
	 */
	@ApiModelProperty(value = "到站量/吨")
	private String numberOfArrivals;
	/**
	 * 实卸/吨
	 */
	@ApiModelProperty(value = "实卸/吨")
	private String solidDischarge;
	/**
	 * 每吨单价
	 */
	@ApiModelProperty(value = "每吨单价")
	private BigDecimal price;
	/**
	 * 总价
	 */
	@ApiModelProperty(value = "总价")
	private BigDecimal totalPrices;
	/**
	 * 订单状态(0-已创建未审核;1-已审核;2-已支付;3-已完成)
	 */
	@ApiModelProperty(value = "订单状态(0-已创建未审核;1-已审核;2-已支付;3-已完成)")
	private String orderStatus;
	/**
	 * 采购人
	 */
	@ApiModelProperty(value = "采购人")
	private String buyer;
	/**
	 * 验收人
	 */
	@ApiModelProperty(value = "验收人")
	private String accepter;
	/**
	 * 购液负责人
	 */
	@ApiModelProperty(value = "购液负责人")
	private String buyFieldPrincipal;

	/**
	 * 文件url
	 */
	@ApiModelProperty(value = "文件url")
	private String fileUrl;

	/**
	 * 订单内部pdf文件
	 */
	@ApiModelProperty(value = "订单内部pdf文件")
	private String pdfUrl;

	/**
	 * 到货时间
	 */
	@ApiModelProperty(value = "到货时间")
	private Date arrivalTime;

	/**
	 * 采购方案
	 */
	@ApiModelProperty(value = "采购方案")
	private String procurementScheme;

	/**
	 * 审核内容
	 */
	@ApiModelProperty(value = "审核内容")
	private String content;

	public FieldOrderEntity(){}

	public FieldOrderEntity(FieldOrderDto dto){
		this.id = dto.getId();
		this.orderId = dto.getOrderId();
		this.gasId = dto.getGasId();
		this.gasName = dto.getGasName();
		this.orderData = dto.getOrderData();
		this.num = dto.getNum();
		this.carNumber = dto.getCarNumber();
		this.unitOfCarriage = dto.getUnitOfCarriage();
		this.forwardingUnit = dto.getForwardingUnit();
		this.consigneeUnit = dto.getConsigneeUnit();
		this.numberOfArrivals = dto.getNumberOfArrivals();
		this.solidDischarge = dto.getSolidDischarge();
		this.price = dto.getPrice();
		this.totalPrices = dto.getTotalPrices();
		this.orderStatus = dto.getOrderStatus();
		this.buyer = dto.getBuyer();
		this.accepter = dto.getAccepter();
		this.buyFieldPrincipal = dto.getBuyFieldPrincipal();
		this.fileUrl = dto.getFileUrl();
		this.pdfUrl = dto.getPdfUrl();
		this.arrivalTime = dto.getArrivalTime();
		this.procurementScheme = dto.getProcurementScheme();
		this.content = dto.getContent();
	}
}
