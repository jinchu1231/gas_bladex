package org.springblade.modules.core.vo;

import lombok.Data;

@Data
public class OrderVO {

	/**
	 * 订单总数
	 */
	private int orderCount;
	/**
	 * 液厂订单
	 */
	private int FieldOrderCount;
	/**
	 * 车队订单
	 */
	private int carOderCount;
}
