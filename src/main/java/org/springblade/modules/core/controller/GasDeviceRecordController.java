package org.springblade.modules.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.service.GasDeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 特种设备安全检查记录Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/gas-device")
@Api(value = "特种设备安全检查记录", tags = "特种设备安全检查记录接口")
public class GasDeviceRecordController {

    @Autowired
    private GasDeviceRecordService gasDeviceRecordService;

    /**
     * 查询特种设备安全检查记录列表
     */
    @PostMapping("/list")
    public R<IPage<GasDeviceRecord>> list(@RequestBody @Valid GasDeviceRecord gasDeviceRecord,
						@Valid @RequestBody Query query) {
		IPage<GasDeviceRecord> gasDeviceRecordIPage =
			gasDeviceRecordService.selectGasDeviceRecordList(Condition.getPage(query), gasDeviceRecord);
		return R.data(gasDeviceRecordIPage);
    }

    /**
     * 导出特种设备安全检查记录列表
     */
/*    @PostMapping("/export")
    public R export(GasDeviceRecord gasDeviceRecord)
    {
        List<GasDeviceRecord> list = gasDeviceRecordService.selectGasDeviceRecordList(gasDeviceRecord);
        ExcelUtil<GasDeviceRecord> util = new ExcelUtil<GasDeviceRecord>(GasDeviceRecord.class);
        return util.exportExcel(list, "特种设备安全检查记录数据");
    }*/


    /**
     * 新增保存特种设备安全检查记录
     */
    @PostMapping("/save")
    public R save(@RequestBody @Valid GasDeviceRecord gasDeviceRecord)
    {
        return R.data(gasDeviceRecordService.insertGasDeviceRecord(gasDeviceRecord));
    }


    /**
     * 修改保存特种设备安全检查记录
     */
    @PostMapping("/update")
    public R update(@RequestBody @Valid GasDeviceRecord gasDeviceRecord)
    {
        return R.data(gasDeviceRecordService.updateGasDeviceRecord(gasDeviceRecord));
    }

    /**
     * 删除特种设备安全检查记录
     */
    @PostMapping( "/remove")
    public R remove(String ids)
    {
        return R.data(gasDeviceRecordService.deleteGasDeviceRecordByIds(ids));
    }
}
