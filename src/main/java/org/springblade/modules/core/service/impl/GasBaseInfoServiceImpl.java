package org.springblade.modules.core.service.impl;

import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.mapper.GasBaseInfoMapper;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GasBaseInfoServiceImpl implements GasBaseInfoService {

    @Autowired
    private GasBaseInfoMapper gasBaseInfoMapper;

    @Override
    public List<GasBaseInfoDto> getBaseInfoList() {
        return gasBaseInfoMapper.getBaseInfoList();
    }
}
