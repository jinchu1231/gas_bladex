package org.springblade.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gas-service/baseinfo")
@Api(value = "加气站基本信息表", tags = "加气站基本信息表接口")
public class GasBaseInfoController {

    @Autowired
    private GasBaseInfoService gasBaseInfoService;

    /**
     * 获取加气站基础信息
     */
    @ApiOperation("获取加气站基础信息")
    @PostMapping("/getBaseInfoList")
    public R getBaseInfoList(){
        return R.data(gasBaseInfoService.getBaseInfoList());
    }

}
