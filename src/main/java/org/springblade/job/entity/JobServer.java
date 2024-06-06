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
package org.springblade.job.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

/**
 * 任务服务表 实体类
 *
 * @author BladeX
 */
@Data
@TableName("blade_job_server")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "JobServer对象", description = "任务服务表")
public class JobServer extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 任务服务名称
	 */
	@ApiModelProperty(value = "任务服务名称")
	private String jobServerName;
	/**
	 * 任务服务器地址
	 */
	@ApiModelProperty(value = "任务服务器地址")
	private String jobServerUrl;
	/**
	 * 任务应用名称
	 */
	@ApiModelProperty(value = "任务应用名称")
	private String jobAppName;
	/**
	 * 任务应用密码
	 */
	@ApiModelProperty(value = "任务应用密码")
	private String jobAppPassword;
	/**
	 * 任务备注
	 */
	@ApiModelProperty(value = "任务备注")
	private String jobRemark;

}
