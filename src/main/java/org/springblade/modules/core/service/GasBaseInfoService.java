package org.springblade.modules.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.entity.GasBaseInfo;

import java.util.List;

/**
 * 加气站基本信息表 服务类
 *
 * @author Blade
 * @since 2022-09-14
 */
public interface GasBaseInfoService extends BaseService<GasBaseInfo> {

    /**
     * 获取加气站基础信息
     */
    List<GasBaseInfoDto> getBaseInfoList(String type);

	String selectIdByName(String gasName);

	String selectNameByNumber(String gasNumber);

	IPage<GasBaseInfo> selectGasBaseInfoList(IPage<GasBaseInfo> page, GasBaseInfo gasBaseInfo);
}
