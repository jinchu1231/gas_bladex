package org.springblade.modules.core.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class DeviceRecordVo {
	/** 检查项*/
	@Excel(name = "检查项",width = 20)
	private String inspectItem;

	/** 检查内容*/
	@Excel(name = "检查内容",height = 30, width = 40)
	private String inspectContent;

	/** 检查结果 */
	@Excel(name = "检查结果",width = 20)
	private String inspectResult;

	/** 处理结果 */
	@Excel(name = "处理结果",width = 20)
	private String result;

	/** 备注 */
	@Excel(name = "备注",width = 20)
	private String remark;
}
