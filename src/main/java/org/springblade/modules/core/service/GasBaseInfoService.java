package org.springblade.modules.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.entity.GasBaseInfo;

import java.util.List;

/**
 * 加气站基本信息表 服务类
 *
 * @author Blade
 * @since 2022-09-14
 */
public interface GasBaseInfoService extends IService<GasBaseInfo> {

    /**
     * 获取加气站基础信息
     */
    List<GasBaseInfoDto> getBaseInfoList();

}
