package org.springblade.modules.core.service;


import org.springblade.core.mp.base.BaseService;
import org.springblade.modules.core.entity.GasFluCorrelation;
import org.springblade.modules.core.vo.GasFluCorrelationVO;

import java.util.List;

public interface GasFluCorrelationService extends BaseService<GasFluCorrelation> {

	List<GasFluCorrelationVO> detail(Long userId);
}
