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
import org.springblade.job.entity.JobServer;
import org.springblade.job.vo.JobServerVO;

/**
 * 任务服务表 服务类
 *
 * @author BladeX
 */
public interface IJobServerService extends BaseService<JobServer> {
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param jobServer
	 * @return
	 */
	IPage<JobServerVO> selectJobServerPage(IPage<JobServerVO> page, JobServerVO jobServer);

	/**
	 * 保存并同步
	 *
	 * @param jobServer
	 * @return
	 */
	Boolean submitAndSync(JobServer jobServer);

	/**
	 * 同步数据
	 *
	 * @param jobServer
	 * @return
	 */
	Boolean sync(JobServer jobServer);

}
