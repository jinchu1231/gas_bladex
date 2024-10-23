package org.springblade.modules.core.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.dto.tour.GasTourReconcileSaveDto;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;

import java.math.BigDecimal;
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
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 加气站id */
    private String gasId;

	/** 加气站名称 */
	private String gasName;

    /** 交班人 */
    private String tourPerson;

    /** 交班时间 */
    private String tourTime;
	/** 交班时间-开始 */
	private String startTourTime;
	/** 交班时间-结束 */
	private String endTourTime;

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

	/** 充值数据-总充值额(元)*/
	private BigDecimal totalRechargeAmount;

	/** 充充值数据-应收额(元)*/
	private BigDecimal amountReceivableT;

	/** 充值数据-实收金额(元)*/
	private BigDecimal fundsReceivedT;

	/** 充值数据-总交易数(笔)*/
	private int dealCountT;

	/** 充值数据-扣款金额(元)*/
	private BigDecimal amountDeducted;

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
    private double inventory;

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
		this.totalRechargeAmount = dto.getTotalRechargeAmount();
		this.amountReceivableT = dto.getAmountReceivableT();
		this.fundsReceivedT = dto.getFundsReceivedT();
		this.dealCountT = dto.getDealCountT();
		this.amountDeducted = dto.getAmountDeducted();
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

	public GasTourReconcile(GasTourReconcileExcelDto dto){
		this.id = dto.getId();
		this.gasId = dto.getGasId();
		this.gasName = dto.getGasName();
		this.tourPerson = dto.getTourPerson();
//		this.tourTime = dto.getTourTime();
		this.startTourTime = dto.getStartTourTime();
		this.endTourTime = dto.getEndTourTime();
		this.addLiquidMeasureCount = dto.getAddLiquidMeasureCount();
		this.amountCount = dto.getAmountCount();
		this.amountReceivable = dto.getAmountReceivable();
		this.fundsReceived = dto.getFundsReceived();
		this.dealCount = dto.getDealCount();
		this.totalRechargeAmount = dto.getTotalRechargeAmount();
		this.amountReceivableT = dto.getAmountReceivableT();
		this.fundsReceivedT = dto.getFundsReceivedT();
		this.dealCountT = dto.getDealCountT();
		this.amountDeducted = dto.getAmountDeducted();
		this.collectionChannelSummary = JSONObject.toJSONString(dto.getCollectionChannelSummaryList());
		this.gunNumberSummary = JSONObject.toJSONString(dto.getGunNumberSummaryList());
		this.groupSummary = JSONObject.toJSONString(dto.getGroupSummaryList());
		this.fleetSummary = JSONObject.toJSONString(dto.getFleetSummaryList());
		this.unitPriceSummary = JSONObject.toJSONString(dto.getUnitPriceSummaryList());
		this.inventory = dto.getInventory();
		this.leaderSignature = dto.getLeaderSignature();
		this.agentSignature = dto.getAgentSignature();
		this.createUser = AuthUtil.getUserId().toString();
		this.updateUser = dto.getUpdateUser();
		this.isDeleted = dto.getIsDeleted();
	}

	public GasTourReconcile(GasTourReconcileSaveDto dto){
		this.id = dto.getId();
		this.gasId = dto.getGasId();
		this.gasName = dto.getGasName();
		this.tourPerson = dto.getTourPerson();
		this.tourTime = dto.getTourTime();
		this.startTourTime = dto.getStartTourTime();
		this.endTourTime = dto.getEndTourTime();
		this.addLiquidMeasureCount = dto.getTourDealSummary().getAddLiquidMeasureCount();
		this.amountCount = dto.getTourDealSummary().getAmountCount();
		this.amountReceivable = dto.getTourDealSummary().getAmountReceivable();
		this.fundsReceived = dto.getTourDealSummary().getFundsReceived();
		this.dealCount = dto.getTourDealSummary().getDealCount();
		this.totalRechargeAmount = dto.getTourPaySummary().getTotalRechargeAmount();
		this.amountReceivableT = dto.getTourPaySummary().getAmountReceivableT();
		this.fundsReceivedT = dto.getTourPaySummary().getFundsReceivedT();
		this.dealCountT = dto.getTourPaySummary().getDealCountT();
		this.amountDeducted = dto.getTourPaySummary().getAmountDeducted();
		this.collectionChannelSummary = JSONObject.toJSONString(dto.getCollectionChannelSummaryList());
		this.gunNumberSummary = JSONObject.toJSONString(dto.getGunNumberSummaryList());
		this.groupSummary = JSONObject.toJSONString(dto.getGroupSummaryList());
		this.fleetSummary = JSONObject.toJSONString(dto.getFleetSummaryList());
		this.unitPriceSummary = JSONObject.toJSONString(dto.getUnitPriceSummaryList());
		this.inventory = dto.getTourManageSummary().getInventory();
		this.leaderSignature = dto.getTourManageSummary().getLeaderSignature();
		this.agentSignature = dto.getTourManageSummary().getAgentSignature();
	}
}
