package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.dto.FluidFieldBaseInfoDto;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;

import java.util.List;

public interface FluidFieldBaseInfoMapper extends BaseMapper<FluidFieldBaseInfo> {

   List<FluidFieldBaseInfoDto> getBaseInfoList();

	List<FluidFieldBaseInfo> selectFluidFieldBaseInfoList(IPage page, FluidFieldBaseInfo fluidFieldBaseInfo);
}
