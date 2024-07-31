package org.springblade.modules.core.service.impl;


import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.core.entity.GasFluCorrelation;
import org.springblade.modules.core.mapper.GasFluCorrelationMapper;
import org.springblade.modules.core.service.GasFluCorrelationService;
import org.springblade.modules.core.vo.GasFluCorrelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GasFluCorrelationServiceImpl extends BaseServiceImpl<GasFluCorrelationMapper, GasFluCorrelation> implements GasFluCorrelationService {

	@Autowired
	private GasFluCorrelationMapper gasFluCorrelationMapper;

	@Override
	public List<GasFluCorrelationVO> detail(Long userId) {
		return gasFluCorrelationMapper.detail(String.valueOf(userId));
	}
}
