/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统字典枚举类
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

	/**
	 * 未知
	 */
	STATUS_UNKNOW("未知","-1"),
	/**
	 * 否
	 */
	STATUS_NO("否","0"),
	/**
	 * 是
	 */
	STATUS_YES("否","1"),
	;

	final String name;

	final String value;

	/**
	 * 根据枚举值获取枚举对象
	 *
	 * @param value v
	 * @return 枚举对象
	 */
	public static StatusEnum getStatus(String value) {
		for (StatusEnum enumItem : StatusEnum.values()) {
			if (enumItem.getValue().equals(value)) {
				return enumItem;
			}
		}
		return null;
	}

}
