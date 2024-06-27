package org.springblade.modules.core.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springblade.core.mp.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 特种设备安全检查记录对象 gas_device_record
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
public class GasDeviceRecord {
    private static final long serialVersionUID = 1L;

    /** id */
    @ExcelIgnore
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;

	/** 加气站ID */
	@ExcelIgnore
	private String gasId;

	/** 加气站名称*/
	@ExcelProperty("加气站名称")
	private String gasName;

	/** 检查内容*/
	@ExcelIgnore
	private String content;

	/** 巡查人名称*/
	@ExcelProperty("巡查人名称")
	private String inspectName;

	/** 检查日期 */
	@ExcelProperty("检查日期")
	private String inspectData;

    /** 采取的防范措施 */
	@ExcelProperty("采取的防范措施")
    private String takeSteps;

    /** 安全员 */
	@ExcelProperty("安全员")
    private String safetyOfficer;

    /** 创建者 */
    @ExcelIgnore
    private String createUser;

	/** 创建时间 */
	@ExcelIgnore
	private Date createTime;

    /** 最后修改人 */
	@ExcelIgnore
    private String updateUser;

	/** 更新时间 */
	@ExcelIgnore
	private Date UpdateTime;

    /** 是否刪除 */
	@ExcelIgnore
    private Long isDeleted;
}
