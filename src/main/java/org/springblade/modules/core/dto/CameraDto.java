package org.springblade.modules.core.dto;

import lombok.Data;

@Data
public class CameraDto {
    private static final long serialVersionUID = 1L;

    /**
     * 设备序列号
     */
    private String deviceSerial;
    /**
     * 通道号
     */
    private String channelNo;

    public CameraDto(){}
}
