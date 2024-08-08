package org.springblade.modules.core.service.impl;


import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.flow.demo.leave.entity.ProcessLeave;
import org.springblade.flow.demo.leave.service.ILeaveService;
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

	@Autowired
	private ILeaveService leaveService;

	@Override
	public List<GasFluCorrelationVO> detail(String id) {
		//根据申请人查询对应的液厂列表
		ProcessLeave leave = leaveService.getById(id);
		return gasFluCorrelationMapper.detail(String.valueOf(leave.getTaskUser()));
	}
}
