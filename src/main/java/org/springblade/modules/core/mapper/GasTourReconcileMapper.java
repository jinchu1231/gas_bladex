package org.springblade.modules.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import liquibase.pro.packaged.P;
import org.apache.ibatis.annotations.Param;
import org.springblade.modules.core.dto.GasTourReconcileDto;
import org.springblade.modules.core.dto.dapin.DayPriceDto;
import org.springblade.modules.core.dto.dapin.PriceServerTrendDto;
import org.springblade.modules.core.dto.dapin.StoredValueDto;
import org.springblade.modules.core.entity.GasTourReconcile;
import org.springblade.modules.core.excel.GasTourReconcileExcelDto;

import java.util.List;

/**
 * 交班对账Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GasTourReconcileMapper extends BaseMapper<GasTourReconcile>
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
    public List<GasTourReconcile> selectGasTourReconcileList(IPage page,@Param("gas") GasTourReconcile gasTourReconcile);

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
    public int updateGasTourReconcile(@Param("gas") GasTourReconcile gasTourReconcile);

    /**
     * 删除交班对账
     *
     * @param id 交班对账主键
     * @return 结果
     */
    public int deleteGasTourReconcileById(String id);

	/**
	 * 查询交班对账列表
	 *
	 * @return 交班对账集合
	 */
	public List<GasTourReconcile> selectAllGasTourReconcileList();

    List<DayPriceDto> revenueTrend(String id);

	List<DayPriceDto> inventoryTrend(String id);

	List<StoredValueDto> allRevenueTrend(@Param("time")String time, @Param("endTime") String endTime);

	List<StoredValueDto> allRevenueTrendYear(@Param("firstDayOfYearAsString") String firstDayOfYearAsString, @Param("today") String today);

	List<StoredValueDto> allInventoryTrend(@Param("firstDayOfMonth") String firstDayOfMonth, @Param("todayAsString") String todayAsString);

	List<StoredValueDto> allStoredCalueTrend(@Param("firstDayOfMonth") String firstDayOfMonth, @Param("firstDayOfNextMonth") String firstDayOfNextMonth);
}
