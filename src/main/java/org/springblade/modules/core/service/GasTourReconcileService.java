package org.springblade.modules.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.dto.dapin.PriceServerTrendDto;
import org.springblade.modules.core.dto.tour.GasTourReconcileSaveDto;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 交班对账Service接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasTourReconcileService extends IService<GasTourReconcile>
{
    /**
     * 查询交班对账
     *
     * @param id 交班对账主键
     * @return 交班对账
     */
    public GasTourReconcileSaveDto selectGasTourReconcileById(Long id);

    /**
     * 查询交班对账列表
     *
     * @param gasTourReconcile 交班对账
     * @return 交班对账集合
     */
    public IPage<GasTourReconcile> selectGasTourReconcileList(IPage<GasTourReconcile> page, GasTourReconcile gasTourReconcile);

    /**
     * 新增交班对账
     *
     * @param dto 交班对账
     * @return 结果
     */
    public int insertGasTourReconcile(GasTourReconcileSaveDto dto);

    /**
     * 修改交班对账
     *
     * @param dto 交班对账
     * @return 结果
     */
    public int updateGasTourReconcile(GasTourReconcileSaveDto dto);

    /**
     * 删除交班对账信息
     *
     * @param id 交班对账主键
     * @return 结果
     */
    public int deleteGasTourReconcileById(String id);


	List<GasTourReconcileExcelDto> selectAllGasTourReconcileList();


	GasTourReconcileExcelDto writeNotice(MultipartFile file);

	PriceServerTrendDto revenueTrend(String id);

	PriceServerTrendDto inventoryTrend(String id);

	PriceServerTrendDto allRevenueTrend(String type);

	PriceServerTrendDto allInventoryTrend(String type);

	PriceServerTrendDto allStoredCalueTrend(String type);
}
