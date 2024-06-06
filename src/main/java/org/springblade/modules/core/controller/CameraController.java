package org.springblade.modules.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/gas/camera")
@Api(value = "危化品车辆日统计表", tags = "危化品车辆日统计表接口")
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
     * 获取设备列表
     */
    @ApiOperation("获取设备列表")
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
}
