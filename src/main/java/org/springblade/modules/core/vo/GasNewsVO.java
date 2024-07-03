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
package org.springblade.modules.core.vo;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.springblade.modules.core.entity.GasNewsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 新闻动态 视图实体类
 *
 * @author BladeX
 * @since 2024-07-01
 */
@Data
public class GasNewsVO{
	private static final long serialVersionUID = 1L;
	/**
	 * 新闻类型(1-公司动态;2-行业资讯;3-行业新闻;4-其他)
	 */
	@ApiModelProperty(value = "新闻类型(1-公司动态;2-行业资讯;3-行业新闻;4-其他)")
	private Integer type;
	private String typeName;
	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	private String headline;
	/**
	 * 内容
	 */
	@ApiModelProperty(value = "内容")
	private String content;
	/**
	 * pdf文件url
	 */
	@ApiModelProperty(value = "pdf文件url")
	private String fileUrl;
	/**
	 * 所属站点
	 */
	@ApiModelProperty(value = "所属站点")
	private Long theirId;
	/**
	 * 发布时间
	 */
	@ApiModelProperty(value = "发布时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pubTime;

	private String tenantId;

	private Long id;
	private Long createUser;
	private Long createDept;
	private Date createTime;
	private Long updateUser;
	private Date updateTime;
	private Integer status;
	private Integer isDeleted;
}
