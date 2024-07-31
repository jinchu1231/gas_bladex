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

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tenant.mp.TenantEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Data
@ApiModel(value = "BaseInfo对象", description = "加气站基本信息表")
public class GasBaseInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
	/**
	 * 加气站编码
	 */
	@ApiModelProperty(value = "加气站编码")
	private String gasNumber;
	/**
	 * 父级ID
	 */
	@ApiModelProperty(value = "父级ID")
	private String parentId;
    /**
     * 加气站名称
     */
    @ApiModelProperty(value = "加气站名称")
    private String gasName;
	/**
	 * 加气站位置
	 */
	@ApiModelProperty(value = "加气站位置")
	private String gasLocation;
    /**
     * 所处地市
     */
    @ApiModelProperty(value = "所处地市")
    private String gasCity;
    /**
     * 所处区县
     */
    @ApiModelProperty(value = "所处区县")
    private String gasCount;
    /**
     * 行政区划代码
     */
    @ApiModelProperty(value = "行政区划代码")
    private String gasDivision;
    /**
     * 加气站地址
     */
    @ApiModelProperty(value = "加气站地址")
    private String gasAddress;
    /**
     * 公路类型
     */
    @ApiModelProperty(value = "公路类型")
    private Byte roadType;
    /**
     * 加气站类型
     */
    @ApiModelProperty(value = "加气站类型")
    private Byte gasType;
    /**
     * 产权隶属单位
     */
    @ApiModelProperty(value = "产权隶属单位")
    private String propertyRight;
    /**
     * 所在路段名称
     */
    @ApiModelProperty(value = "所在路段名称")
    private String sectionName;
    /**
     * 所在路段编号
     */
    @ApiModelProperty(value = "所在路段编号")
    private String sectionNumber;
    /**
     * 路线编码
     */
    @ApiModelProperty(value = "路线编码")
    private String lineNumber;
    /**
     * 路线名称
     */
    @ApiModelProperty(value = "路线名称")
    private String lineName;
    /**
     * 桩号
     */
    @ApiModelProperty(value = "桩号")
    private String stakeNumber;
    /**
     * 方向
     */
    @ApiModelProperty(value = "方向")
    private String gasDirection;
    /**
     * 行车路线方向
     */
    @ApiModelProperty(value = "行车路线方向")
    private String drivingRoute;
    /**
     * 是否出省口加气站
     */
    @ApiModelProperty(value = "是否出省口加气站")
    private String outProvincial;
    /**
     * 是否入省口加气站
     */
    @ApiModelProperty(value = "是否入省口加气站")
    private String inProvincial;
    /**
     * 相邻省份
     */
    @ApiModelProperty(value = "相邻省份")
    private String neighboringProvinces;
    /**
     * 建成时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "建成时间")
    private LocalDate completionTime;
    /**
     * 初始运营时间
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "初始运营时间")
    private LocalDate initialOperationTime;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String gasLong;
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String gasLat;
	/**
	 * 加气站图片
	 */
	@ApiModelProperty(value = "加气站图片")
	private String gasImg;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
	/**
	 * 加气站状态
	 */
	@ApiModelProperty(value = "加气站状态")
	private Byte onOff;
	/**
	 * 原因
	 */
	@ApiModelProperty(value = "原因")
	private String cause;
	/**
	 * 管理单位
	 */
	@ApiModelProperty(value = "管理单位")
	private  Byte manageCompany;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private  Date updateTime;
    /**
     * 是否刪除
     */
    @ApiModelProperty(value = "是否刪除")
	@TableLogic
    private Integer isDeleted;
    /**
     * 状态(0-待建;1-建设中;2-已建成待运行;3-已运行;4-废弃)
     */
    @ApiModelProperty(value = "状态(0-待建;1-建设中;2-已建成待运行;3-已运行;4-废弃)")
    private Integer status;
    private String statusName;
	/**
	 * 创建部门
	 */
	@ApiModelProperty(value = "创建部门")
    private String dept;

}
