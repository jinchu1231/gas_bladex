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
package org.springblade.job.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springblade.core.http.util.HttpUtil;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springblade.job.entity.JobServer;
import org.springblade.job.mapper.JobServerMapper;
import org.springblade.job.service.IJobServerService;
import org.springblade.job.vo.JobServerVO;
import org.springframework.stereotype.Service;
import tech.powerjob.common.response.ResultDTO;

/**
 * 任务服务表 服务实现类
 *
 * @author BladeX
 */
@Service
public class JobServerServiceImpl extends BaseServiceImpl<JobServerMapper, JobServer> implements IJobServerService {

	@Override
	public IPage<JobServerVO> selectJobServerPage(IPage<JobServerVO> page, JobServerVO jobServer) {
		return page.setRecords(baseMapper.selectJobServerPage(page, jobServer));
	}

	@Override
	public Boolean submitAndSync(JobServer jobServer) {
		if (Func.isEmpty(jobServer.getId())) {
			this.sync(jobServer);
		}
		return this.saveOrUpdate(jobServer);
	}

	@Override
	public Boolean sync(JobServer jobServer) {
		Kv appInfo = Kv.create().set("appName", jobServer.getJobAppName()).set("password", jobServer.getJobAppPassword());
		String data = HttpUtil.postJson(jobServer.getJobServerUrl() + "/appInfo/save", JsonUtil.toJson(appInfo));
		ResultDTO<Void> result = JsonUtil.parse(data, new TypeReference<ResultDTO<Void>>() {});
		return result.isSuccess();
	}

}
