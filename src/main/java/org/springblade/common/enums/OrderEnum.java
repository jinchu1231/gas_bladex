package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderEnum {
	/**
	 * 未知
	 */
	ORDER_UNKNOW("未知","-1"),
	/**
	 * 已创建未支付
	 */
	ORDER_CREATE_UNPAID("已创建未支付","0"),
	/**
	 * 已支付
	 */
	ORDER_PAID("已支付","1"),
	/**
	 * 超时未支付
	 */
	ORDER_UNPAID("超时未支付","2"),
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
