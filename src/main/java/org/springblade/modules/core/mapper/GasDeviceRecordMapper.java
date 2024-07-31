package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;

import java.util.List;

/**
 * 特种设备安全检查记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasDeviceRecordMapper extends BaseMapper<GasDeviceRecord> {
    /**
     * 查询特种设备安全检查记录
     *
     * @param id 特种设备安全检查记录主键
     * @return 特种设备安全检查记录
     */
    public GasDeviceRecord selectGasDeviceRecordById(Long id);

    /**
     * 查询特种设备安全检查记录列表
     *
     * @param gasDeviceRecord 特种设备安全检查记录
     * @return 特种设备安全检查记录集合
     */
    public List<GasDeviceRecord> selectGasDeviceRecordList(IPage page,@Param("re") GasDeviceRecord gasDeviceRecord);

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
    public int updateGasDeviceRecord(@Param("gas") GasDeviceRecord gasDeviceRecord);

    /**
     * 删除特种设备安全检查记录
     *
     * @param id 特种设备安全检查记录主键
     * @return 结果
     */
    public int deleteGasDeviceRecordById(String id);

    List<GasDeviceRecord> selectGasDeviceRecordAllList();

	int updateFileUrlById(@Param("id") String id, @Param("url") String fileUrl);
}
