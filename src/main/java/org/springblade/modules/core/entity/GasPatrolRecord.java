package org.springblade.modules.core.entity;

import lombok.Data;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;

/**
 * 加气站巡查记录对象 gas_patrol_record
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
public class GasPatrolRecord
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 加气站id */
    private String gasId;

    /** 填报时间(几点) */
    private String fillTime;

    /** 填报日期(几月几日) */
    private String fillDate;

    /** 班次(1-白班;2-夜班) */
    private Long workShift;

    /** 储罐val1(液位) */
    private String storageTankO;

    /** 储罐val2(压力) */
    private String storageTankT;

    /** 泵前val1 */
    private String beforePumpO;

    /** 泵后val1 */
    private String afterPumpO;

    /** 泵前val2 */
    private String beforePumpT;

    /** 泵后val2 */
    private String afterPumpT;

    /** 仪表风 */
    private String instrumentWind;

    /** 加液机 */
    private String liquidFillingMachine;

    /** 储罐val3(温度) */
    private String storageTankTh;

    /** 储液区 */
    private String liquidStorageArea;

    /** 加液区 */
    private String liquidAddingZone;

    /** 电压 */
    private String voltage;

    /** 空压机排水 */
    private String airCompressorDrainage;

    /** 人员设备场站环境巡查情况 */
    private String circumstancesPatrolSituation;

    /** 消防重点单位防火巡查情况 */
    private String fireproofingPatrolSituation;

    /** 填报人 */
    private String fillPerson;

    /** 问题描述 */
    private String problemDescription;

    /** 现场处置措施 */
    private String siteDisposalMeasures;

    /** 处置结果 */
    private String resultOfDisposal;

    /** 遗留隐患描述 */
    private String hiddenDangerDesc;

    /** 遗留隐患和防空措施 */
    private String hiddenDangerAntiaircraftMeasure;

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
