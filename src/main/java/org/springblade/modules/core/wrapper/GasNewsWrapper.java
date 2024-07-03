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
import org.springblade.modules.core.entity.GasNewsEntity;
import org.springblade.modules.core.vo.GasNewsVO;
import java.util.Objects;

/**
 * 新闻动态 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2024-07-01
 */
public class GasNewsWrapper extends BaseEntityWrapper<GasNewsEntity, GasNewsVO>  {

	public static GasNewsWrapper build() {
		return new GasNewsWrapper();
 	}

	@Override
	public GasNewsVO entityVO(GasNewsEntity gasnews) {
		GasNewsVO gasnewsVO = Objects.requireNonNull(BeanUtil.copy(gasnews, GasNewsVO.class));

		//User createUser = UserCache.getUser(gasnews.getCreateUser());
		//User updateUser = UserCache.getUser(gasnews.getUpdateUser());
		//gasnewsVO.setCreateUserName(createUser.getName());
		//gasnewsVO.setUpdateUserName(updateUser.getName());

		return gasnewsVO;
	}


}
