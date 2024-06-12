package org.springblade.modules.core.entity.tour;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class FleetSummary {
    //车队名称
	@ExcelProperty("车队名称")
    private String nameOfFleet;
    //加液金额(元)
	@ExcelProperty("加液金额(元)")
    private String amountOfLiquidFilling;
    //加液量(公斤)
	@ExcelProperty("加液量(公斤)")
    private String amountOfLiquidAdded;
    //充值金额(元)
	@ExcelProperty("充值金额(元)")
    private String rechargeAmount;
    //电子账户当前余额
	@ExcelProperty("电子账户当前余额")
    private String remainingSum;
    //车队卡片余额
	@ExcelProperty("车队卡片余额")
    private String fleetRemainingSum;
}
