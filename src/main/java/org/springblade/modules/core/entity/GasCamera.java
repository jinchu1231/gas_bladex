/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.modules.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.mp.base.BaseEntity;


@Data
@ApiModel(value = "关联对象", description = "视频设备序列号关联表")
public class GasCamera extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
	/**
	 * 加气站id
	 */
	@ApiModelProperty(value = "加气站id")
	private String gasId;
	/**
	 * 设备序列号id
	 */
	@ApiModelProperty(value = "设备序列号id")
	private String deviceSerial;

}
