package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.entity.GasBaseInfo;

import java.util.List;

public interface GasBaseInfoMapper extends BaseMapper<GasBaseInfo> {

   List<GasBaseInfoDto> getBaseInfoList();

    String selectIdByName(String gasName);

	List<GasBaseInfo> selectGasBaseInfoList(IPage page, GasBaseInfo gasBaseInfo);
}
