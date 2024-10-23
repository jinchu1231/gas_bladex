package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.modules.core.entity.GasCamera;



public interface GasCameraMapper extends BaseMapper<GasCamera> {


	String getDeviceSerial(String gasId);
}
