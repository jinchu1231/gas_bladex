package org.springblade.modules.core.entity.tour;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class CollectionChannelSummary {
    //支付方式
	@ExcelProperty("支付方式")
    private String modeOfPayment;

    //支付金额
	@ExcelProperty("支付金额")
    private String paymentAmount;
}
