package org.springblade.modules.core.dto.dapin;

import lombok.Data;

import java.util.List;

/**
 * 液厂价格趋势图dto
 */
@Data
public class PriceTrendDto {
	/** 最小值 */
	private double min;
	/** 最大值 */
	private double max;
	/** 趋势列表 */
	private List<DayPriceDto> priceList;
}
