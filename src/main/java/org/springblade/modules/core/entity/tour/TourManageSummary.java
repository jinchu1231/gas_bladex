package org.springblade.modules.core.entity.tour;

import lombok.Data;

@Data
public class TourManageSummary {
	/** 库存 */
	private String inventory;

	/** 班组长签字 */
	private String leaderSignature;

	/** 值班站长签字 */
	private String agentSignature;
}
