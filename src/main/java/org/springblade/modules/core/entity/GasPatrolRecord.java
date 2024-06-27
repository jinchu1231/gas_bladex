package org.springblade.modules.core.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.modules.core.dto.patrol.RecordDto;

import java.util.Date;
import java.util.List;

/**
 * 加气站巡查记录对象 gas_patrol_record
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
public class GasPatrolRecord {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ExcelIgnore
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 加气站id */
	@ExcelIgnore
    private String gasId;

	/** 加气站名称 */
	@ExcelProperty("加气站名称")
	private String gasName;

    /** 填报日期(几月几日) */
	@ExcelProperty("填报日期")
    private String fillDate;

	/** 填报内容 */
	private String content;

    /** 填报人 */
	@ExcelProperty("填报人")
    private String fillPerson;

    /** 负责人 */
	@ExcelProperty("负责人")
    private String principal;

    /** 复查人 */
	@ExcelProperty("复查人")
    private String reviewPerson;

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
