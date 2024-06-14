package org.springblade.modules.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.core.dto.FluidFieldBaseInfoDto;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;

import java.util.List;

/**
 * 加气站基本信息表 服务类
 *
 * @author Blade
 * @since 2022-09-14
 */
public interface FluidFieldBaseInfoService extends IService<FluidFieldBaseInfo> {

    /**
     * 获取加气站基础信息
     */
    List<FluidFieldBaseInfoDto> getBaseInfoList();

}
