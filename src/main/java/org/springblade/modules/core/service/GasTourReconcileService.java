package org.springblade.modules.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.entity.GasTourReconcile;

import java.util.List;

/**
 * 交班对账Service接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasTourReconcileService
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
    public List<GasTourReconcileDto> selectGasTourReconcileList(IPage<GasTourReconcile> page, GasTourReconcile gasTourReconcile);

    /**
     * 新增交班对账
     *
     * @param dto 交班对账
     * @return 结果
     */
    public int insertGasTourReconcile(GasTourReconcileDto dto);

    /**
     * 修改交班对账
     *
     * @param dto 交班对账
     * @return 结果
     */
    public int updateGasTourReconcile(GasTourReconcileDto dto);

    /**
     * 批量删除交班对账
     *
     * @param ids 需要删除的交班对账主键集合
     * @return 结果
     */
    public int deleteGasTourReconcileByIds(String ids);

    /**
     * 删除交班对账信息
     *
     * @param id 交班对账主键
     * @return 结果
     */
    public int deleteGasTourReconcileById(Long id);
}
