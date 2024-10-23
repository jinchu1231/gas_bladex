package org.springblade.modules.core.dto.dapin;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderTrendDto {
	/** 订单总数 */
	private int orderNum;
	/** 平均价格 */
	private BigDecimal price;
	/** 数量(吨) */
	private int num;
	/** 时间 */
	private String orderDate;
}
