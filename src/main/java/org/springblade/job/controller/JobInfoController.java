/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.job.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.job.entity.JobInfo;
import org.springblade.job.service.IJobInfoService;
import org.springblade.job.vo.JobInfoVO;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;

/**
 * 任务信息表 控制器
 *
 * @author BladeX
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_JOB_NAME + "/job-info")
@Api(value = "任务信息表", tags = "任务信息表接口")
public class JobInfoController extends BladeController {

	private final IJobInfoService jobInfoService;

	/**
	 * 任务信息表 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入jobInfo")
	public R<JobInfo> detail(JobInfo jobInfo) {
		JobInfo detail = jobInfoService.getOne(Condition.getQueryWrapper(jobInfo));
		return R.data(detail);
	}

	/**
	 * 任务信息表 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入jobInfo")
	public R<IPage<JobInfo>> list(@ApiIgnore @RequestParam Map<String, Object> jobInfo, Query query) {
		IPage<JobInfo> pages = jobInfoService.page(Condition.getPage(query), Condition.getQueryWrapper(jobInfo, JobInfo.class));
		return R.data(pages);
	}

	/**
	 * 任务信息表 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入jobInfo")
	public R<IPage<JobInfoVO>> page(JobInfoVO jobInfo, Query query) {
		IPage<JobInfoVO> pages = jobInfoService.selectJobInfoPage(Condition.getPage(query), jobInfo);
		return R.data(pages);
	}

	/**
	 * 任务信息表 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入jobInfo")
	public R save(@Valid @RequestBody JobInfo jobInfo) {
		return R.status(jobInfoService.save(jobInfo));
	}

	/**
	 * 任务信息表 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入jobInfo")
	public R update(@Valid @RequestBody JobInfo jobInfo) {
		return R.status(jobInfoService.updateById(jobInfo));
	}

	/**
	 * 任务信息表 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入jobInfo")
	public R submit(@Valid @RequestBody JobInfo jobInfo) {
		return R.status(jobInfoService.submitAndSync(jobInfo));
	}

	/**
	 * 任务信息表 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(jobInfoService.removeAndSync(Func.toLongList(ids)));
	}

	/**
	 * 任务信息表 变更状态
	 */
	@PostMapping("/change")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "变更状态", notes = "传入id与status")
	public R change(@ApiParam(value = "主键", required = true) @RequestParam Long id, @ApiParam(value = "是否启用", required = true) @RequestParam Integer enable) {
		return R.status(jobInfoService.changeServerJob(id, enable));
	}

	/**
	 * 运行服务
	 */
	@PostMapping("run")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "运行服务", notes = "传入jobInfoId")
	public R run(@ApiParam(value = "主键", required = true) @RequestParam Long id) {
		return R.status(jobInfoService.runServerJob(id));
	}


	/**
	 * 任务信息数据同步
	 */
	@PostMapping("sync")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "任务信息数据同步", notes = "任务信息数据同步")
	public R sync() {
		return R.status(jobInfoService.sync());
	}

}
