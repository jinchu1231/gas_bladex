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
package org.springblade.modules.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tenant.mp.TenantEntity;

/**
 * 液厂挂牌价格 实体类
 *
 * @author BladeX
 * @since 2024-06-14
 */
@Data
@TableName("fluid_listed_price")
@ApiModel(value = "ListedPrice对象", description = "液厂挂牌价格")
@EqualsAndHashCode(callSuper = true)
public class ListedPriceEntity extends BaseEntity {

	/**
	 * 液厂id
	 */
	@ApiModelProperty(value = "液厂id")
	private String fluid;
	/**
	 * 液厂名称
	 */
	@ApiModelProperty(value = "液厂名称")
	private String fluidName;
	/**
	 * 日期
	 */
	@ApiModelProperty(value = "日期")
	private String day;
	/**
	 * 挂牌价格
	 */
	@ApiModelProperty(value = "挂牌价格")
	private String listedPrice;

}
