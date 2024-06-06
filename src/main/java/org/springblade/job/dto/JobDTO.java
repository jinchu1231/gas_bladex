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
package org.springblade.job.dto;

import lombok.Data;
import org.springblade.job.entity.JobInfo;
import org.springblade.job.entity.JobServer;
import tech.powerjob.client.PowerJobClient;

/**
 * 任务数据DTO
 *
 * @author Chill
 */
@Data
public class JobDTO {

	/**
	 * 任务信息类
	 */
	private JobInfo jobInfo;

	/**
	 * 任务服务类
	 */
	private JobServer jobServer;

	/**
	 * 任务客户端类
	 */
	private PowerJobClient powerJobClient;

}
