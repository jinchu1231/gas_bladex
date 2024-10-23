package org.springblade.modules.core.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GasTourReconcileExcelDto {
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
	@ColumnWidth(38)
	private String tourTime;
	/** 交班时间-开始 */
	private String startTourTime;
	/** 交班时间-结束 */
	private String endTourTime;

	/** 总加液量(公斤) */
	@ExcelProperty("总加液量(公斤)")
	private double addLiquidMeasureCount;

	/** 总金额(元) */
	@ExcelProperty("总金额(元)")
	private BigDecimal amountCount;

	/** 应收金额(元) */
	@ExcelProperty("应收金额(元)")
	private BigDecimal amountReceivable;

	/** 实收金额(元) */
	@ExcelProperty("实收金额(元)")
	private BigDecimal fundsReceived;

	/** 总交易数(笔) */
	@ExcelProperty("总交易数(笔)")
	private int dealCount;

	/** 充值数据-总充值额(元)*/
	@ExcelProperty("充值数据-总充值额(元)")
	private BigDecimal totalRechargeAmount;

	/** 充充值数据-应收额(元)*/
	@ExcelProperty("充值数据-应收额(元)")
	private BigDecimal amountReceivableT;

	/** 充值数据-实收金额(元)*/
	@ExcelProperty("充值数据-实收金额(元)")
	private BigDecimal fundsReceivedT;

	/** 充值数据-总交易数(笔)*/
	@ExcelProperty("充值数据-总交易数(笔)")
	private int dealCountT;

	/** 充值数据-扣款金额(元)*/
	@ExcelProperty("充值数据-扣款金额(元)")
	private BigDecimal amountDeducted;

	/** 收款渠道汇总 */
	@ExcelProperty("收款渠道汇总")
	@ExcelIgnore
	private List<CollectionChannelSummary> collectionChannelSummaryList;

	//支付方式
	@ExcelProperty({"收款渠道","支付方式"})
	@ColumnWidth(15)
	private String modeOfPayment;
	//支付金额
	@ExcelProperty({"收款渠道","支付金额"})
	@ColumnWidth(15)
	private String paymentAmount;

	/** 枪号汇总 */
	@ExcelProperty("枪号汇总")
	@ExcelIgnore
	private List<GunNumberSummary> gunNumberSummaryList;

	//枪号
	@ExcelProperty({"枪号汇总","枪号"})
	private String gunMark;
	//加液量
	@ExcelProperty({"枪号汇总","加液量"})
	private String amountOfLiquidAdded;
	//加液金额
	@ExcelProperty({"枪号汇总","加液金额"})
	private String amountOfLiquidFilling;
	//总交易笔数
	@ExcelProperty({"枪号汇总","总交易笔数"})
	private String frequency;


	/** 班组汇总 */
	@ExcelProperty("班组汇总")
	@ExcelIgnore
	private List<GroupSummary> groupSummaryList;

	//班组号
	@ExcelProperty({"班组汇总","班组号"})
	private String classNumber;
	//交易笔数
	@ExcelProperty({"班组汇总","交易笔数"})
	private String frequencyT;
	//加液量(公斤)
	@ExcelProperty({"班组汇总","加液量(公斤)"})
	private String amountOfLiquidAddedT;
	//加液金额(元)
	@ExcelProperty({"班组汇总","加液金额(元)"})
	private String amountOfLiquidFillingT;

	/** 车队汇总 */
	@ExcelProperty("车队汇总")
	@ExcelIgnore
	private List<FleetSummary> fleetSummaryList;

	//车队名称
	@ExcelProperty({"车队汇总","车队名称"})
	@ColumnWidth(30)
	private String nameOfFleet;
	//车-加液金额(元)
	@ExcelProperty({"车队汇总","车-加液金额(元)"})
	private String amountOfLiquidFillingTH;
	//车-加液量(公斤)
	@ExcelProperty({"车队汇总","车-加液量(公斤)"})
	private String amountOfLiquidAddedTH;
	//充值金额(元)
	@ExcelProperty({"车队汇总","充值金额(元)"})
	private String rechargeAmount;
	//电子账户当前余额
	@ExcelProperty({"车队汇总","电子账户当前余额"})
	private String remainingSum;
	//车队卡片余额
	@ExcelProperty({"车队汇总","车队卡片余额"})
	private String fleetRemainingSum;

	/** 单价汇总 */
	@ExcelProperty("单价汇总")
	@ExcelIgnore
	private List<UnitPriceSummary> unitPriceSummaryList;

	//燃料名称
	@ExcelProperty({"单价汇总","燃料名称"})
	private String symbolName;
	//挂牌价(元)
	@ExcelProperty({"单价汇总","挂牌价(元)"})
	private String stickerPrice;
	//重量(KG)
	@ExcelProperty({"单价汇总","重量(KG)"})
	private String weight;
	//小票金额(元)
	@ExcelProperty({"单价汇总","小票金额(元)"})
	private String amountOfReceipt;
	//实付金额(元)
	@ExcelProperty({"单价汇总","实付金额(元)"})
	private String amountPaid;
	//记录笔数
	@ExcelProperty({"单价汇总","记录笔数"})
	private String frequencyTH;

	/** 库存 */
	@ExcelProperty("库存")
	private double inventory;

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

	public GasTourReconcileExcelDto(){}

	public GasTourReconcileExcelDto(GasTourReconcile gasTourReconcile,String modeOfPayment, String paymentAmount,
									String gunMark, String amountOfLiquidAdded, String amountOfLiquidFilling,
									String frequency, String classNumber, String frequencyT,
									String amountOfLiquidAddedT, String amountOfLiquidFillingT, String nameOfFleet,
									String amountOfLiquidFillingTH, String amountOfLiquidAddedTH,
									String rechargeAmount, String remainingSum, String fleetRemainingSum,
									String symbolName, String stickerPrice, String weight, String amountOfReceipt,
									String amountPaid, String frequencyTH){
		this.id = gasTourReconcile.getId();
		this.gasId = gasTourReconcile.getGasId();
		this.tourPerson = gasTourReconcile.getTourPerson();
		this.tourTime = gasTourReconcile.getTourTime();
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
		this.modeOfPayment = modeOfPayment;
		this.paymentAmount = paymentAmount;
		this.gunMark = gunMark;
		this.amountOfLiquidAdded = amountOfLiquidAdded;
		this.amountOfLiquidFilling = amountOfLiquidFilling;
		this.frequency = frequency;
		this.classNumber = classNumber;
		this.frequencyT = frequencyT;
		this.amountOfLiquidAddedT = amountOfLiquidAddedT;
		this.amountOfLiquidFillingT = amountOfLiquidFillingT;
		this.nameOfFleet = nameOfFleet;
		this.amountOfLiquidFillingTH = amountOfLiquidFillingTH;
		this.amountOfLiquidAddedTH = amountOfLiquidAddedTH;
		this.rechargeAmount = rechargeAmount;
		this.remainingSum = remainingSum;
		this.fleetRemainingSum = fleetRemainingSum;
		this.symbolName = symbolName;
		this.stickerPrice = stickerPrice;
		this.weight = weight;
		this.amountOfReceipt = amountOfReceipt;
		this.amountPaid = amountPaid;
		this.frequencyTH = frequencyTH;
		this.inventory = gasTourReconcile.getInventory();
		this.leaderSignature = gasTourReconcile.getLeaderSignature();
		this.agentSignature = gasTourReconcile.getAgentSignature();
	}
}
