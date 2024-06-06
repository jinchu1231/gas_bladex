package org.springblade.modules.core.entity;

import lombok.Data;

@Data
public class DeviceCamera {
    private String id;
    //设备序列号
    private String deviceSerial;
    //通道号
    private int channelNo;
    //通道名
    private String channelName;
    //在线状态：0-不在线，1-在线（该字段已废弃）
    private int status;

    private int isShared;

    //图片地址（大图），若在萤石客户端设置封面则返回封面图片，未设置则返回默认图片
    private String picUrl;
    //是否加密，0：不加密，1：加密
    private int isEncrypt;
    //视频质量：0-流畅，1-均衡，2-高清，3-超清
    private int videoLevel;
    //分享设备的权限字段
    private int permission;
    //0:隐藏，1:显示
    private int isAdd;
}
