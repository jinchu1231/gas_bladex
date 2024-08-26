package org.springblade.modules.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spire.xls.Workbook;
import org.json.JSONArray;
import org.springblade.common.utils.FindAndReplaceData;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.dto.dapin.DayPriceDto;
import org.springblade.modules.core.dto.dapin.PriceServerTrendDto;
import org.springblade.modules.core.dto.dapin.StoredValueDto;
import org.springblade.modules.core.dto.tour.GasTourReconcileSaveDto;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.mapper.GasTourReconcileMapper;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springblade.modules.core.service.GasTourReconcileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
	@Autowired
	private GasBaseInfoService gasBaseInfoService;

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
			record.setTourTime(record.getStartTourTime() + "至" + record.getEndTourTime());
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


	@Override
	public GasTourReconcileExcelDto writeNotice(MultipartFile file) {
		Workbook workbook = new Workbook();
		try {
			// 创建一个临时文件
			File tempFile = File.createTempFile("temp", ".xlsx");
			tempFile.deleteOnExit(); // 确保程序结束后删除临时文件

			// 将 MultipartFile 的内容写入临时文件
			try (InputStream inputStream = file.getInputStream();
				 OutputStream outputStream = new FileOutputStream(tempFile)) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			}
			// 使用 Spire.XLS 加载临时文件
			workbook.loadFromFile(tempFile.getAbsolutePath());
		}catch (IOException e) {
			e.printStackTrace();
		}

		GasTourReconcileExcelDto dto = new GasTourReconcileExcelDto();

		ArrayList<String[]> strings = FindAndReplaceData.readRows(workbook, 0);
		for (int i = 0; i < strings.size(); i++) {
			String[] string = strings.get(i);
//			System.out.println("第一层数据打印：" + JSON.toJSONString(string));
			for (int j = 0; j < string.length; j++) {
				if ("站点".equals(string[j])) {
					dto.setGasName(string[j + 1]);
					dto.setGasId(gasBaseInfoService.selectIdByName(dto.getGasName()));
				}
				if ("交班人".equals(string[j])){
					dto.setTourPerson(string[j + 1]);
				}
				if ("时间".equals(string[j])){
					String[] split = string[j + 1].split("至");
					dto.setStartTourTime(split[0]);
					dto.setEndTourTime(split[1]);
				}
				if ("总加液量(公斤)".equals(string[j])){
					dto.setAddLiquidMeasureCount(string[j + 1]);
				}
				if ("总金额(元)".equals(string[j])){
					dto.setAmountCount(string[j + 1]);
				}
				if ("应收金额(元)".equals(string[j])){
					dto.setAmountReceivable(string[j + 1]);
				}
				if (i == 3 && "实收金额(元)".equals(string[j])){
					dto.setFundsReceived(string[j + 1]);
				}
				if (i == 3 && "总交易数(笔)".equals(string[j])){
					dto.setDealCount(string[j + 1]);
				}

				if ("总充值额(元)".equals(string[j])){
					dto.setTotalRechargeAmount(string[j + 1]);
				}
				if ("应收额(元)".equals(string[j])){
					dto.setAmountReceivableT(string[j + 1]);
				}
				if (i == 5 && "实收金额(元)".equals(string[j])){
					dto.setFundsReceivedT(string[j + 1]);
				}
				if (i == 5 && "总交易数(笔)".equals(string[j])){
					dto.setDealCountT(string[j + 1]);
				}
				if ("扣款金额(元)".equals(string[j])){
					dto.setAmountDeducted(string[j + 1]);
				}

				if (string[j].contains("收款渠道汇总")){
					List<CollectionChannelSummary> collectionChannelSummaryList = new ArrayList<>();

					for (int k = i + 2; k < strings.size(); k++) {
						if (strings.get(k)[0].contains("枪号汇总")){
							break;
						}
						CollectionChannelSummary collectionChannelSummary = new CollectionChannelSummary();
						collectionChannelSummary.setModeOfPayment(strings.get(k)[0]);
						collectionChannelSummary.setPaymentAmount(strings.get(k)[1]);
						collectionChannelSummaryList.add(collectionChannelSummary);
					}
					dto.setCollectionChannelSummaryList(collectionChannelSummaryList);
				}

				if (string[j].contains("枪号汇总")){
					List<GunNumberSummary> gunNumberSummaryList = new ArrayList<>();
					for (int k = i + 2; k < strings.size(); k++) {
						if (strings.get(k)[0].contains("班组汇总")){
							break;
						}
						GunNumberSummary gunNumberSummary = new GunNumberSummary();
						gunNumberSummary.setGunMark(strings.get(k)[0]);
						gunNumberSummary.setAmountOfLiquidAdded(strings.get(k)[1]);
						gunNumberSummary.setAmountOfLiquidFilling(strings.get(k)[2]);
						gunNumberSummary.setFrequency(strings.get(k)[3]);
						gunNumberSummaryList.add(gunNumberSummary);
					}
					dto.setGunNumberSummaryList(gunNumberSummaryList);
				}

				if (string[j].contains("班组汇总")){
					List<GroupSummary> groupSummaryList = new ArrayList<>();
					for (int k = i + 2; k < strings.size(); k++) {
						if (strings.get(k)[0].contains("车队汇总")){
							break;
						}
						GroupSummary groupSummary = new GroupSummary();
						groupSummary.setClassNumber(strings.get(k)[0]);
						groupSummary.setFrequency(strings.get(k)[1]);
						groupSummary.setAmountOfLiquidAdded(strings.get(k)[2]);
						groupSummary.setAmountOfLiquidFilling(strings.get(k)[3]);
						groupSummaryList.add(groupSummary);
					}
					dto.setGroupSummaryList(groupSummaryList);
				}

				if (string[j].contains("车队汇总")){
					List<FleetSummary> fleetSummaryList = new ArrayList<>();
					for (int k = i + 2; k < strings.size(); k++) {
						if (strings.get(k)[0].contains("单价汇总")){
							break;
						}
						FleetSummary fleetSummary = new FleetSummary();
						fleetSummary.setNameOfFleet(strings.get(k)[0]);
						fleetSummary.setAmountOfLiquidFilling(strings.get(k)[1]);
						fleetSummary.setAmountOfLiquidAdded(strings.get(k)[2]);
						fleetSummary.setRechargeAmount(strings.get(k)[3]);
						fleetSummary.setRemainingSum(strings.get(k)[4]);
						fleetSummary.setFleetRemainingSum(strings.get(k)[5]);
						fleetSummaryList.add(fleetSummary);
					}
					dto.setFleetSummaryList(fleetSummaryList);
				}

				if (string[j].contains("单价汇总")){
					List<UnitPriceSummary> unitPriceSummaryList = new ArrayList<>();
					for (int k = i + 2; k < strings.size(); k++) {
						if (strings.get(k)[0].contains("库存")){
							break;
						}
						UnitPriceSummary unitPriceSummary = new UnitPriceSummary();
						unitPriceSummary.setSymbolName(strings.get(k)[0]);
						unitPriceSummary.setStickerPrice(strings.get(k)[1]);
						unitPriceSummary.setWeight(strings.get(k)[2]);
						unitPriceSummary.setAmountOfReceipt(strings.get(k)[3]);
						unitPriceSummary.setAmountPaid(strings.get(k)[4]);
						unitPriceSummary.setFrequency(strings.get(k)[5]);
						unitPriceSummaryList.add(unitPriceSummary);
					}
					dto.setUnitPriceSummaryList(unitPriceSummaryList);
				}

				if ("库存".equals(string[j])){
					dto.setInventory(string[j + 1]);
				}
				if ("班组长签字".equals(string[j])){
					dto.setLeaderSignature(string[j + 1]);
				}
				if ("值班站长签字".equals(string[j])){
					dto.setAgentSignature(string[j + 1]);
				}
//				System.out.println("第二次数据打印：" + string[j]);
			}
		}
		return dto;
	}

	@Override
	public PriceServerTrendDto revenueTrend(String id) {
		List<DayPriceDto> dayPriceDtos = baseMapper.revenueTrend(id);
		List<String> price = new ArrayList<>();
		List<String> day = new ArrayList<>();
		PriceServerTrendDto dto = new PriceServerTrendDto();
		dayPriceDtos.forEach(dtos -> {
			price.add(String.valueOf(dtos.getPrice()));
			day.add(dtos.getPriceDay());
		});
		dto.setPriceList(price);
		dto.setDateList(day);
		return dto;
	}

	@Override
	public PriceServerTrendDto inventoryTrend(String id) {
		List<DayPriceDto> dayPriceDtos = baseMapper.inventoryTrend(id);
		List<String> inventory = new ArrayList<>();
		List<String> day = new ArrayList<>();
		PriceServerTrendDto dto = new PriceServerTrendDto();
		dayPriceDtos.forEach(dtos -> {
			inventory.add(dtos.getInventory());
			day.add(dtos.getPriceDay());
		});
		dto.setPriceList(inventory);
		dto.setDateList(day);
		return dto;
	}

	@Override
	public PriceServerTrendDto allRevenueTrend(String type) {
		PriceServerTrendDto priceServerTrendDto = new PriceServerTrendDto();
		List<StoredValueDto> storedValueDtos;
		YearMonth currentMonth = YearMonth.now();
		// 获取当前月份第一天的日期
		String firstDayOfMonth = currentMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		if (type.equals("1")){
			//本月
			YearMonth nextMonth = YearMonth.now().plusMonths(1);
			String firstDayOfNextMonth =  nextMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
			storedValueDtos = baseMapper.allRevenueTrend(firstDayOfMonth, firstDayOfNextMonth);
		}else if (type.equals("2")){
			//上月
			YearMonth lastMonth = YearMonth.now().minusMonths(1);
			String firstDayOfLastMonth = lastMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
			storedValueDtos = baseMapper.allRevenueTrend(firstDayOfLastMonth, firstDayOfMonth);
		}else {
			//今年
			LocalDate today = LocalDate.now();
			String todayAsString = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
			LocalDate firstDayOfYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
			String firstDayOfYearAsString = firstDayOfYear.format(DateTimeFormatter.ISO_LOCAL_DATE);
			storedValueDtos = baseMapper.allRevenueTrendYear(firstDayOfYearAsString, todayAsString);
		}
		List<String> inventory = new ArrayList<>();
		List<String> day = new ArrayList<>();
		storedValueDtos.forEach(dtos -> {
			inventory.add(dtos.getStoredValue());
			day.add(dtos.getPriceDay());
		});
		priceServerTrendDto.setPriceList(inventory);
		priceServerTrendDto.setDateList(day);
		return priceServerTrendDto;
	}

	@Override
	public PriceServerTrendDto allInventoryTrend(String type) {
		PriceServerTrendDto priceServerTrendDto = new PriceServerTrendDto();
		List<StoredValueDto> storedValueDtos = new ArrayList<>();
		YearMonth currentMonth = YearMonth.now();
		// 获取当前月份第一天的日期
		String firstDayOfMonth = currentMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		if (type.equals("1")){
			//本月
			YearMonth nextMonth = YearMonth.now().plusMonths(1);
			String firstDayOfNextMonth =  nextMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
			storedValueDtos = baseMapper.allInventoryTrend(firstDayOfMonth, firstDayOfNextMonth);
		}else if (type.equals("2")){
			//上月
			YearMonth lastMonth = YearMonth.now().minusMonths(1);
			String firstDayOfLastMonth = lastMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
			storedValueDtos = baseMapper.allInventoryTrend(firstDayOfLastMonth, firstDayOfMonth);
		}
		List<String> inventory = new ArrayList<>();
		List<String> day = new ArrayList<>();
		storedValueDtos.forEach(dtos -> {
			inventory.add(dtos.getStoredValue());
			day.add(dtos.getPriceDay());
		});
		priceServerTrendDto.setPriceList(inventory);
		priceServerTrendDto.setDateList(day);
		return priceServerTrendDto;
	}

	@Override
	public PriceServerTrendDto allStoredCalueTrend(String type) {
		List<StoredValueDto> storedValueList = new ArrayList<>();
		Map<LocalDate, Double> aggregatedData = new HashMap<>();
		YearMonth currentMonth = YearMonth.now();
		// 获取当前月份第一天的日期
		String firstDayOfMonth = currentMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate startDate = currentMonth.atDay(1);
		LocalDate endDate = currentMonth.atEndOfMonth();
		if (type.equals("1")){
			//本月
			YearMonth nextMonth = YearMonth.now().plusMonths(1);
			String firstDayOfNextMonth =  nextMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
			storedValueList = baseMapper.allStoredCalueTrend(firstDayOfMonth, firstDayOfNextMonth);
		}else if (type.equals("2")){
			//上月
			YearMonth lastMonth = YearMonth.now().minusMonths(1);
			startDate = lastMonth.atDay(1);
			endDate = lastMonth.atEndOfMonth();
			String firstDayOfLastMonth = lastMonth.atDay(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
			storedValueList = baseMapper.allStoredCalueTrend(firstDayOfLastMonth, firstDayOfMonth);
		}
		storedValueList.forEach(dto -> {
			LocalDate date = LocalDate.parse(dto.getPriceDay());
			Double sum = parseValues(dto.getStoredValue());

			aggregatedData.merge(date, sum, Double::sum);
		});

		fillMissingDates(aggregatedData, startDate, endDate);

		PriceServerTrendDto priceServerTrendDto = prepareTrendDto(aggregatedData);
		return priceServerTrendDto;
	}

	private Double parseValues(String storedValue) {
		// 将JSON字符串解析为JSONArray
		JSONArray jsonArray = new JSONArray(storedValue);
		// 初始化总和
		double sum = 0;
		// 遍历JSONArray，将每个元素转换为double并累加
		for (int i = 0; i < jsonArray.length(); i++) {
			sum += jsonArray.getDouble(i);
		}
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(sum));
	}


	private void fillMissingDates(Map<LocalDate, Double> aggregatedData, LocalDate start, LocalDate end) {
		LocalDate current = start;
		while (!current.isAfter(end)) {
			if (!aggregatedData.containsKey(current)) {
				aggregatedData.put(current, 0.0);
			}
			current = current.plusDays(1);
		}
	}

	private PriceServerTrendDto prepareTrendDto(Map<LocalDate, Double> aggregatedData) {
		PriceServerTrendDto trendDto = new PriceServerTrendDto();
		List<String> dateList = new ArrayList<>();
		List<String> priceList = new ArrayList<>();

		// Iterate over the map and prepare the final lists
		for (Map.Entry<LocalDate, Double> entry : aggregatedData.entrySet()) {
			dateList.add(entry.getKey().toString());
			priceList.add(String.valueOf(entry.getValue()));
		}

		Collections.reverse(dateList);
		Collections.reverse(priceList);
		trendDto.setDateList(dateList);
		trendDto.setPriceList(priceList);

		return trendDto;
	}

}
