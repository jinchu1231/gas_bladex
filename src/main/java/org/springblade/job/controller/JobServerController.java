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
import org.springblade.core.tool.utils.StringPool;
import org.springblade.job.entity.JobServer;
import org.springblade.job.service.IJobServerService;
import org.springblade.job.vo.JobServerVO;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 任务服务表 控制器
 *
 * @author BladeX
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.APPLICATION_JOB_NAME + "/job-server")
@Api(value = "任务服务表", tags = "任务服务表接口")
public class JobServerController extends BladeController {

	private final IJobServerService jobServerService;

	/**
	 * 任务服务表 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入jobServer")
	public R<JobServer> detail(JobServer jobServer) {
		JobServer detail = jobServerService.getOne(Condition.getQueryWrapper(jobServer));
		return R.data(detail);
	}

	/**
	 * 任务服务表 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入jobServer")
	public R<IPage<JobServer>> list(@ApiIgnore @RequestParam Map<String, Object> jobServer, Query query) {
		IPage<JobServer> pages = jobServerService.page(Condition.getPage(query), Condition.getQueryWrapper(jobServer, JobServer.class));
		return R.data(pages);
	}

	/**
	 * 任务服务表 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入jobServer")
	public R<IPage<JobServerVO>> page(JobServerVO jobServer, Query query) {
		IPage<JobServerVO> pages = jobServerService.selectJobServerPage(Condition.getPage(query), jobServer);
		return R.data(pages);
	}

	/**
	 * 任务服务表 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入jobServer")
	public R save(@Valid @RequestBody JobServer jobServer) {
		return R.status(jobServerService.save(jobServer));
	}

	/**
	 * 任务服务表 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入jobServer")
	public R update(@Valid @RequestBody JobServer jobServer) {
		return R.status(jobServerService.updateById(jobServer));
	}

	/**
	 * 任务服务表 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入jobServer")
	public R submit(@Valid @RequestBody JobServer jobServer) {
		return R.status(jobServerService.submitAndSync(jobServer));
	}

	/**
	 * 任务服务表 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(jobServerService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 应用服务信息 列表
	 */
	@GetMapping("/select")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "应用服务信息", notes = "应用服务信息")
	public R select() {
		List<JobServer> list = jobServerService.list();
		list.forEach(jobServer -> jobServer.setJobAppName(
			jobServer.getJobAppName() + StringPool.COLON + StringPool.SPACE + StringPool.LEFT_BRACKET +
				jobServer.getJobServerName() + StringPool.SPACE + StringPool.DASH + StringPool.SPACE + jobServer.getJobServerUrl() + StringPool.RIGHT_BRACKET)
		);
		return R.data(list);
	}

	/**
	 * 任务服务数据同步
	 */
	@PostMapping("sync")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "任务服务数据同步", notes = "任务服务数据同步")
	public R sync() {
		jobServerService.list().forEach(jobServerService::sync);
		return R.success("同步完毕");
	}


}
