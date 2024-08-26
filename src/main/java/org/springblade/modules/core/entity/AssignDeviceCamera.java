package org.springblade.modules.core.entity;

import lombok.Data;

@Data
public class AssignDeviceCamera {
    //设备序列号
    private String deviceSerial;
    //IPC序列号
    private String ipcSerial;
    //通道号
    private int channelNo;
    //设备名
    private String deviceName;
    //设备上报名称
    private String localName;
    //通道名
    private String channelName;
    //在线状态：0-不在线，1-在线（该字段已废弃）
    private int status;
    //图片地址（大图），若在萤石客户端设置封面则返回封面图片，未设置则返回默认图片
    private String picUrl;
    //是否加密，0：不加密，1：加密
    private int isEncrypt;
    //视频质量：0-流畅，1-均衡，2-高清，3-超清
    private int videoLevel;
    //当前通道是否关联IPC：true-是，false-否。设备未上报或者未关联都是false
    private boolean relatedIpc;
    //0:隐藏，1:显示
    private int isAdd;
    //camera设备类型
    private String devType;

    //文档中没标识的字段，实际接口返回了
	private String id;
	private int isShared;
	private int permission;
}
