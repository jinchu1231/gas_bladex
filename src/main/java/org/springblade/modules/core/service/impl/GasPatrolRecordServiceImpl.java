package org.springblade.modules.core.service.impl;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.entity.GasPatrolRecord;
import org.springblade.modules.core.mapper.GasPatrolRecordMapper;
import org.springblade.modules.core.service.GasPatrolRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 加气站巡查记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class GasPatrolRecordServiceImpl implements GasPatrolRecordService {
    @Autowired
    private GasPatrolRecordMapper gasPatrolRecordMapper;

    /**
     * 查询加气站巡查记录
     *
     * @param id 加气站巡查记录主键
     * @return 加气站巡查记录
     */
    @Override
    public GasPatrolRecord selectGasPatrolRecordById(Long id)
    {
        return gasPatrolRecordMapper.selectGasPatrolRecordById(id);
    }

    /**
     * 查询加气站巡查记录列表
     *
     * @param page page
     * @param gasPatrolRecord 加气站巡查记录
     * @return 加气站巡查记录
     */
    @Override
    public IPage<GasPatrolRecord> selectGasPatrolRecordList(IPage<GasPatrolRecord> page,GasPatrolRecord gasPatrolRecord)
    {
        return page.setRecords(gasPatrolRecordMapper.selectGasPatrolRecordList(page,gasPatrolRecord));
    }

    /**
     * 新增加气站巡查记录
     *
     * @param gasPatrolRecord 加气站巡查记录
     * @return 结果
     */
    @Override
    public int insertGasPatrolRecord(GasPatrolRecord gasPatrolRecord)
    {
        gasPatrolRecord.setCreateTime(new Date());
        return gasPatrolRecordMapper.insertGasPatrolRecord(gasPatrolRecord);
    }

    /**
     * 修改加气站巡查记录
     *
     * @param gasPatrolRecord 加气站巡查记录
     * @return 结果
     */
    @Override
    public int updateGasPatrolRecord(GasPatrolRecord gasPatrolRecord)
    {
        gasPatrolRecord.setUpdateTime(new Date());
        return gasPatrolRecordMapper.updateGasPatrolRecord(gasPatrolRecord);
    }

    /**
     * 批量删除加气站巡查记录
     *
     * @param ids 需要删除的加气站巡查记录主键
     * @return 结果
     */
    @Override
    public int deleteGasPatrolRecordByIds(String ids)
    {
        return gasPatrolRecordMapper.deleteGasPatrolRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除加气站巡查记录信息
     *
     * @param id 加气站巡查记录主键
     * @return 结果
     */
    @Override
    public int deleteGasPatrolRecordById(Long id)
    {
        return gasPatrolRecordMapper.deleteGasPatrolRecordById(id);
    }
}
