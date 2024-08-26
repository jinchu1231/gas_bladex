package org.springblade.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.dto.CameraDto;
import org.springblade.modules.core.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 海康-萤石摄像头 Api
 *
 * @author ruoyi
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.DEV_CODE + "/gas/camera")
@Api(value = "萤石api", tags = "萤石视频接口")
public class CameraController{

    @Autowired
    private CameraService cameraService;

    /**
     * 测试调用萤石摄像头
     */
    @ApiOperation("调用萤石摄像头")
    @PostMapping("/get")
    public R get(@RequestBody @Valid CameraDto cameraDto){
        return R.data(cameraService.getCameraInfo(cameraDto));
    }


    /**
     * 获取所有的服务区设备序列号列表
     */
    @ApiOperation("获取所有的服务区设备序列号列表")
    @PostMapping("/getDeviceList")
    public R getDeviceList(@RequestBody @Valid Query query){
        return cameraService.getDeviceList(query);
    }

    /*
     * 获取设备通道列表
     */
    @ApiOperation("获取设备通道列表")
    @PostMapping("/getDeviceCameraList")
    public R getDeviceCameraList(@RequestBody @Valid Query query)
    {
        return cameraService.getDeviceCameraList(query);
    }

	/*
	 * 获取指定服务区的设备通道列表
	 */
	@ApiOperation("获取指定服务区的设备通道列表")
	@PostMapping("/getAreaDeviceCameraList")
	public R getAreaDeviceCameraList(@RequestBody @Valid CameraDto cameraDto)
	{
		return cameraService.getAreaDeviceCameraList(cameraDto);
	}
}
