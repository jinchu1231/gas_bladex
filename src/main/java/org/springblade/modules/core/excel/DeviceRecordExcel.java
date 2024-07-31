package org.springblade.modules.core.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class DeviceRecordExcel {

	/** 加气站名称*/
	@ExcelProperty("加气站id")
	private String gasId;
	/** 加气站名称*/
	@ExcelProperty("加气站名称")
	private String gasName;
	/** 检查日期*/
	@ExcelProperty("检查日期")
	private String inspectData;
	/** 巡查人名称*/
	@ExcelProperty("巡查人名称")
	private String inspectName;

	/** 检查项*/
	@ExcelProperty("检查项")
	@ColumnWidth(50)
	private String inspectItem;

	/** 检查内容*/
	@ExcelProperty("检查内容")
	@ColumnWidth(50)
	private String inspectContent;

	/** 检查结果 */
	@ExcelProperty("检查结果")
	@ColumnWidth(50)
	private String inspectResult;

	/** 处理结果 */
	@ExcelProperty("处理结果")
	@ColumnWidth(50)
	private String result;

	/** 备注 */
	@ExcelProperty("备注")
	@ColumnWidth(50)
	private String remark;
	/** 采取的防范措施 */
	@ExcelProperty("采取的防范措施")
	private String takeSteps;
	/** 安全员 */
	@ExcelProperty("安全员")
	private String safetyOfficer;
}
