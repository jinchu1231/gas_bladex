package org.springblade.modules.core.service;




import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.core.dto.GasPatrolRecordDto;
import org.springblade.modules.core.entity.GasPatrolRecord;

import java.util.List;

/**
 * 加气站巡查记录Service接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasPatrolRecordService extends IService<GasPatrolRecord>
{
    /**
     * 查询加气站巡查记录
     *
     * @param id 加气站巡查记录主键
     * @return 加气站巡查记录
     */
    public GasPatrolRecordDto selectGasPatrolRecordById(Long id);

    /**
     * 查询加气站巡查记录列表
     *
     * @param gasPatrolRecord 加气站巡查记录
     * @return 加气站巡查记录集合
     */
    public IPage<GasPatrolRecord> selectGasPatrolRecordList(IPage<GasPatrolRecord> page,GasPatrolRecord gasPatrolRecord);

    /**
     * 新增加气站巡查记录
     *
     * @param gasPatrolRecord 加气站巡查记录
     * @return 结果
     */
    public int insertGasPatrolRecord(GasPatrolRecordDto gasPatrolRecord);

    /**
     * 修改加气站巡查记录
     *
     * @param gasPatrolRecords 加气站巡查记录
     * @return 结果
     */
    public int updateGasPatrolRecord(GasPatrolRecordDto gasPatrolRecords);

    /**
     * 批量删除加气站巡查记录
     *
     * @param ids 需要删除的加气站巡查记录主键集合
     * @return 结果
     */
    public int deleteGasPatrolRecordByIds(String ids);

	List<GasPatrolRecord> selectGasPatrolRecordAllList();
}
