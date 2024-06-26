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
package org.springblade.modules.resource.rule.sms;

import com.github.qcloudsms.SmsMultiSender;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.sms.SmsTemplate;
import org.springblade.core.sms.TencentSmsTemplate;
import org.springblade.core.sms.props.SmsProperties;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.resource.entity.Sms;
import org.springblade.modules.resource.rule.context.SmsContext;

/**
 * 腾讯云短信构建类
 *
 * @author Chill
 */
@LiteflowComponent(id = "tencentSmsRule", name = "腾讯SMS构建")
public class TencentSmsRule extends NodeComponent {

	@Override
	public void process() throws Exception {
		// 获取上下文
		SmsContext contextBean = this.getContextBean(SmsContext.class);
		Sms sms = contextBean.getSms();
		BladeRedis bladeRedis = contextBean.getBladeRedis();

		SmsProperties smsProperties = new SmsProperties();
		smsProperties.setTemplateId(sms.getTemplateId());
		smsProperties.setAccessKey(sms.getAccessKey());
		smsProperties.setSecretKey(sms.getSecretKey());
		smsProperties.setSignName(sms.getSignName());
		SmsMultiSender smsSender = new SmsMultiSender(Func.toInt(smsProperties.getAccessKey()), sms.getSecretKey());
		SmsTemplate smsTemplate = new TencentSmsTemplate(smsProperties, smsSender, bladeRedis);

		// 设置上下文
		contextBean.setSmsTemplate(smsTemplate);

	}
}
