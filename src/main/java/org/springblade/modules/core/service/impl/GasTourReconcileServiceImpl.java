package org.springblade.modules.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.dto.tour.GasTourReconcileSaveDto;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.mapper.GasTourReconcileMapper;
import org.springblade.modules.core.service.GasTourReconcileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 交班对账Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class GasTourReconcileServiceImpl extends ServiceImpl<GasTourReconcileMapper,GasTourReconcile> implements GasTourReconcileService {
    @Autowired
    private GasTourReconcileMapper gasTourReconcileMapper;

    /**
     * 查询交班对账
     *
     * @param id 交班对账主键
     * @return 交班对账
     */
    @Override
    public GasTourReconcileSaveDto selectGasTourReconcileById(Long id)
    {

		GasTourReconcile gasTourReconcile = gasTourReconcileMapper.selectGasTourReconcileById(id);
		ObjectMapper mapper = new ObjectMapper();
		List<CollectionChannelSummary> collectionChannelSummaries = null;
		List<GunNumberSummary> gunNumberSummaryList = null;
		List<GroupSummary> groupSummaryList = null;
		List<FleetSummary> fleetSummaryList = null;
		List<UnitPriceSummary> unitPriceSummaryList = null;
		try {
			collectionChannelSummaries = mapper.readValue(gasTourReconcile.getCollectionChannelSummary(), new TypeReference<List<CollectionChannelSummary>>() {});
			gunNumberSummaryList = mapper.readValue(gasTourReconcile.getGunNumberSummary(), new TypeReference<List<GunNumberSummary>>() {});
			groupSummaryList = mapper.readValue(gasTourReconcile.getGroupSummary(), new TypeReference<List<GroupSummary>>() {});
			fleetSummaryList = mapper.readValue(gasTourReconcile.getFleetSummary(), new TypeReference<List<FleetSummary>>() {});
			unitPriceSummaryList = mapper.readValue(gasTourReconcile.getUnitPriceSummary(), new TypeReference<List<UnitPriceSummary>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		TourDealSummary tourDealSummary = new TourDealSummary();
		tourDealSummary.setAddLiquidMeasureCount(gasTourReconcile.getAddLiquidMeasureCount());
		tourDealSummary.setAmountCount(gasTourReconcile.getAmountCount());
		tourDealSummary.setAmountReceivable(gasTourReconcile.getAmountReceivable());
		tourDealSummary.setFundsReceived(gasTourReconcile.getFundsReceived());
		tourDealSummary.setDealCount(gasTourReconcile.getDealCount());

		TourPaySummary tourPaySummary = new TourPaySummary();
		tourPaySummary.setTotalRechargeAmount(gasTourReconcile.getTotalRechargeAmount());
		tourPaySummary.setAmountReceivableT(gasTourReconcile.getAmountReceivableT());
		tourPaySummary.setFundsReceivedT(gasTourReconcile.getFundsReceivedT());
		tourPaySummary.setDealCountT(gasTourReconcile.getDealCountT());
		tourPaySummary.setAmountDeducted(gasTourReconcile.getAmountDeducted());

		TourManageSummary tourManageSummary = new TourManageSummary();
		tourManageSummary.setInventory(gasTourReconcile.getInventory());
		tourManageSummary.setLeaderSignature(gasTourReconcile.getLeaderSignature());
		tourManageSummary.setAgentSignature(gasTourReconcile.getAgentSignature());

		return new GasTourReconcileSaveDto(gasTourReconcile, tourDealSummary, tourPaySummary, collectionChannelSummaries,
			gunNumberSummaryList, groupSummaryList, fleetSummaryList, unitPriceSummaryList, tourManageSummary);
    }

    /**
     * 查询交班对账列表
     *
     * @param gasTourReconcile 交班对账
     * @return 交班对账
     */
    @Override
    public IPage<GasTourReconcile> selectGasTourReconcileList(IPage<GasTourReconcile> page, GasTourReconcile gasTourReconcile)
    {
        IPage<GasTourReconcile> gasTourReconciles = page.setRecords(gasTourReconcileMapper.selectGasTourReconcileList(page,gasTourReconcile));
		for (GasTourReconcile record : gasTourReconciles.getRecords()) {
			record.setTourTime(record.getStarTourTime() + "至" + record.getEndTourTime());
		}
        return gasTourReconciles;
    }

    /**
     * 新增交班对账
     *
     * @param dto 交班对账
     * @return 结果
     */
    @Override
    public int insertGasTourReconcile(GasTourReconcileSaveDto dto) {
        GasTourReconcile gasTourReconcile = new GasTourReconcile(dto);
        gasTourReconcile.setCreateTime(new Date());
        return gasTourReconcileMapper.insertGasTourReconcile(gasTourReconcile);
    }

    /**
     * 修改交班对账
     *
     * @param dto 交班对账
     * @return 结果
     */
    @Override
    public int updateGasTourReconcile(GasTourReconcileSaveDto dto)
    {
        GasTourReconcile gasTourReconcile = new GasTourReconcile(dto);
        gasTourReconcile.setUpdateTime(new Date());
        return gasTourReconcileMapper.updateGasTourReconcile(gasTourReconcile);
    }

    /**
     * 删除交班对账信息
     *
     * @param id 交班对账主键
     * @return 结果
     */
    @Override
    public int deleteGasTourReconcileById(String id)
    {
        return gasTourReconcileMapper.deleteGasTourReconcileById(id);
    }

	@Override
	public List<GasTourReconcileExcelDto> selectAllGasTourReconcileList() {
		List<GasTourReconcile> gasTourReconcileExcelDtos = gasTourReconcileMapper.selectAllGasTourReconcileList();
		List<GasTourReconcileExcelDto> dtos = new ArrayList<>();
		gasTourReconcileExcelDtos.forEach(gas -> {
			try {
				String modeOfPayment = "";
				String paymentAmount = "";
				String gunMark = "";
				String amountOfLiquidAdded = "";
				String amountOfLiquidFilling = "";
				String frequency = "";
				String classNumber = "";
				String frequencyT = "";
				String amountOfLiquidAddedT = "";
				String amountOfLiquidFillingT = "";
				String nameOfFleet = "";
				String amountOfLiquidFillingTH = "";
				String amountOfLiquidAddedTH = "";
				String rechargeAmount = "";
				String remainingSum = "";
				String fleetRemainingSum = "";
				String symbolName = "";
				String stickerPrice = "";
				String weight = "";
				String amountOfReceipt = "";
				String amountPaid = "";
				String frequencyTH = "";
				ObjectMapper mapper = new ObjectMapper();
				List<CollectionChannelSummary> collectionChannelSummaries = mapper.readValue(gas.getCollectionChannelSummary(), new TypeReference<List<CollectionChannelSummary>>() {});
				List<GunNumberSummary> gunNumberSummaryList = mapper.readValue(gas.getGunNumberSummary(), new TypeReference<List<GunNumberSummary>>() {});
				List<GroupSummary> groupSummaryList = mapper.readValue(gas.getGroupSummary(), new TypeReference<List<GroupSummary>>() {});
				List<FleetSummary> fleetSummaryList = mapper.readValue(gas.getFleetSummary(), new TypeReference<List<FleetSummary>>() {});
				List<UnitPriceSummary> unitPriceSummaryList = mapper.readValue(gas.getUnitPriceSummary(), new TypeReference<List<UnitPriceSummary>>() {});
				for (CollectionChannelSummary collectionChannelSummary : collectionChannelSummaries) {
					modeOfPayment = modeOfPayment + collectionChannelSummary.getModeOfPayment() + "\n";
					paymentAmount = paymentAmount + collectionChannelSummary.getPaymentAmount() + "\n";
				}

				for (GunNumberSummary gunNumberSummary : gunNumberSummaryList) {
					gunMark = gunMark + gunNumberSummary.getGunMark() + "\n";
					amountOfLiquidAdded = amountOfLiquidAdded + gunNumberSummary.getAmountOfLiquidAdded() + "\n";
					amountOfLiquidFilling = amountOfLiquidFilling + gunNumberSummary.getAmountOfLiquidFilling() + "\n";
					frequency = frequency + gunNumberSummary.getFrequency() + "\n";
				}

				for (GroupSummary groupSummary : groupSummaryList) {
					classNumber = classNumber + groupSummary.getClassNumber() + "\n";
					frequencyT = frequencyT + groupSummary.getFrequency() + "\n";
					amountOfLiquidAddedT = amountOfLiquidAddedT + groupSummary.getAmountOfLiquidAdded() + "\n";
					amountOfLiquidFillingT = amountOfLiquidFillingT + groupSummary.getAmountOfLiquidFilling() + "\n";
				}

				for (FleetSummary fleetSummary : fleetSummaryList) {
					nameOfFleet = nameOfFleet + fleetSummary.getNameOfFleet() + "\n";
					amountOfLiquidFillingTH = amountOfLiquidFillingTH + fleetSummary.getAmountOfLiquidFilling() + "\n";
					amountOfLiquidAddedTH = amountOfLiquidAddedTH + fleetSummary.getAmountOfLiquidAdded() + "\n";
					rechargeAmount = rechargeAmount + fleetSummary.getRechargeAmount() + "\n";
					remainingSum = remainingSum + fleetSummary.getRemainingSum() + "\n";
					fleetRemainingSum = fleetRemainingSum + fleetSummary.getFleetRemainingSum() + "\n";
				}

				for (UnitPriceSummary unitPriceSummary : unitPriceSummaryList) {
					symbolName = symbolName + unitPriceSummary.getSymbolName() + "\n";
					stickerPrice = stickerPrice + unitPriceSummary.getStickerPrice() + "\n";
					weight = weight + unitPriceSummary.getWeight() + "\n";
					amountOfReceipt = amountOfReceipt + unitPriceSummary.getAmountOfReceipt() + "\n";
					amountPaid = amountPaid + unitPriceSummary.getAmountPaid() + "\n";
					frequencyTH = frequencyTH + unitPriceSummary.getFrequency() + "\n";
				}
				GasTourReconcileExcelDto gasTourReconcileDto = new GasTourReconcileExcelDto(gas,modeOfPayment,paymentAmount,
					gunMark,amountOfLiquidAdded,amountOfLiquidFilling,frequency,classNumber,frequencyT,amountOfLiquidAddedT,
					amountOfLiquidFillingT,nameOfFleet,amountOfLiquidFillingTH,amountOfLiquidAddedTH,rechargeAmount,remainingSum,
					fleetRemainingSum,symbolName,stickerPrice,weight,amountOfReceipt,amountPaid,frequencyTH);
				dtos.add(gasTourReconcileDto);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
		return dtos;
	}
}
