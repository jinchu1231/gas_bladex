package org.springblade.modules.core.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springblade.core.mp.base.BaseEntity;

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

    /** 检查日期 */
	@ExcelProperty("检查日期")
    private String inspectData;

    /** 检查项-人员 */
	@ExcelProperty("检查项-人员")
    private String inspectPerson;

    /** 检查项-设备 */
	@ExcelProperty("检查项-设备")
    private String inspectDevice;

    /** 检查项-安全附件或安全保护装置 */
	@ExcelProperty("检查项-安全附件或安全保护装置")
    private String inspectProtector;

    /** 检查项-环境 */
	@ExcelProperty("检查项-环境")
    private String inspectCircumstances;

    /** 检查项-政府监督、通报、预警 */
	@ExcelProperty("检查项-政府监督、通报、预警")
    private String inspectWarning;

    /** 检查项-投诉举报 */
	@ExcelProperty("检查项-投诉举报")
    private String inspectComplaintReport;

    /** 检查项-舆情信息 */
	@ExcelProperty("检查项-舆情信息")
    private String inspectPublicSentiment;

    /** 检查项-人员-处理结果 */
	@ExcelProperty("检查项-人员-处理结果")
    private String inspectPersonResult;

    /** 检查项-设备-处理结果 */
	@ExcelProperty("检查项-设备-处理结果")
    private String inspectDeviceResult;

    /** 检查项-安全附件或安全保护装置-处理结果 */
	@ExcelProperty("检查项-安全附件或安全保护装置-处理结果")
    private String inspectProtectorResult;

    /** 检查项-环境-处理结果 */
	@ExcelProperty("检查项-环境-处理结果")
    private String inspectCircumstancesResult;

    /** 检查项-政府监督、通报、预警-处理结果 */
	@ExcelProperty("检查项-政府监督、通报、预警-处理结果")
    private String inspectWarningResult;

    /** 检查项-投诉举报-处理结果 */
	@ExcelProperty("检查项-投诉举报-处理结果")
    private String inspectComplaintReportResult;

    /** 检查项-舆情信息-处理结果 */
	@ExcelProperty("检查项-舆情信息-处理结果")
    private String inspectPublicSentimentResult;

    /** 检查项-人员-备注 */
	@ExcelProperty("检查项-人员-备注")
    private String inspectPersonRemark;

    /** 检查项-设备-备注 */
	@ExcelProperty("检查项-设备-备注")
    private String inspectDeviceRemark;

    /** 检查项-安全附件或安全保护装置-备注 */
	@ExcelProperty("检查项-安全附件或安全保护装置-备注")
    private String inspectProtectorRemark;

    /** 检查项-环境-备注 */
	@ExcelProperty("检查项-环境-备注")
    private String inspectCircumstancesRemark;

    /** 检查项-政府监督、通报、预警-备注 */
	@ExcelProperty("检查项-政府监督、通报、预警-备注")
    private String inspectWarningRemark;

    /** 检查项-投诉举报-备注 */
	@ExcelProperty("检查项-投诉举报-备注")
    private String inspectComplaintReportRemark;

    /** 检查项-舆情信息-备注 */
	@ExcelProperty("检查项-舆情信息-备注")
    private String inspectPublicSentimentRemark;

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
