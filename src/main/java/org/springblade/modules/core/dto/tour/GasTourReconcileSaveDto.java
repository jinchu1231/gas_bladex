package org.springblade.modules.core.dto.tour;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;

import java.util.List;

@Data
public class GasTourReconcileSaveDto {
    private static final long serialVersionUID = 1L;

	/** id */
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
	private String starTourTime;

	/** 交班时间-结束 */
	private String endTourTime;

	/** 交易汇总 */
	private TourDealSummary tourDealSummary;

	/** 充值汇总 */
	private TourPaySummary tourPaySummary;

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

    /** 管理汇总 */
    private TourManageSummary tourManageSummary;

	public GasTourReconcileSaveDto(){}

	public GasTourReconcileSaveDto(GasTourReconcile gasTourReconcile,TourDealSummary tourDealSummary,
								   TourPaySummary tourPaySummary, List<CollectionChannelSummary> channelSummary,
							   List<GunNumberSummary> gunNumberSummary, List<GroupSummary> groupSummary,
							   List<FleetSummary> fleetSummary, List<UnitPriceSummary> unitPriceSummary,
								   TourManageSummary tourManageSummary){
		this.id = gasTourReconcile.getId();
		this.gasId = gasTourReconcile.getGasId();
		this.tourPerson = gasTourReconcile.getTourPerson();
		this.tourTime = gasTourReconcile.getStarTourTime() + "至" + gasTourReconcile.getEndTourTime();
		this.starTourTime = gasTourReconcile.getStarTourTime();
		this.endTourTime = gasTourReconcile.getEndTourTime();
		this.tourDealSummary = tourDealSummary;
		this.tourPaySummary = tourPaySummary;
		this.collectionChannelSummaryList = channelSummary;
		this.gunNumberSummaryList = gunNumberSummary;
		this.groupSummaryList = groupSummary;
		this.fleetSummaryList = fleetSummary;
		this.unitPriceSummaryList = unitPriceSummary;
		this.tourManageSummary = tourManageSummary;
	}

}
