package org.springblade.modules.core.entity.tour;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TourDealSummary {
	/** 总加液量(公斤) */
	private double addLiquidMeasureCount;

	/** 总金额(元) */
	private BigDecimal amountCount;

	/** 应收金额(元) */
	private BigDecimal amountReceivable;

	/** 实收金额(元) */
	private BigDecimal fundsReceived;

	/** 总交易数(笔) */
	private int dealCount;
}
