package org.springblade.modules.core.dto.patrol;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecordDto {

	/** 填报时间(几点) */
	@ApiModelProperty("填报时间(几点)")
	private String fillTime;

	/** 储罐val1(液位) */
	@ApiModelProperty("储罐val1(液位)")
	private String storageTankO;

	/** 储罐val2(压力) */
	@ApiModelProperty("储罐val2(压力)")
	private String storageTankT;

	/** 泵前val1 */
	@ApiModelProperty("泵前val1")
	private String beforePumpO;

	/** 泵后val1 */
	@ApiModelProperty("泵后val1")
	private String afterPumpO;

	/** 泵前val2 */
	@ApiModelProperty("泵前val2")
	private String beforePumpT;

	/** 泵后val2 */
	@ApiModelProperty("泵后val2")
	private String afterPumpT;

	/** 仪表风 */
	@ApiModelProperty("仪表风")
	private String instrumentWind;

	/** 加液机 */
	@ApiModelProperty("加液机")
	private String liquidFillingMachine;

	/** 储罐val3(温度) */
	@ApiModelProperty("储罐val3(温度)")
	private String storageTankTh;

	/** 储液区 */
	@ApiModelProperty("储液区")
	private String liquidStorageArea;

	/** 加液区 */
	@ApiModelProperty("加液区")
	private String liquidAddingZone;

	/** 电压 */
	@ApiModelProperty("电压")
	private String voltage;

	/** 空压机排水 */
	@ApiModelProperty("空压机排水")
	private String airCompressorDrainage;

	/** 人员设备场站环境巡查情况 */
	@ApiModelProperty("人员设备场站环境巡查情况")
	private String circumstancesPatrolSituation;

	/** 消防重点单位防火巡查情况 */
	@ApiModelProperty("消防重点单位防火巡查情况")
	private String fireproofingPatrolSituation;

	/** 填报人 */
	@ApiModelProperty("填报人")
	private String fillPerson;

	/** 问题描述 */
	@ApiModelProperty("问题描述")
	private String problemDescription;

	/** 现场处置措施 */
	@ApiModelProperty("现场处置措施")
	private String siteDisposalMeasures;

	/** 处置结果 */
	@ApiModelProperty("处置结果")
	private String resultOfDisposal;

	/** 遗留隐患描述 */
	@ApiModelProperty("遗留隐患描述")
	private String hiddenDangerDesc;

	/** 遗留隐患和防空措施 */
	@ApiModelProperty("遗留隐患和防空措施")
	private String hiddenDangerAntiaircraftMeasure;

	public RecordDto(){}

	public RecordDto(String fillTime, String storageTankO, String storageTankT, String beforePumpO,
					 String afterPumpO, String beforePumpT, String afterPumpT, String instrumentWind,
					 String liquidFillingMachine, String storageTankTh, String liquidStorageArea, String liquidAddingZone,
					 String voltage, String airCompressorDrainage, String circumstancesPatrolSituation,
					 String fireproofingPatrolSituation, String problemDescription, String siteDisposalMeasures,
					 String resultOfDisposal, String hiddenDangerDesc, String hiddenDangerAntiaircraftMeasure) {
		this.fillTime = fillTime;
		this.storageTankO = storageTankO;
		this.storageTankT = storageTankT;
		this.beforePumpO = beforePumpO;
		this.afterPumpO = afterPumpO;
		this.beforePumpT = beforePumpT;
		this.afterPumpT = afterPumpT;
		this.instrumentWind = instrumentWind;
		this.liquidFillingMachine = liquidFillingMachine;
		this.storageTankTh = storageTankTh;
		this.liquidStorageArea = liquidStorageArea;
		this.liquidAddingZone = liquidAddingZone;
		this.voltage = voltage;
		this.airCompressorDrainage = airCompressorDrainage;
		this.circumstancesPatrolSituation = circumstancesPatrolSituation;
		this.fireproofingPatrolSituation = fireproofingPatrolSituation;
		this.problemDescription = problemDescription;
		this.siteDisposalMeasures = siteDisposalMeasures;
		this.resultOfDisposal = resultOfDisposal;
		this.hiddenDangerDesc = hiddenDangerDesc;
		this.hiddenDangerAntiaircraftMeasure = hiddenDangerAntiaircraftMeasure;
	}
}
