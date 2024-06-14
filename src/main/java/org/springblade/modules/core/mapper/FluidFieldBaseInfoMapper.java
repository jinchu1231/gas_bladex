package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.core.dto.FluidFieldBaseInfoDto;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;

import java.util.List;

public interface FluidFieldBaseInfoMapper extends BaseMapper<FluidFieldBaseInfo> {

   List<FluidFieldBaseInfoDto> getBaseInfoList();
}
