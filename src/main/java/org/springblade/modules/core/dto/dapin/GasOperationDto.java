package org.springblade.modules.core.dto.dapin;

import lombok.Data;

@Data
public class GasOperationDto {
	private String gasName;
	/** 单日最高营收 */
	private String maxPrice;
	/** 昨日营收 */
	private String totalRevenue;
}
