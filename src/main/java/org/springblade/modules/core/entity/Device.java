package org.springblade.modules.core.entity;

import lombok.Data;

@Data
public class Device {
    //	条目索引
    private String id;
    //设备序列号
    private String deviceSerial;
    //设备名称
    private String deviceName;
    //设备型号
    private String deviceType;
    //设备在线状态，1-在线；0-离线
    private int status;
    //布撤防状态
    private int defence;
    //固件版本号
    private String deviceVersion;
    //用户添加时间
    private long addTime;
    //	设备最后更新时间
    private long updateTime;
    //	设备二级类目名称
    private String parentCategory;
    //	设备风险安全等级，0-安全；大于0，有风险，风险越高，值越大
    private int riskLevel;
    //设备IP地址
    private String netAddress;
}
