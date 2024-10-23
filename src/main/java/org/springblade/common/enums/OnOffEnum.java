package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OnOffEnum {
	/**
	 * 关闭
	 */
	CLOSE("关闭",0),
	/**
	 * 开启
	 */
	OPEN("开启",1),
	;

	final String name;

	final Integer value;

	/**
	 * 根据枚举值获取枚举对象
	 *
	 * @param value v
	 * @return 枚举对象
	 */
	public static OnOffEnum getStatus(Integer value) {
		for (OnOffEnum enumItem : OnOffEnum.values()) {
			if (enumItem.getValue().equals(value)) {
				return enumItem;
			}
		}
		return null;
	}
}
