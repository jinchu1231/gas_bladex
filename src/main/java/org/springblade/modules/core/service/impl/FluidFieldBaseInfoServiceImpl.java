package org.springblade.modules.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.core.dto.FluidFieldBaseInfoDto;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;
import org.springblade.modules.core.mapper.FluidFieldBaseInfoMapper;
import org.springblade.modules.core.service.FluidFieldBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FluidFieldBaseInfoServiceImpl extends BaseServiceImpl<FluidFieldBaseInfoMapper,FluidFieldBaseInfo> implements FluidFieldBaseInfoService {

    @Autowired
    private FluidFieldBaseInfoMapper fluidFieldBaseInfoMapper;

    @Override
    public List<FluidFieldBaseInfoDto> getBaseInfoList() {
        return fluidFieldBaseInfoMapper.getBaseInfoList();
    }

	@Override
	public IPage<FluidFieldBaseInfo> selectFluidFieldBaseInfoList(IPage<FluidFieldBaseInfo> page, FluidFieldBaseInfo fluidFieldBaseInfo) {
		return page.setRecords(fluidFieldBaseInfoMapper.selectFluidFieldBaseInfoList(page, fluidFieldBaseInfo));
	}
}
