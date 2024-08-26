package org.springblade.modules.core.dto;

import lombok.Data;
import org.springblade.core.mp.support.Query;

@Data
public class CameraDto extends Query {
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
