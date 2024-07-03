package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.dto.tour.GasTourReconcileSaveDto;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.entity.tour.*;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.service.GasBaseInfoService;
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
import java.util.Objects;

/**
 * 交班对账Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping(AppConstant.DEV_CODE + "/gas-reconcile")
@Api(value = "交班对账", tags = "交班对账接口")
public class GasTourReconcileController {

    @Autowired
    private GasTourReconcileService gasTourReconcileService;
    @Autowired
    private GasBaseInfoService gasBaseInfoService;

	/**
	 * 查询交班对账详情
	 */
	@GetMapping("/detail")
	public R selectById(@RequestParam("id") String id) {
		GasTourReconcileSaveDto gasTourReconcileDto = gasTourReconcileService.selectGasTourReconcileById(Long.valueOf(id));
		return R.data(gasTourReconcileDto);
	}

    /**
     * 查询交班对账列表
     */
    @PostMapping("/list")
    public R list(@RequestBody GasTourReconcile gasTourReconcile, @RequestBody Query query) {
		IPage<GasTourReconcile> list =
			gasTourReconcileService.selectGasTourReconcileList(Condition.getPage(query),gasTourReconcile);
        return R.data(list);
    }

    /**
     * 新增保存交班对账
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasTourReconcileSaveDto dto)
    {
        return R.data(gasTourReconcileService.insertGasTourReconcile(dto));
    }


    /**
     * 修改保存交班对账
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasTourReconcileSaveDto dto)
    {
        return R.data(gasTourReconcileService.updateGasTourReconcile(dto));
    }

    /**
     * 删除交班对账
     */
	@GetMapping( "/delete")
    public R delete(@RequestParam("ids") String ids) {
        return R.data(gasTourReconcileService.deleteGasTourReconcileById(ids));
    }

	/**
	 * 下载Excel模板
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "下载模板", httpMethod = "GET")
	public void downloadTemplate(HttpServletResponse response) {
		ExcelUtil.download(response, "交班对账.xlsx");
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
		if(Objects.isNull(list) || list.size() <= 0){
			return R.fail("请导入有效数据");
		}
		//需要将dto中的汇总数据转换成json格式后再进行保存
		list.forEach(dto -> {
			//收款渠道汇总 数据处理
			if (!StringUtils.isEmpty(dto.getModeOfPayment()) || !StringUtils.isEmpty(dto.getPaymentAmount())){
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
			}

			if (!StringUtils.isEmpty(dto.getGunMark()) || !StringUtils.isEmpty(dto.getAmountOfLiquidAdded()) ||
				!StringUtils.isEmpty(dto.getAmountOfLiquidFilling()) || !StringUtils.isEmpty(dto.getFrequency())){
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
			}

			if (!StringUtils.isEmpty(dto.getClassNumber()) || !StringUtils.isEmpty(dto.getFrequencyT()) ||
				!StringUtils.isEmpty(dto.getAmountOfLiquidAddedT()) || !StringUtils.isEmpty(dto.getAmountOfLiquidFillingT())){
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
			}

			if (!StringUtils.isEmpty(dto.getNameOfFleet()) || !StringUtils.isEmpty(dto.getAmountOfLiquidFillingTH()) ||
				!StringUtils.isEmpty(dto.getAmountOfLiquidAddedTH()) || !StringUtils.isEmpty(dto.getRechargeAmount()) ||
				!StringUtils.isEmpty(dto.getAmountOfLiquidAddedTH()) || !StringUtils.isEmpty(dto.getFleetRemainingSum())) {
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
			}

			if (!StringUtils.isEmpty(dto.getSymbolName()) || !StringUtils.isEmpty(dto.getStickerPrice()) ||
				!StringUtils.isEmpty(dto.getAmountOfLiquidAddedTH()) || !StringUtils.isEmpty(dto.getAmountOfReceipt()) ||
				!StringUtils.isEmpty(dto.getAmountOfLiquidAddedTH()) || !StringUtils.isEmpty(dto.getFrequencyTH())) {
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
			}

			dto.setGasId(gasBaseInfoService.selectIdByName(dto.getGasName()));
			GasTourReconcile gasTourReconcile = new GasTourReconcile(dto);
			gasDeviceRecordList.add(gasTourReconcile);
		});
		return R.data(gasTourReconcileService.saveBatch(gasDeviceRecordList));
	}
}
