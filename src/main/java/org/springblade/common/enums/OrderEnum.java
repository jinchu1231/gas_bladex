package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderEnum {
	/**
	 * 未知
	 */
	CREATE_UNAUDITED("已创建未审核","0"),
	/**
	 * 已创建未支付
	 */
	CREATED_NOT_PAID("已审核","1"),
	/**
	 * 已支付
	 */
	ORDER_PAID("已支付","2"),
	/**
	 * 已完成
	 */
	ORDER_OVER("已完成","3"),
	;

	final String name;

	final String value;

	/**
	 * 根据枚举值获取枚举对象
	 *
	 * @param value v
	 * @return 枚举对象
	 */
	public static OrderEnum getStatus(String value) {
		for (OrderEnum enumItem : OrderEnum.values()) {
			if (enumItem.getValue().equals(value)) {
				return enumItem;
			}
		}
		return null;
	}
}
