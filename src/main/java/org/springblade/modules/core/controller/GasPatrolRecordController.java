package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.entity.GasPatrolRecord;
import org.springblade.modules.core.service.GasPatrolRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 加气站巡查记录Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/gas-patrol")
@Api(value = "加气站巡查记录", tags = "加气站巡查记录接口")
public class GasPatrolRecordController {

    @Autowired
    private GasPatrolRecordService gasPatrolRecordService;

    /**
     * 查询加气站巡查记录列表
     */
    @PostMapping("/list")
    public R<IPage<GasPatrolRecord>> list(@RequestBody @Valid GasPatrolRecord gasPatrolRecord,
				  @Valid @RequestBody Query query)
    {
		IPage<GasPatrolRecord> list =
			gasPatrolRecordService.selectGasPatrolRecordList(Condition.getPage(query),gasPatrolRecord);
        return R.data(list);
    }

    /**
     * 导出加气站巡查记录列表
     */
  /*  @PostMapping("/export")
    public AjaxResult export(GasPatrolRecord gasPatrolRecord)
    {
        List<GasPatrolRecord> list = gasPatrolRecordService.selectGasPatrolRecordList(gasPatrolRecord);
        ExcelUtil<GasPatrolRecord> util = new ExcelUtil<GasPatrolRecord>(GasPatrolRecord.class);
        return util.exportExcel(list, "加气站巡查记录数据");
    }*/


    /**
     * 新增保存加气站巡查记录
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasPatrolRecord gasPatrolRecord)
    {
        return R.data(gasPatrolRecordService.insertGasPatrolRecord(gasPatrolRecord));
    }


    /**
     * 修改保存加气站巡查记录
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasPatrolRecord gasPatrolRecord)
    {
        return R.data(gasPatrolRecordService.updateGasPatrolRecord(gasPatrolRecord));
    }

    /**
     * 删除加气站巡查记录
     */
    @PostMapping( "/remove")
    public R remove(String ids)
    {
        return R.data(gasPatrolRecordService.deleteGasPatrolRecordByIds(ids));
    }
}
