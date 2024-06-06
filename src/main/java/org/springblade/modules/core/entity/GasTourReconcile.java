package org.springblade.modules.core.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import org.springblade.modules.core.dto.GasTourReconcileDto;

import java.util.Date;

/**
 * 交班对账对象 gas_tour_reconcile
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
public class GasTourReconcile {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 加气站id */
    private String gasId;

    /** 交班人 */
    private String tourPerson;

    /** 交班时间 */
    private String tourTime;

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

    /** 收款渠道汇总 */
    private String collectionChannelSummary;

    /** 枪号汇总 */
    private String gunNumberSummary;

    /** 班组汇总 */
    private String groupSummary;

    /** 车队汇总 */
    private String fleetSummary;

    /** 单价汇总 */
    private String unitPriceSummary;

    /** 库存 */
    private String inventory;

    /** 班组长签字 */
    private String leaderSignature;

    /** 值班站长签字 */
    private String agentSignature;

    /** 创建者 */
    private String createUser;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改人 */
	private String updateUser;

	/** 更新时间 */
	private Date UpdateTime;

    /** 是否刪除 */
    private Long isDeleted;

    public GasTourReconcile(){

    }

    public GasTourReconcile(GasTourReconcileDto dto){
        this.id = dto.getId();
        this.gasId = dto.getGasId();
        this.tourPerson = dto.getTourPerson();
        this.tourTime = dto.getTourTime();
        this.addLiquidMeasureCount = dto.getAddLiquidMeasureCount();
        this.amountCount = dto.getAmountCount();
        this.amountReceivable = dto.getAmountReceivable();
        this.fundsReceived = dto.getFundsReceived();
        this.dealCount = dto.getDealCount();
        this.collectionChannelSummary = JSONObject.toJSONString(dto.getCollectionChannelSummaryList());
        this.gunNumberSummary = JSONObject.toJSONString(dto.getGunNumberSummaryList());
        this.groupSummary = JSONObject.toJSONString(dto.getGroupSummaryList());
        this.fleetSummary = JSONObject.toJSONString(dto.getFleetSummaryList());
        this.unitPriceSummary = JSONObject.toJSONString(dto.getUnitPriceSummaryList());
        this.inventory = dto.getInventory();
        this.leaderSignature = dto.getLeaderSignature();
        this.agentSignature = dto.getAgentSignature();
        this.createUser = dto.getCreateUser();
        this.updateUser = dto.getUpdateUser();
        this.isDeleted = dto.getIsDeleted();
    }
}
