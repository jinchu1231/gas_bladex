package org.springblade.modules.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.core.dto.GasDeviceRecordDto;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.excel.DeviceRecordExcel;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 特种设备安全检查记录Service接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasDeviceRecordService extends IService<GasDeviceRecord> {
    /**
     * 查询特种设备安全检查记录
     *
     * @param id 特种设备安全检查记录主键
     * @return 特种设备安全检查记录
     */
    public GasDeviceRecordDto selectGasDeviceRecordById(Long id);

    /**
     * 查询特种设备安全检查记录列表
     *
     * @param gasDeviceRecord 特种设备安全检查记录
     * @return 特种设备安全检查记录集合
     */
    public IPage<GasDeviceRecord> selectGasDeviceRecordList(IPage<GasDeviceRecord> page, GasDeviceRecord gasDeviceRecord);

    /**
     * 新增特种设备安全检查记录
     *
     * @param gasDeviceRecord 特种设备安全检查记录
     * @return 结果
     */
    public int insertGasDeviceRecord(GasDeviceRecord gasDeviceRecord);

    /**
     * 修改特种设备安全检查记录
     *
     * @param gasDeviceRecord 特种设备安全检查记录
     * @return 结果
     */
    public int updateGasDeviceRecord(GasDeviceRecord gasDeviceRecord);

    /**
     * 删除特种设备安全检查记录信息
     *
     * @param id 特种设备安全检查记录主键
     * @return 结果
     */
    public int deleteGasDeviceRecordById(String id);

	List<GasDeviceRecordDto> selectGasDeviceRecordAllList();

	int insertGasDevice(GasDeviceRecordDto gasDeviceRecordDto);

	int updateGasDevice(GasDeviceRecordDto gasDeviceRecordDto);

	void writeNotice(MultipartFile file);
}
