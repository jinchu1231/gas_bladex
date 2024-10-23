package org.springblade.modules.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springblade.common.enums.GasStatusEnum;
import org.springblade.common.enums.OnOffEnum;
import org.springblade.common.enums.StatusEnum;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.core.dto.FluidFieldBaseInfoDto;
import org.springblade.modules.core.entity.FluidFieldBaseInfo;
import org.springblade.modules.core.mapper.FluidFieldBaseInfoMapper;
import org.springblade.modules.core.service.FluidFieldBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
		IPage<FluidFieldBaseInfo> fluidFieldBaseInfoIPage = page.setRecords(fluidFieldBaseInfoMapper.selectFluidFieldBaseInfoList(page, fluidFieldBaseInfo));
		fluidFieldBaseInfoIPage.getRecords().forEach(record -> {
			if (!StringUtils.isEmpty(String.valueOf(record.getOnOff()))){
				record.setOnOffName(Objects.requireNonNull(OnOffEnum.getStatus(record.getOnOff().intValue())).getName());
			}
		});
		return fluidFieldBaseInfoIPage;
	}

	@Override
	public String fluBaseInfo(String fluId) {
		return fluidFieldBaseInfoMapper.fluBaseInfo(fluId);
	}

	@Override
	public List<FluidFieldBaseInfo> getFluList() {
		return fluidFieldBaseInfoMapper.getFluList();
	}

	@Override
	public boolean updateBaseInfoById(FluidFieldBaseInfo baseInfo) {
		return fluidFieldBaseInfoMapper.updateBaseInfoById(baseInfo) > 0;
	}

	@Override
	public FluidFieldBaseInfoDto selectInfoById(String id) {
		FluidFieldBaseInfoDto fluidFieldBaseInfoDto = fluidFieldBaseInfoMapper.selectInfoById(id);
		if (Objects.isNull(fluidFieldBaseInfoDto)){
			return new FluidFieldBaseInfoDto();
		}
		fluidFieldBaseInfoDto.setOnOffName(Objects.requireNonNull(OnOffEnum.getStatus(fluidFieldBaseInfoDto.getOnOff().intValue())).getName());
		return fluidFieldBaseInfoDto;
	}
}
