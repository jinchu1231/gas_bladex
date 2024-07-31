package org.springblade.modules.core.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.vo.DeviceRecordVo;

import java.util.Date;
import java.util.List;

/**
 * 特种设备安全检查记录对象 gas_device_record
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
public class GasDeviceRecordDto {
    private static final long serialVersionUID = 1L;

	/** 加气站ID */
	private String id;
	/** 加气站ID */
    private String gasId;
	/** 加气站名称*/
//	@ExcelProperty("加气站名称")
	@Excel(name = "加气站名称",width = 30)
    private String gasName;
	/** 检查日期*/
	@Excel(name = "检查日期",width = 15)
	private String inspectData;
	/** 巡查人名称*/
	@Excel(name = "巡查人名称",width = 10)
    private String inspectName;
    @ExcelCollection(name = "检查信息")
    private List<DeviceRecordVo> list;
	/** 采取的防范措施 */
	@Excel(name = "采取的防范措施")
	private String takeSteps;
	/** 安全员 */
	@Excel(name = "安全员")
	private String safetyOfficer;
	/**
	 * 文件url
	 */
	private String fileUrl;

}
