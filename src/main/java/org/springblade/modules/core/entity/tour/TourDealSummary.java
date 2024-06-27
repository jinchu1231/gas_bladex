package org.springblade.modules.core.entity.tour;

import lombok.Data;

@Data
public class TourDealSummary {
	/** 总加液量(公斤) */
	private String addLiquidMeasureCount;

	/** 总金额(元) */
	private String amountCount;

	/** 应收金额(元) */
	private String amountReceivable;

	/** 实收金额(元) */
	private String fundsReceived;

	/** 总交易数(笔) */
	private String dealCount;
}
