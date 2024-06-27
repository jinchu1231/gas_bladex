package org.springblade.modules.core.entity.tour;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TourPaySummary {
	/** 充值数据-总充值额(元)*/
	private String totalRechargeAmount;

	/** 充充值数据-应收额(元)*/
	private String amountReceivableT;

	/** 充值数据-实收金额(元)*/
	private String fundsReceivedT;

	/** 充值数据-总交易数(笔)*/
	private String dealCountT;

	/** 充值数据-扣款金额(元)*/
	private String amountDeducted;
}
