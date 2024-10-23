package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.constant.GasManageConstant;
import org.springblade.modules.core.dto.tour.GasTourReconcileSaveDto;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springblade.modules.core.service.GasTourReconcileService;
import org.springblade.modules.core.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
    	if(!GasManageConstant.MANAGE_USERS.contains(AuthUtil.getUserName())){
			gasTourReconcile.setCreateUser(AuthUtil.getUserId().toString());
		}
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

	/**
	 * 导入
	 * @param file file
	 * @return boolean
	 */
	@SneakyThrows
	@PostMapping("write-notice")
	public R writeNotice(MultipartFile file) {
		GasTourReconcileExcelDto dto;
		try {
			dto = gasTourReconcileService.writeNotice(file);
		}catch (Exception e){
			return R.fail("上传失败");
		}
		gasTourReconcileService.save(new GasTourReconcile(dto));
		return R.success("上传成功");
	}

	/**
	 * 大屏首页-加气站营收趋势图
	 * 获取单个加气站信息
	 * @param id id
	 */
	@PostMapping("revenueTrend")
	public R revenueTrend(String id) {
		return R.data(gasTourReconcileService.revenueTrend(id));
	}

	/**
	 * 大屏首页-加气站库存趋势图
	 * 获取单个加气站信息
	 * @param id id
	 */
	@PostMapping("inventoryTrend")
	public R inventoryTrend(String id) {
		return R.data(gasTourReconcileService.inventoryTrend(id));
	}


	/**
	 * 大屏-加气站管理-加气站营收趋势图
	 * 获取所有加气站信息
	 * @param type 类型
	 */
	@PostMapping("allRevenueTrend")
	public R allRevenueTrend(@RequestParam("type") String type, @RequestParam("gasId") String gasId) {
		return R.data(gasTourReconcileService.allRevenueTrend(type, gasId));
	}

	/**
	 * 大屏-加气站管理-加气站库存趋势图
	 * 获取所有加气站信息
	 * @param type 类型
	 */
	@PostMapping("allInventoryTrend")
	public R allInventoryTrend(String type) {
		return R.data(gasTourReconcileService.allInventoryTrend(type));
	}

	/**
	 * 大屏-加气站管理-加气站储值金额趋势图
	 * 获取所有加气站信息
	 * @param type 类型
	 */
	@PostMapping("allStoredCalueTrend")
	public R allStoredCalueTrend(String type) {
		return R.data(gasTourReconcileService.allStoredCalueTrend(type));
	}

	/**
	 * 后台-获取加气站营收
	 * @param gasId 加气站id
	 */
	@PostMapping("getGasRevenue")
	public R getGasRevenue(String gasId) {
		return R.data(gasTourReconcileService.getGasRevenue(gasId));
	}

	/**
	 * 大屏-加气运营状态
	 * @param
	 */
	@PostMapping("statusOfOperation")
	public R statusOfOperation() {
		return R.data(gasTourReconcileService.statusOfOperation());
	}

	/**
	 * 下载Excel模板-龙门
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate-longmen", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "下载模板", httpMethod = "GET")
	public void downloadTemplateLongmen(HttpServletResponse response) {
		ExcelUtil.download(response, "交班对账-龙门.xlsx");
	}

	/**
	 * 导入-龙门
	 * @param file file
	 * @return boolean
	 */
	@SneakyThrows
	@PostMapping("write-notice-longmen")
	public R writeNoticeLongmen(MultipartFile file) {
		GasTourReconcileExcelDto dto;
		try {
			dto = gasTourReconcileService.writeNoticeLongmen(file);
		}catch (Exception e){
			return R.fail("上传失败");
		}
		gasTourReconcileService.save(new GasTourReconcile(dto));
		return R.success("上传成功");
	}


	/**
	 * 下载Excel模板-垣曲
	 *
	 * @param response response
	 */
	@GetMapping(value = "/downloadTemplate-yuanqu", produces = "application/json;charset=UTF-8")
	@ApiOperation(value = "下载模板", httpMethod = "GET")
	public void downloadTemplateYuanqu(HttpServletResponse response) {
		ExcelUtil.download(response, "交班对账-垣曲.xlsx");
	}

	/**
	 * 导入-垣曲
	 * @param file file
	 * @return boolean
	 */
	@SneakyThrows
	@PostMapping("write-notice-yuanqu")
	public R writeNoticeYuanqu(MultipartFile file) {
		GasTourReconcileExcelDto dto;
		try {
			dto = gasTourReconcileService.writeNoticeYuanqu(file);
		}catch (Exception e){
			return R.fail("上传失败");
		}
		gasTourReconcileService.save(new GasTourReconcile(dto));
		return R.success("上传成功");
	}
}
