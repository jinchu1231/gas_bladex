package org.springblade.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.service.GasTourReconcileService;
import org.springblade.modules.core.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 交班对账Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/gas-reconcile")
@Api(value = "交班对账", tags = "交班对账接口")
public class GasTourReconcileController {

    @Autowired
    private GasTourReconcileService gasTourReconcileService;

    /**
     * 查询交班对账列表
     */
    @PostMapping("/list")
    public R list(@RequestBody @Valid GasTourReconcile gasTourReconcile,
				  @Valid @RequestBody Query query)
    {
		List<GasTourReconcileDto> list =
			gasTourReconcileService.selectGasTourReconcileList(Condition.getPage(query),gasTourReconcile);
        return R.data(list);
    }

    /**
     * 新增保存交班对账
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasTourReconcileDto dto)
    {
        return R.data(gasTourReconcileService.insertGasTourReconcile(dto));
    }


    /**
     * 修改保存交班对账
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasTourReconcileDto dto)
    {
        return R.data(gasTourReconcileService.updateGasTourReconcile(dto));
    }

    /**
     * 删除交班对账
     */
    @PostMapping( "/remove")
    public R remove(String ids)
    {
        return R.data(gasTourReconcileService.deleteGasTourReconcileByIds(ids));
    }

	/**
	 * 下载Excel模板
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "下载模板", httpMethod = "GET")
	public void downloadTemplate(HttpServletResponse response) {
		ExcelUtil.download(response, "temp" + File.separator + "交班对账.xlsx");
	}

	/**
	 * 导出交班对账列表
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) {
		List<GasTourReconcileExcelDto> dtos = gasTourReconcileService.selectAllGasTourReconcileList();
		ExcelUtil.export(response, "交班对账", "交班对账数据表", dtos, GasTourReconcileExcelDto.class);
	}

	@PostMapping("write-notice")
	public R writeNotice(MultipartFile file) {
		List<GasTourReconcileExcelDto> list = ExcelUtil.read(file, GasTourReconcileExcelDto.class);

		List<GasTourReconcile> gasDeviceRecordList = new ArrayList<>();
		//需要将dto中的汇总数据转换成json格式后再进行保存
		list.forEach(dto -> {
			//收款渠道汇总 数据处理
			String[] split = dto.getModeOfPayment().split("\n");
			String[] split1 = dto.getPaymentAmount().split("\n");
			List<CollectionChannelSummary> channelSummaryList = new ArrayList<>();
			for (int i = 0; i < split.length; i++) {
				CollectionChannelSummary collectionChannelSummary = new CollectionChannelSummary();
				collectionChannelSummary.setModeOfPayment(split[i]);
				collectionChannelSummary.setPaymentAmount(split1[i]);
				channelSummaryList.add(collectionChannelSummary);
			}
			dto.setCollectionChannelSummaryList(channelSummaryList);

			//枪号汇总 数据处理
			String[] gun = dto.getGunMark().split("\n");
			String[] amount = dto.getAmountOfLiquidAdded().split("\n");
			String[] amountF = dto.getAmountOfLiquidFilling().split("\n");
			String[] fre = dto.getFrequency().split("\n");
			List<GunNumberSummary> gunNumberSummaryList = new ArrayList<>();
			for (int i = 0; i < gun.length; i++) {
				GunNumberSummary gunNumberSummary = new GunNumberSummary();
				gunNumberSummary.setGunMark(gun[i]);
				gunNumberSummary.setAmountOfLiquidAdded(amount[i]);
				gunNumberSummary.setAmountOfLiquidFilling(amountF[i]);
				gunNumberSummary.setFrequency(fre[i]);
				gunNumberSummaryList.add(gunNumberSummary);
			}
			dto.setGunNumberSummaryList(gunNumberSummaryList);

			//班组汇总 数据处理
			String[] classN = dto.getClassNumber().split("\n");
			String[] freq = dto.getFrequencyT().split("\n");
			String[] amountT = dto.getAmountOfLiquidAddedT().split("\n");
			String[] amountFT = dto.getAmountOfLiquidFillingT().split("\n");
			List<GroupSummary> groupSummaryList = new ArrayList<>();
			for (int i = 0; i < classN.length; i++) {
				GroupSummary groupSummary = new GroupSummary();
				groupSummary.setClassNumber(classN[i]);
				groupSummary.setFrequency(freq[i]);
				groupSummary.setAmountOfLiquidAdded(amountT[i]);
				groupSummary.setAmountOfLiquidFilling(amountFT[i]);
				groupSummaryList.add(groupSummary);
			}
			dto.setGroupSummaryList(groupSummaryList);

			//车队汇总 数据处理
			String[] name = dto.getNameOfFleet().split("\n");
			String[] amountFTh = dto.getAmountOfLiquidFillingTH().split("\n");
			String[] amountTh = dto.getAmountOfLiquidAddedTH().split("\n");
			String[] rec = dto.getRechargeAmount().split("\n");
			String[] rem = dto.getRemainingSum().split("\n");
			String[] fle = dto.getFleetRemainingSum().split("\n");
			List<FleetSummary> fleetSummaryList = new ArrayList<>();
			for (int i = 0; i < name.length; i++) {
				FleetSummary fleetSummary = new FleetSummary();
				fleetSummary.setNameOfFleet(name[i]);
				fleetSummary.setAmountOfLiquidFilling(amountFTh[i]);
				fleetSummary.setAmountOfLiquidAdded(amountTh[i]);
				fleetSummary.setRechargeAmount(rec[i]);
				fleetSummary.setRemainingSum(rem[i]);
				fleetSummary.setFleetRemainingSum(fle[i]);
				fleetSummaryList.add(fleetSummary);
			}
			dto.setFleetSummaryList(fleetSummaryList);

			//单价汇总 数据处理
			String[] sym = dto.getSymbolName().split("\n");
			String[] sti = dto.getStickerPrice().split("\n");
			String[] wei = dto.getWeight().split("\n");
			String[] amountR = dto.getAmountOfReceipt().split("\n");
			String[] amountP = dto.getAmountPaid().split("\n");
			String[] freTH = dto.getFrequencyTH().split("\n");
			List<UnitPriceSummary> unitPriceSummaryList = new ArrayList<>();
			for (int i = 0; i < sym.length; i++) {
				UnitPriceSummary unitPriceSummary = new UnitPriceSummary();
				unitPriceSummary.setSymbolName(sym[i]);
				unitPriceSummary.setStickerPrice(sti[i]);
				unitPriceSummary.setWeight(wei[i]);
				unitPriceSummary.setAmountOfReceipt(amountR[i]);
				unitPriceSummary.setAmountPaid(amountP[i]);
				unitPriceSummary.setFrequency(freTH[i]);
				unitPriceSummaryList.add(unitPriceSummary);
			}
			dto.setUnitPriceSummaryList(unitPriceSummaryList);

			GasTourReconcile gasTourReconcile = new GasTourReconcile(dto);
			gasDeviceRecordList.add(gasTourReconcile);
		});
		return R.data(gasTourReconcileService.saveBatch(gasDeviceRecordList));
	}
}
