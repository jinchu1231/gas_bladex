package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.core.entity.GasFluCorrelation;
import org.springblade.modules.core.vo.GasFluCorrelationVO;

import java.util.List;


public interface GasFluCorrelationMapper extends BaseMapper<GasFluCorrelation> {


	List<GasFluCorrelationVO> detail(String userId);
}
