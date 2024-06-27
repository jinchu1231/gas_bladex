package org.springblade.modules.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.core.dto.FluidFieldBaseInfoDto;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;

import java.util.List;

/**
 * 加气站基本信息表 服务类
 *
 * @author Blade
 * @since 2022-09-14
 */
public interface FluidFieldBaseInfoService extends BaseService<FluidFieldBaseInfo> {

    /**
     * 获取加气站基础信息
     */
    List<FluidFieldBaseInfoDto> getBaseInfoList();

    IPage<FluidFieldBaseInfo> selectFluidFieldBaseInfoList(IPage<FluidFieldBaseInfo> page, FluidFieldBaseInfo fluidFieldBaseInfo);
}
