package org.springblade.modules.core.entity.tour;

import lombok.Data;

@Data
public class UnitPriceSummary {

    //燃料名称
    private String symbolName;
    //挂牌价(元)
    private String stickerPrice;
    //重量(KG)
    private String weight;
    //小票金额(元)
    private String amountOfReceipt;
    //实付金额(元)
    private String amountPaid;
    //记录笔数
    private String frequency;
}
