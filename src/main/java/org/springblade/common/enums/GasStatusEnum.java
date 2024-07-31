package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GasStatusEnum {
	/**
	 * 待建
	 */
	TO_BE_BUILT("待建",0),
	/**
	 * 建设中
	 */
	IN_CONSTRUCTION("建设中",1),
	/**
	 * 已建成待运行
	 */
	TO_BE_RUN("已建成待运行",2),
	/**
	 * 已运行
	 */
	RUN("已运行",3),
	/**
	 * 废弃
	 */
	ABANDON("废弃",4),
	;

	final String name;

	final Integer value;

	/**
	 * 根据枚举值获取枚举对象
	 *
	 * @param value v
	 * @return 枚举对象
	 */
	public static GasStatusEnum getStatus(Integer value) {
		for (GasStatusEnum enumItem : GasStatusEnum.values()) {
			if (enumItem.getValue().equals(value)) {
				return enumItem;
			}
		}
		return null;
	}
}
