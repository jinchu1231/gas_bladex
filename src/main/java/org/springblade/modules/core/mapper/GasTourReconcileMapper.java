package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.entity.GasTourReconcile;

import java.util.List;

/**
 * 交班对账Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasTourReconcileMapper
{
    /**
     * 查询交班对账
     *
     * @param id 交班对账主键
     * @return 交班对账
     */
    public GasTourReconcile selectGasTourReconcileById(Long id);

    /**
     * 查询交班对账列表
     *
     * @param gasTourReconcile 交班对账
     * @return 交班对账集合
     */
    public List<GasTourReconcile> selectGasTourReconcileList(IPage page, GasTourReconcile gasTourReconcile);

    /**
     * 新增交班对账
     *
     * @param gasTourReconcile 交班对账
     * @return 结果
     */
    public int insertGasTourReconcile(GasTourReconcile gasTourReconcile);

    /**
     * 修改交班对账
     *
     * @param gasTourReconcile 交班对账
     * @return 结果
     */
    public int updateGasTourReconcile(GasTourReconcile gasTourReconcile);

    /**
     * 删除交班对账
     *
     * @param id 交班对账主键
     * @return 结果
     */
    public int deleteGasTourReconcileById(Long id);

    /**
     * 批量删除交班对账
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGasTourReconcileByIds(String[] ids);
}
