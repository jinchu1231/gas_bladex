package org.springblade.modules.core.service;


import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.CameraDto;

public interface CameraService {
    /**
     * 获取萤石摄像头流信息
     *
     * @param cameraDto 请求参数
     * @return 拼接流信息
     */
    StringBuilder getCameraInfo(CameraDto cameraDto);

    /**
     * 获取设备列表集合
     * @param query 分页
     * @return 返回设备列表
     */
    R getDeviceList(Query query);

    /**
     * 获取设备通道列表集合
     * @param query 分页
     * @return 返回设备列表
     */
    R getDeviceCameraList(Query query);

	/**
	 * 获取指定服务区的设备通道列表
	 * @param cameraDto 参数
	 * @return 返回设备列表
	 */
	R getAreaDeviceCameraList(CameraDto cameraDto);
}
