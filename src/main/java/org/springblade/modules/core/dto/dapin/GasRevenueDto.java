package org.springblade.modules.core.dto.dapin;

import lombok.Data;

@Data
public class GasRevenueDto {
	/** 日均 */
	private double averageDaily;
	/** 最高 */
	private double maxRevenue;
	/** 总营收 */
	private double grossRevenue;
}
