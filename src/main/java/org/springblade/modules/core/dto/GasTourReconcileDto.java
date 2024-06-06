package org.springblade.modules.core.dto;

import lombok.Data;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;

import java.util.List;

@Data
public class GasTourReconcileDto {
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
    private List<CollectionChannelSummary> collectionChannelSummaryList;

    /** 枪号汇总 */
    private List<GunNumberSummary> gunNumberSummaryList;

    /** 班组汇总 */
    private List<GroupSummary> groupSummaryList;

    /** 车队汇总 */
    private List<FleetSummary> fleetSummaryList;

    /** 单价汇总 */
    private List<UnitPriceSummary> unitPriceSummaryList;

    /** 库存 */
    private String inventory;

    /** 班组长签字 */
    private String leaderSignature;

    /** 值班站长签字 */
    private String agentSignature;

    /** 创建者 */
    private String createUser;

    /** 最后修改人 */
    private String updateUser;

    /** 是否刪除 */
    private Long isDeleted;

    public GasTourReconcileDto(){}

    public GasTourReconcileDto(GasTourReconcile gasTourReconcile, List<CollectionChannelSummary> channelSummary,
							   List<GunNumberSummary> gunNumberSummary, List<GroupSummary> groupSummary,
							   List<FleetSummary> fleetSummary, List<UnitPriceSummary> unitPriceSummary){
        this.id = gasTourReconcile.getId();
        this.gasId = gasTourReconcile.getGasId();
        this.tourPerson = gasTourReconcile.getTourPerson();
        this.tourTime = gasTourReconcile.getTourTime();
        this.addLiquidMeasureCount = gasTourReconcile.getAddLiquidMeasureCount();
        this.amountCount = gasTourReconcile.getAmountCount();
        this.amountReceivable = gasTourReconcile.getAmountReceivable();
        this.fundsReceived = gasTourReconcile.getFundsReceived();
        this.dealCount = gasTourReconcile.getDealCount();
        this.collectionChannelSummaryList = channelSummary;
        this.gunNumberSummaryList = gunNumberSummary;
        this.groupSummaryList = groupSummary;
        this.fleetSummaryList = fleetSummary;
        this.unitPriceSummaryList = unitPriceSummary;
        this.inventory = gasTourReconcile.getInventory();
        this.leaderSignature = gasTourReconcile.getLeaderSignature();
        this.agentSignature = gasTourReconcile.getAgentSignature();
    }
}
