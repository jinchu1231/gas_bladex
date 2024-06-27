package org.springblade.modules.core.service.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.core.dto.GasDeviceRecordDto;
import org.springblade.modules.core.entity.Device;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.excel.DeviceRecordExcel;
import org.springblade.modules.core.mapper.GasDeviceRecordMapper;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springblade.modules.core.service.GasDeviceRecordService;
import org.springblade.modules.core.util.ExcelUtil;
import org.springblade.modules.core.vo.DeviceRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 特种设备安全检查记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class GasDeviceRecordServiceImpl extends ServiceImpl<GasDeviceRecordMapper, GasDeviceRecord> implements GasDeviceRecordService {
    @Autowired
    private GasDeviceRecordMapper gasDeviceRecordMapper;

	@Autowired
	private GasBaseInfoService gasBaseInfoService;

    /**
     * 查询特种设备安全检查记录
     *
     * @param id 特种设备安全检查记录主键
     * @return 特种设备安全检查记录
     */
    @Override
    public GasDeviceRecordDto selectGasDeviceRecordById(Long id)
    {
		GasDeviceRecordDto dto = new GasDeviceRecordDto();
		GasDeviceRecord gasDeviceRecord = gasDeviceRecordMapper.selectGasDeviceRecordById(id);
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<DeviceRecordVo> deviceRecordVos = mapper.readValue(gasDeviceRecord.getContent(), new TypeReference<List<DeviceRecordVo>>() {});
			dto.setList(deviceRecordVos);
			dto.setGasId(gasDeviceRecord.getGasId());
			dto.setGasName(gasDeviceRecord.getGasName());
			dto.setInspectName(gasDeviceRecord.getInspectName());
			dto.setSafetyOfficer(gasDeviceRecord.getSafetyOfficer());
			dto.setTakeSteps(gasDeviceRecord.getTakeSteps());
			dto.setInspectData(gasDeviceRecord.getInspectData());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return dto;
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
     * 删除特种设备安全检查记录信息
     *
     * @param id 特种设备安全检查记录主键
     * @return 结果
     */
    @Override
    public int deleteGasDeviceRecordById(String id)
    {
        return gasDeviceRecordMapper.deleteGasDeviceRecordById(id);
    }

	@Override
	public List<GasDeviceRecordDto> selectGasDeviceRecordAllList() {
		List<GasDeviceRecord> records = gasDeviceRecordMapper.selectGasDeviceRecordAllList();
//		List<DeviceRecordExcel> excels = new ArrayList<>();
		List<GasDeviceRecordDto> excels = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		records.forEach(record -> {
			try {
				GasDeviceRecordDto excel = new GasDeviceRecordDto();
				List<DeviceRecordVo> deviceRecordVos = mapper.readValue(record.getContent(), new TypeReference<List<DeviceRecordVo>>() {});
				excel.setList(deviceRecordVos);
				excel.setGasName(record.getGasName());
				excel.setInspectName(record.getInspectName());
				excel.setSafetyOfficer(record.getSafetyOfficer());
				excel.setTakeSteps(record.getTakeSteps());
				excel.setInspectData(record.getInspectData());
				excels.add(excel);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});

		/*records.forEach(record -> {
			String inspectItem = "";
			String inspectContent = "";
			String inspectResult = "";
			String result = "";
			String remark = "";
			try {
				DeviceRecordExcel excel = new DeviceRecordExcel();
				List<DeviceRecordVo> deviceRecordVos = mapper.readValue(record.getContent(), new TypeReference<List<DeviceRecordVo>>() {});
				for (DeviceRecordVo deviceRecordVo : deviceRecordVos) {
					inspectItem = inspectItem + deviceRecordVo.getInspectItem() + "\n";
					inspectContent = inspectContent + deviceRecordVo.getInspectContent() + "\n";
					inspectResult = inspectResult + deviceRecordVo.getInspectResult() + "\n";
					result = result + deviceRecordVo.getResult() + "\n";
					remark = remark + deviceRecordVo.getRemark() + "\n";
				}
				excel.setGasName(record.getGasName());
				excel.setInspectName(record.getInspectName());
				excel.setSafetyOfficer(record.getSafetyOfficer());
				excel.setTakeSteps(record.getTakeSteps());
				excel.setInspectData(record.getInspectData());
				excel.setInspectItem(inspectItem);
				excel.setInspectContent(inspectContent);
				excel.setInspectResult(inspectResult);
				excel.setResult(result);
				excel.setRemark(remark);
				excels.add(excel);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});*/
		return excels;
	}

	@Override
	public int insertGasDevice(GasDeviceRecordDto gasDeviceRecordDto) {
		if (!StringUtils.isEmpty(gasDeviceRecordDto.getInspectData())){
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(gasDeviceRecordDto.getInspectData());
			String dateOnly = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			gasDeviceRecordDto.setInspectData(dateOnly);
		}
		GasDeviceRecord gasDeviceRecord = new GasDeviceRecord();
		gasDeviceRecord.setContent(JSON.toJSONString(gasDeviceRecordDto.getList()));
		gasDeviceRecord.setGasId(gasDeviceRecordDto.getGasId());
		gasDeviceRecord.setGasName(gasDeviceRecordDto.getGasName());
		gasDeviceRecord.setSafetyOfficer(gasDeviceRecordDto.getSafetyOfficer());
		gasDeviceRecord.setTakeSteps(gasDeviceRecordDto.getTakeSteps());
		gasDeviceRecord.setInspectData(gasDeviceRecordDto.getInspectData());
		gasDeviceRecord.setInspectName(gasDeviceRecordDto.getInspectName());
		gasDeviceRecordMapper.insertGasDeviceRecord(gasDeviceRecord);
		return 1;
	}

	@Override
	public int updateGasDevice(GasDeviceRecordDto gasDeviceRecordDto) {
		if (!StringUtils.isEmpty(gasDeviceRecordDto.getInspectData())){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(gasDeviceRecordDto.getInspectData(), formatter);
			gasDeviceRecordDto.setInspectData(String.valueOf(date));
		}
		GasDeviceRecord gasDeviceRecord = new GasDeviceRecord();
		gasDeviceRecord.setContent(JSON.toJSONString(gasDeviceRecordDto.getList()));
		gasDeviceRecord.setId(Long.valueOf(gasDeviceRecordDto.getId()));
		gasDeviceRecord.setGasId(gasDeviceRecordDto.getGasId());
		gasDeviceRecord.setGasName(gasDeviceRecordDto.getGasName());
		gasDeviceRecord.setSafetyOfficer(gasDeviceRecordDto.getSafetyOfficer());
		gasDeviceRecord.setTakeSteps(gasDeviceRecordDto.getTakeSteps());
		gasDeviceRecord.setInspectData(gasDeviceRecordDto.getInspectData());
		gasDeviceRecord.setInspectName(gasDeviceRecordDto.getInspectName());
		gasDeviceRecordMapper.updateGasDeviceRecord(gasDeviceRecord);
		return 1;
	}

	@Override
	public void writeNotice(MultipartFile file) {
		List<DeviceRecordExcel> list = ExcelUtil.read(file, DeviceRecordExcel.class);
		List<DeviceRecordVo> records = new ArrayList<>();
		String name = "";
		String inspectName = "";
		String inspectData = "";
		String takeSteps = "";
		String safetyOfficer = "";
		//每7条为一组数据
		for (int i = 1; i < list.size(); i++) {
			DeviceRecordExcel record = list.get(i);
			DeviceRecordVo deviceRecordVo = new DeviceRecordVo();
			deviceRecordVo.setInspectItem(record.getInspectItem());
			deviceRecordVo.setRemark(record.getRemark());
			deviceRecordVo.setResult(record.getResult());
			deviceRecordVo.setInspectContent(record.getInspectContent());
			deviceRecordVo.setInspectResult(record.getInspectResult());
			records.add(deviceRecordVo);
			if (!StringUtils.isEmpty(record.getGasName())){
				name = record.getGasName();
			}
			if (!StringUtils.isEmpty(record.getInspectName())){
				inspectName = record.getInspectName();
			}
			if (!StringUtils.isEmpty(record.getInspectData())){
				inspectData = record.getInspectData();
			}
			if (!StringUtils.isEmpty(record.getTakeSteps())){
				takeSteps = record.getTakeSteps();
			}
			if (!StringUtils.isEmpty(record.getSafetyOfficer())){
				safetyOfficer = record.getSafetyOfficer();
			}

			//每7条数据保存一次
			if (i % 7 == 0) {
				GasDeviceRecord gasDeviceRecord = new GasDeviceRecord();
				String gasId = gasBaseInfoService.selectIdByName(name);
				gasDeviceRecord.setGasId(gasId);
				gasDeviceRecord.setGasName(name);
				gasDeviceRecord.setContent(JSON.toJSONString(records));
				gasDeviceRecord.setInspectName(inspectName);
				if ("".equals(inspectData)){
					gasDeviceRecord.setInspectData(LocalDate.now().toString());
				}
				gasDeviceRecord.setTakeSteps(takeSteps);
				gasDeviceRecord.setSafetyOfficer(safetyOfficer);
				insertGasDeviceRecord(gasDeviceRecord);
				records.clear();
			}
		}
	}
}
