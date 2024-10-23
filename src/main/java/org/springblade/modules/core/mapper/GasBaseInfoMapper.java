package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.core.dto.GasBaseInfoDto;
import org.springblade.modules.core.entity.GasBaseInfo;

import java.util.List;

public interface GasBaseInfoMapper extends BaseMapper<GasBaseInfo> {

   List<GasBaseInfoDto> getBaseInfoList(String type, String gasId);

    String selectIdByName(String gasName);

    String selectNameByNumber(String gasNumber);

	List<GasBaseInfo> selectGasBaseInfoList(IPage page, @Param("gas") GasBaseInfo gasBaseInfo);

    GasBaseInfo getDetailById(String id);

	int updateBaseInfo(@Param("gas") GasBaseInfo baseInfo);

	List<GasBaseInfoDto> getList();
}
