/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.modules.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Data
@ApiModel(value = "BaseInfo对象", description = "液厂基本信息表")
public class FluidFieldBaseInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
	/**
	 * 液厂名称
	 */
	@ApiModelProperty(value = "液厂名称")
	private String fluName;
	/**
	 * 液厂编码
	 */
	@ApiModelProperty(value = "液厂编码")
	private String fluNumber;
	/**
	 * 液厂概况
	 */
	@ApiModelProperty(value = "液厂概况")
	private String fluProfile;
	/**
	 * 液厂图片
	 */
	@ApiModelProperty(value = "液厂图片")
	private String fluImg;
	/**
	 * 经度
	 */
	@ApiModelProperty(value = "经度")
	private String fluLong;
	/**
	 * 纬度
	 */
	@ApiModelProperty(value = "纬度")
	private String fluLat;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private String createUser;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人")
	private  String updateUser;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private  Date updateTime;
	/**
	 * 是否刪除
	 */
	@ApiModelProperty(value = "是否刪除")
	private  Integer isDeleted;
	/**
	 * 液厂状态
	 */
	private Byte onOff;


}
