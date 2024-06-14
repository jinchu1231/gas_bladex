package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.entity.GasBaseInfo;

import java.util.List;

public interface GasBaseInfoMapper extends BaseMapper<GasBaseInfo> {

   List<GasBaseInfoDto> getBaseInfoList();
}
