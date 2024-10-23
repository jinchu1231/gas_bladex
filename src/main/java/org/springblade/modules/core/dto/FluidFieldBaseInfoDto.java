package org.springblade.modules.core.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class FluidFieldBaseInfoDto {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 液厂名称
     */
    private String fluName;
	/**
	 * 液厂编码
	 */
	private String fluNumber;
    /**
     * 液厂概况
     */
    private String fluProfile;
	/**
	 * 液厂图片
	 */
	private String fluImg;
    /**
     * 经度
     */
    private String fluLong;
    /**
     * 纬度
     */
    private String fluLat;
    /**
     * 备注
     */
    private String remark;
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
	 * 液厂状态
	 */
	private Byte onOff;
	private String onOffName;

}
