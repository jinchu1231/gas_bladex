package org.springblade.modules.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springblade.common.enums.GasStatusEnum;
import org.springblade.common.enums.StatusEnum;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.entity.GasBaseInfo;
import org.springblade.modules.core.mapper.GasBaseInfoMapper;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GasBaseInfoServiceImpl extends BaseServiceImpl<GasBaseInfoMapper,GasBaseInfo> implements GasBaseInfoService {

    @Autowired
    private GasBaseInfoMapper gasBaseInfoMapper;

    @Override
    public List<GasBaseInfoDto> getBaseInfoList(String type) {
    	//type传入0查询父级站级信息
		List<GasBaseInfoDto> baseInfoList = gasBaseInfoMapper.getBaseInfoList(type);
		baseInfoList.forEach(baseInfo -> {
			baseInfo.setStatusName(Objects.requireNonNull(GasStatusEnum.getStatus(baseInfo.getStatus())).getName());
		});
		return baseInfoList;
    }

	@Override
	public String selectIdByName(String gasName) {
		return gasBaseInfoMapper.selectIdByName(gasName);
	}

	@Override
	public String selectNameByNumber(String gasNumber) {
		return gasBaseInfoMapper.selectNameByNumber(gasNumber);
	}

	@Override
	public IPage<GasBaseInfo> selectGasBaseInfoList(IPage<GasBaseInfo> page, GasBaseInfo gasBaseInfo) {
		IPage<GasBaseInfo> gasBaseInfoIPage = page.setRecords(baseMapper.selectGasBaseInfoList(page, gasBaseInfo));
		gasBaseInfoIPage.getRecords().forEach(record -> {
			if (!StringUtils.isEmpty(record.getOutProvincial())){
				record.setOutProvincial(Objects.requireNonNull(StatusEnum.getStatus(String.valueOf(record.getOutProvincial()))).getName());
			}

			if (!StringUtils.isEmpty(record.getInProvincial())){
				record.setInProvincial(Objects.requireNonNull(StatusEnum.getStatus(String.valueOf(record.getInProvincial()))).getName());
			}

			if (!StringUtils.isEmpty(String.valueOf(record.getStatus()))){
				record.setStatusName(Objects.requireNonNull(GasStatusEnum.getStatus(record.getStatus())).getName());
			}
		});
		return gasBaseInfoIPage;
	}

	@Override
	public GasBaseInfo getDetailById(String id) {
		GasBaseInfo detail = gasBaseInfoMapper.getDetailById(id);
		if (!Objects.isNull(detail) && !StringUtils.isEmpty(String.valueOf(detail.getStatus()))){
			detail.setStatusName(Objects.requireNonNull(GasStatusEnum.getStatus(detail.getStatus())).getName());
		}
		return detail;
	}
}
