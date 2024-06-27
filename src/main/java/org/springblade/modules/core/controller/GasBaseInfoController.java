package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.RandomType;
import org.springblade.modules.core.entity.GasBaseInfo;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dev/gas-service/baseinfo")
@Api(value = "加气站基本信息表", tags = "加气站基本信息表接口")
public class GasBaseInfoController extends BladeController {

    @Autowired
    private GasBaseInfoService gasBaseInfoService;

	/**
	 * 加气站详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入fieldOrder")
	public R<GasBaseInfo> detail(String id) {
		return R.data(gasBaseInfoService.getById(id));
	}

    /**
     * 获取加气站基础信息
     */
    @ApiOperation("获取加气站基础信息")
    @PostMapping("/getBaseInfoList")
    public R getBaseInfoList(){
        return R.data(gasBaseInfoService.getBaseInfoList());
    }

	/**
	 * 查询加气站巡查记录列表
	 */
	@PostMapping("/pageList")
	public R<IPage<GasBaseInfo>> list(@RequestBody GasBaseInfo gasBaseInfo,
										 @Valid @RequestBody Query query)
	{
		IPage<GasBaseInfo> list =
			gasBaseInfoService.selectGasBaseInfoList(Condition.getPage(query),gasBaseInfo);
		return R.data(list);
	}

	/**
	 * 加气站基础信息 新增
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入GasBaseInfo")
	public R save(@Valid @RequestBody GasBaseInfo baseInfo) {
		String random = Func.random(15, RandomType.INT);
		baseInfo.setGasNumber(random);
		return R.status(gasBaseInfoService.save(baseInfo));
	}

	/**
	 * 加气站基础信息 修改
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入GasBaseInfo")
	public R update(@Valid @RequestBody GasBaseInfo baseInfo) {
		return R.status(gasBaseInfoService.updateById(baseInfo));
	}

	/**
	 * 删除
	 */
	@GetMapping("/delete")
	@ApiOperation(value = "删除", notes = "传入ids")
	public R delete(@RequestParam("ids") String ids) {
		return R.status(gasBaseInfoService.deleteLogic(Func.toLongList(ids)));
	}
}
