package org.springblade.modules.core.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;

import java.util.List;

@Data
public class GasTourReconcileDto {
    private static final long serialVersionUID = 1L;

    /** id */
    @ExcelIgnore
    private Long id;

    /** 加气站id */
	@ExcelIgnore
    private String gasId;

	/** 加气站名称 */
	@ExcelProperty("加气站名称")
	private String gasName;

    /** 交班人 */
	@ExcelProperty("交班人")
    private String tourPerson;

    /** 交班时间 */
	@ExcelProperty("交班时间")
    private String tourTime;

	/** 交班时间-开始 */
	@ExcelIgnore
	private String starTourTime;
	/** 交班时间-结束 */
	@ExcelIgnore
	private String endTourTime;

    /** 总加液量(公斤) */
	@ExcelProperty("总加液量(公斤)")
    private String addLiquidMeasureCount;

    /** 总金额(元) */
	@ExcelProperty("总金额(元)")
    private String amountCount;

    /** 应收金额(元) */
	@ExcelProperty("应收金额(元)")
    private String amountReceivable;

    /** 实收金额(元) */
	@ExcelProperty("实收金额(元)")
    private String fundsReceived;

    /** 总交易数(笔) */
	@ExcelProperty("总交易数(笔)")
    private String dealCount;

	/** 充值数据-总充值额(元)*/
	@ExcelProperty("充值数据-总充值额(元)")
	private String totalRechargeAmount;

	/** 充充值数据-应收额(元)*/
	@ExcelProperty("充值数据-应收额(元)")
	private String amountReceivableT;

	/** 充值数据-实收金额(元)*/
	@ExcelProperty("充值数据-实收金额(元)")
	private String fundsReceivedT;

	/** 充值数据-总交易数(笔)*/
	@ExcelProperty("充值数据-总交易数(笔)")
	private String dealCountT;

	/** 充值数据-扣款金额(元)*/
	@ExcelProperty("充值数据-扣款金额(元)")
	private String amountDeducted;

    /** 收款渠道汇总 */
	@ExcelProperty("收款渠道汇总")
	@ExcelIgnore
    private List<CollectionChannelSummary> collectionChannelSummaryList;

    /** 枪号汇总 */
	@ExcelProperty("枪号汇总")
	@ExcelIgnore
    private List<GunNumberSummary> gunNumberSummaryList;

    /** 班组汇总 */
	@ExcelProperty("班组汇总")
	@ExcelIgnore
    private List<GroupSummary> groupSummaryList;

    /** 车队汇总 */
	@ExcelProperty("车队汇总")
	@ExcelIgnore
    private List<FleetSummary> fleetSummaryList;

    /** 单价汇总 */
	@ExcelProperty("单价汇总")
	@ExcelIgnore
    private List<UnitPriceSummary> unitPriceSummaryList;

    /** 库存 */
	@ExcelProperty("库存")
    private String inventory;

    /** 班组长签字 */
	@ExcelProperty("班组长签字")
    private String leaderSignature;

    /** 值班站长签字 */
	@ExcelProperty("值班站长签字")
    private String agentSignature;

    /** 创建者 */
	@ExcelIgnore
    private String createUser;

    /** 最后修改人 */
	@ExcelIgnore
    private String updateUser;

    /** 是否刪除 */
	@ExcelIgnore
    private Long isDeleted;

    public GasTourReconcileDto(){}

    public GasTourReconcileDto(GasTourReconcile gasTourReconcile, List<CollectionChannelSummary> channelSummary,
							   List<GunNumberSummary> gunNumberSummary, List<GroupSummary> groupSummary,
							   List<FleetSummary> fleetSummary, List<UnitPriceSummary> unitPriceSummary){
        this.id = gasTourReconcile.getId();
        this.gasId = gasTourReconcile.getGasId();
        this.tourPerson = gasTourReconcile.getTourPerson();
        this.tourTime = gasTourReconcile.getStarTourTime() + "至" + gasTourReconcile.getEndTourTime();
        this.starTourTime = gasTourReconcile.getStarTourTime();
        this.endTourTime = gasTourReconcile.getEndTourTime();
        this.addLiquidMeasureCount = gasTourReconcile.getAddLiquidMeasureCount();
        this.amountCount = gasTourReconcile.getAmountCount();
        this.amountReceivable = gasTourReconcile.getAmountReceivable();
        this.fundsReceived = gasTourReconcile.getFundsReceived();
        this.dealCount = gasTourReconcile.getDealCount();
        this.totalRechargeAmount = gasTourReconcile.getTotalRechargeAmount();
        this.amountReceivableT = gasTourReconcile.getAmountReceivableT();
        this.fundsReceivedT = gasTourReconcile.getFundsReceivedT();
        this.dealCountT = gasTourReconcile.getDealCountT();
        this.amountDeducted = gasTourReconcile.getAmountDeducted();
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
