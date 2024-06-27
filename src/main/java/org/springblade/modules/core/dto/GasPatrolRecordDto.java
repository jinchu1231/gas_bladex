package org.springblade.modules.core.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springblade.modules.core.dto.patrol.RecordDto;

import java.util.Date;
import java.util.List;

@Data
public class GasPatrolRecordDto {
	/** $column.columnComment */
	@ExcelIgnore
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/** 加气站id */
	private String gasId;

	/** 加气站名称 */
	private String gasName;

	/** 填报日期(几月几日) */
	private String fillDate;

	/** 记录详情 */
	private List<RecordDto> recordDtoList;

	/** 负责人 */
	private String principal;

	/** 复查人 */
	private String reviewPerson;

	/** 创建者 */
	private String createUser;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改人 */
	private String updateUser;

	/** 更新时间 */
	private Date UpdateTime;

	/** 是否刪除 */
	private Long isDeleted;


}
