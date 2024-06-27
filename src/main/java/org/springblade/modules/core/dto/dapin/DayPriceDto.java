package org.springblade.modules.core.dto.dapin;

import lombok.Data;

@Data
public class DayPriceDto {
	/** 日期 */
	private String priceDay;
	/** 价格 */
	private int price;
}
