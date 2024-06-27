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
package org.springblade.modules.core.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.core.entity.FieldOrderEntity;
import org.springblade.modules.core.vo.FieldOrderVO;
import java.util.Objects;

/**
 * 液厂采购订单 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2024-06-19
 */
public class FieldOrderWrapper extends BaseEntityWrapper<FieldOrderEntity, FieldOrderVO>  {

	public static FieldOrderWrapper build() {
		return new FieldOrderWrapper();
 	}

	@Override
	public FieldOrderVO entityVO(FieldOrderEntity fieldOrder) {
		FieldOrderVO fieldOrderVO = Objects.requireNonNull(BeanUtil.copy(fieldOrder, FieldOrderVO.class));

		//User createUser = UserCache.getUser(fieldOrder.getCreateUser());
		//User updateUser = UserCache.getUser(fieldOrder.getUpdateUser());
		//fieldOrderVO.setCreateUserName(createUser.getName());
		//fieldOrderVO.setUpdateUserName(updateUser.getName());

		return fieldOrderVO;
	}


}
