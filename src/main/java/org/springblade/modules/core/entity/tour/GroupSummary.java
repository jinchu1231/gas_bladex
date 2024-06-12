package org.springblade.modules.core.entity.tour;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class GroupSummary {
    //班组号
	@ExcelProperty("班组号")
    private String classNumber;
    //交易笔数
	@ExcelProperty("交易笔数")
    private String frequency;
    //加液量(公斤)
	@ExcelProperty("加液量(公斤)")
    private String amountOfLiquidAdded;
    //加液金额(元)
	@ExcelProperty("加液金额(元)")
    private String amountOfLiquidFilling;
}
