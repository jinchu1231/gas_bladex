package org.springblade.modules.core.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.mapper.GasDeviceRecordMapper;
import org.springblade.modules.core.service.GasDeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 特种设备安全检查记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class GasDeviceRecordServiceImpl implements GasDeviceRecordService {
    @Autowired
    private GasDeviceRecordMapper gasDeviceRecordMapper;

    /**
     * 查询特种设备安全检查记录
     *
     * @param id 特种设备安全检查记录主键
     * @return 特种设备安全检查记录
     */
    @Override
    public GasDeviceRecord selectGasDeviceRecordById(Long id)
    {
        return gasDeviceRecordMapper.selectGasDeviceRecordById(id);
    }

    /**
     * 查询特种设备安全检查记录列表
     *
     * @param gasDeviceRecord 特种设备安全检查记录
     * @return 特种设备安全检查记录
     */
    @Override
    public IPage<GasDeviceRecord> selectGasDeviceRecordList(IPage<GasDeviceRecord> page, GasDeviceRecord gasDeviceRecord)
    {
        return page.setRecords(gasDeviceRecordMapper.selectGasDeviceRecordList(page,gasDeviceRecord));
    }

    /**
     * 新增特种设备安全检查记录
     *
     * @param gasDeviceRecord 特种设备安全检查记录
     * @return 结果
     */
    @Override
    public int insertGasDeviceRecord(GasDeviceRecord gasDeviceRecord)
    {
        gasDeviceRecord.setCreateTime(new Date());
        return gasDeviceRecordMapper.insertGasDeviceRecord(gasDeviceRecord);
    }

    /**
     * 修改特种设备安全检查记录
     *
     * @param gasDeviceRecord 特种设备安全检查记录
     * @return 结果
     */
    @Override
    public int updateGasDeviceRecord(GasDeviceRecord gasDeviceRecord)
    {
        gasDeviceRecord.setUpdateTime(new Date());
        return gasDeviceRecordMapper.updateGasDeviceRecord(gasDeviceRecord);
    }

    /**
     * 批量删除特种设备安全检查记录
     *
     * @param ids 需要删除的特种设备安全检查记录主键
     * @return 结果
     */
    @Override
    public int deleteGasDeviceRecordByIds(String ids)
    {
        return gasDeviceRecordMapper.deleteGasDeviceRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除特种设备安全检查记录信息
     *
     * @param id 特种设备安全检查记录主键
     * @return 结果
     */
    @Override
    public int deleteGasDeviceRecordById(Long id)
    {
        return gasDeviceRecordMapper.deleteGasDeviceRecordById(id);
    }
}
