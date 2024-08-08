package org.springblade.modules.core.dto.dapin;

import lombok.Data;

import java.util.List;

/**
 * 液厂价格趋势图dto
 */
@Data
public class PriceServerTrendDto {
	/** 日期 */
	private List<String> dateList;

	/** 价格 */
	private List<String> priceList;
}
