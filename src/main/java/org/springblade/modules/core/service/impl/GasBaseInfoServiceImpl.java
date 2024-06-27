package org.springblade.modules.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springblade.common.enums.StatusEnum;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.entity.GasBaseInfo;
import org.springblade.modules.core.mapper.GasBaseInfoMapper;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GasBaseInfoServiceImpl extends BaseServiceImpl<GasBaseInfoMapper,GasBaseInfo> implements GasBaseInfoService {

    @Autowired
    private GasBaseInfoMapper gasBaseInfoMapper;

    @Override
    public List<GasBaseInfoDto> getBaseInfoList() {
        return gasBaseInfoMapper.getBaseInfoList();
    }

	@Override
	public String selectIdByName(String gasName) {
		return gasBaseInfoMapper.selectIdByName(gasName);
	}

	@Override
	public IPage<GasBaseInfo> selectGasBaseInfoList(IPage<GasBaseInfo> page, GasBaseInfo gasBaseInfo) {
		IPage<GasBaseInfo> gasBaseInfoIPage = page.setRecords(baseMapper.selectGasBaseInfoList(page, gasBaseInfo));
		gasBaseInfoIPage.getRecords().forEach(record -> {
			if (!StringUtils.isEmpty(record.getOutProvincial())){
				record.setOutProvincial(StatusEnum.getStatus(String.valueOf(record.getOutProvincial())).getName());

			}
			if (!StringUtils.isEmpty(record.getInProvincial())){
				record.setInProvincial(StatusEnum.getStatus(String.valueOf(record.getInProvincial())).getName());

			}
		});
		return gasBaseInfoIPage;
	}
}
