package org.springblade.modules.core.entity.tour;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class GunNumberSummary {
    //枪号
	@ExcelProperty("枪号")
    private String gunMark;
    //加液量
	@ExcelProperty("加液量")
    private String amountOfLiquidAdded;
    //加液金额
	@ExcelProperty("加液金额")
    private String amountOfLiquidFilling;
    //总交易笔数
	@ExcelProperty("总交易笔数")
    private String frequency;
}
