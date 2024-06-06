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
package org.springblade.job.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.job.entity.JobInfo;
import org.springblade.job.vo.JobInfoVO;

import java.util.List;

/**
 * 任务信息表 服务类
 *
 * @author BladeX
 */
public interface IJobInfoService extends BaseService<JobInfo> {
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param jobInfo
	 * @return
	 */
	IPage<JobInfoVO> selectJobInfoPage(IPage<JobInfoVO> page, JobInfoVO jobInfo);

	/**
	 * 保存并同步
	 *
	 * @return
	 */
	Boolean submitAndSync(JobInfo jobInfo);

	/**
	 * 删除并同步
	 *
	 * @return
	 */
	Boolean removeAndSync(List<Long> ids);

	/**
	 * 启用禁用服务
	 *
	 * @param id     任务服务ID
	 * @param enable 是否启用
	 * @return
	 */
	Boolean changeServerJob(Long id, Integer enable);

	/**
	 * 运行服务
	 *
	 * @param id 任务服务ID
	 * @return
	 */
	Boolean runServerJob(Long id);

	/**
	 * 数据同步
	 *
	 * @return
	 */
	Boolean sync();

}
