package org.springblade.modules.core.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class GasBaseInfoDto {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 加气站名称
     */
    private String gasName;
    /**
     * 加气站位置
     */
    private String gasLocation;
    /**
     * 加气站编码
     */
    private String gasNumber;
    /**
     * 所处地市
     */
    private String gasCity;
    /**
     * 所处区县
     */
    private String gasCount;
    /**
     * 行政区划代码
     */
    private String gasDivision;
    /**
     * 加气站地址
     */
    private String gasAddress;
    /**
     * 公路类型
     */
    private Byte roadType;
    /**
     * 加气站类型
     */
    private Byte gasType;
    /**
     * 产权隶属单位
     */
    private String propertyRight;
    /**
     * 所在路段名称
     */
    private String sectionName;
    /**
     * 所在路段编号
     */
    private String sectionNumber;
    /**
     * 路线编码
     */
    private String lineNumber;
    /**
     * 路线名称
     */
    private String lineName;
    /**
     * 桩号
     */
    private String stakeNumber;
    /**
     * 方向
     */
    private String gasDirection;
    /**
     * 行车路线方向
     */
    private String drivingRoute;
	/**
	 * 气价
	 */
	private String currentGasPrice;
    /**
     * 是否出省口加气站
     */
    private Byte outProvincial;
    /**
     * 是否入省口加气站
     */
    private Byte inProvincial;
    /**
     * 相邻省份
     */
    private String neighboringProvinces;
    /**
     * 建成时间
     */
    private LocalDate completionTime;
    /**
     * 初始运营时间
     */
    private LocalDate initialOperationTime;
    /**
     * 经度
     */
    private String gasLong;
    /**
     * 纬度
     */
    private String gasLat;
    /**
     * 加气站图片
     */
    private String gasImg;
    /**
     * 备注
     */
    private String remark;
    /**
     * 加气站状态
     */
    private Byte onOff;
    /**
     * 原因
     */
    private String cause;
    /**
     * 管理单位
     */
    private Byte manageCompany;

    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private  String updateUser;
    /**
     * 修改时间
     */
    private  Date updateTime;
    /**
     * 是否刪除
     */
    private  Integer isDeleted;
    /**
     * 状态(0-待建;1-在建;2-已建未开通;3-已开通运营;4-废弃)
     */
    private Integer status;

    private String statusName;
}
