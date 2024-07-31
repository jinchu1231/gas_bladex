package org.springblade.modules.core.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.service.GasFluCorrelationService;
import org.springblade.modules.core.vo.GasFluCorrelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(AppConstant.DEV_CODE + "/gas-correlation")
@Api(value = "加气站液厂关联表", tags = "加气站液厂关联表接口")
public class GasFluCorrelationController extends BladeController {

    @Autowired
    private GasFluCorrelationService gasFluCorrelationService;

	/**
	 * 当前登录加气站关联液厂列表
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入fieldOrder")
	public R<List<GasFluCorrelationVO>> detail() {
		return R.data(gasFluCorrelationService.detail(AuthUtil.getUserId()));
	}



}
