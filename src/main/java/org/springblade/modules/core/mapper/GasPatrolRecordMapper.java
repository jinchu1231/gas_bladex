package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.entity.GasPatrolRecord;

import java.util.List;

/**
 * 加气站巡查记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasPatrolRecordMapper
{
    /**
     * 查询加气站巡查记录
     *
     * @param id 加气站巡查记录主键
     * @return 加气站巡查记录
     */
    public GasPatrolRecord selectGasPatrolRecordById(Long id);

    /**
     * 查询加气站巡查记录列表
     *
     * @param gasPatrolRecord 加气站巡查记录
     * @return 加气站巡查记录集合
     */
    public List<GasPatrolRecord> selectGasPatrolRecordList(IPage page, GasPatrolRecord gasPatrolRecord);

    /**
     * 新增加气站巡查记录
     *
     * @param gasPatrolRecord 加气站巡查记录
     * @return 结果
     */
    public int insertGasPatrolRecord(GasPatrolRecord gasPatrolRecord);

    /**
     * 修改加气站巡查记录
     *
     * @param gasPatrolRecord 加气站巡查记录
     * @return 结果
     */
    public int updateGasPatrolRecord(GasPatrolRecord gasPatrolRecord);

    /**
     * 删除加气站巡查记录
     *
     * @param id 加气站巡查记录主键
     * @return 结果
     */
    public int deleteGasPatrolRecordById(Long id);

    /**
     * 批量删除加气站巡查记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGasPatrolRecordByIds(String[] ids);
}
