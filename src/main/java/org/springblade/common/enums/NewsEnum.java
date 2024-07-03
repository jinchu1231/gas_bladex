package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NewsEnum {
	/**
	 * 公司动态
	 */
	COMPANY_DYNAMIC("公司动态","1"),
	/**
	 * 行业资讯
	 */
	INDUSTRY_INFORMATION("行业资讯","2"),
	/**
	 * 行业新闻
	 */
	TRADE_NEWS("行业新闻","3"),
	/**
	 * 其他
	 */
	OTHER("其他","4"),
	;

	final String name;

	final String value;

	/**
	 * 根据枚举值获取枚举对象
	 *
	 * @param value v
	 * @return 枚举对象
	 */
	public static NewsEnum getStatus(String value) {
		for (NewsEnum enumItem : NewsEnum.values()) {
			if (enumItem.getValue().equals(value)) {
				return enumItem;
			}
		}
		return null;
	}
}
