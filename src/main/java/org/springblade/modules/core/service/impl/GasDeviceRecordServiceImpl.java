package org.springblade.modules.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spire.xls.Workbook;
import org.apache.commons.lang3.StringUtils;
import org.springblade.common.utils.FindAndReplaceData;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.modules.core.dto.GasDeviceRecordDto;
import org.springblade.modules.core.entity.Camera;
import org.springblade.modules.core.entity.DeviceCamera;
import org.springblade.modules.core.entity.GasDeviceRecord;
import org.springblade.modules.core.mapper.GasDeviceRecordMapper;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springblade.modules.core.service.GasDeviceRecordService;
import org.springblade.modules.core.vo.DeviceRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
			dto.setFileUrl(gasDeviceRecord.getFileUrl());
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
		IPage<GasDeviceRecord> gasDeviceRecordIPage = page.setRecords(gasDeviceRecordMapper.selectGasDeviceRecordList(page, gasDeviceRecord));
		gasDeviceRecordIPage.getRecords().forEach(record -> {
			if(!StringUtils.isEmpty(record.getFileUrl())){
				ObjectMapper mapper = new ObjectMapper();
				List<String> fileList = new ArrayList<>();
				try {
					fileList = mapper.readValue(record.getFileUrl(), new TypeReference<List<String>>(){});
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				record.setFileUrlList(fileList);
			}

		});
		return gasDeviceRecordIPage;
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
			gasDeviceRecordDto.setInspectData(gasDeviceRecordDto.getInspectData());
		}
		GasDeviceRecord gasDeviceRecord = new GasDeviceRecord();
		gasDeviceRecord.setContent(JSON.toJSONString(gasDeviceRecordDto.getList()));
		gasDeviceRecord.setGasId(gasDeviceRecordDto.getGasId());
		gasDeviceRecord.setGasName(gasDeviceRecordDto.getGasName());
		gasDeviceRecord.setSafetyOfficer(gasDeviceRecordDto.getSafetyOfficer());
		gasDeviceRecord.setTakeSteps(gasDeviceRecordDto.getTakeSteps());
		gasDeviceRecord.setInspectData(gasDeviceRecordDto.getInspectData());
		gasDeviceRecord.setInspectName(gasDeviceRecordDto.getInspectName());
		gasDeviceRecord.setCreateUser(AuthUtil.getUserId().toString());
		gasDeviceRecordMapper.insertGasDeviceRecord(gasDeviceRecord);
		return 1;
	}

	@Override
	public int updateGasDevice(GasDeviceRecordDto gasDeviceRecordDto) {
		if (!StringUtils.isEmpty(gasDeviceRecordDto.getInspectData())){
			gasDeviceRecordDto.setInspectData(gasDeviceRecordDto.getInspectData());
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
		return gasDeviceRecordMapper.updateGasDeviceRecord(gasDeviceRecord);
	}

	@Override
	public GasDeviceRecord writeNotice(MultipartFile file) {
		Workbook workbook = new Workbook();
		try {
			// 创建一个临时文件
			File tempFile = File.createTempFile("temp", ".xlsx");
			tempFile.deleteOnExit(); // 确保程序结束后删除临时文件

			// 将 MultipartFile 的内容写入临时文件
			try (InputStream inputStream = file.getInputStream();
				 OutputStream outputStream = new FileOutputStream(tempFile)) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			}
			// 使用 Spire.XLS 加载临时文件
			workbook.loadFromFile(tempFile.getAbsolutePath());
		}catch (IOException e) {
			e.printStackTrace();
		}

		GasDeviceRecord dto = new GasDeviceRecord();
		List<DeviceRecordVo> contentList = new ArrayList<>();
		ArrayList<String[]> strings = FindAndReplaceData.readRows(workbook, 0);
		for (int i = 0; i < strings.size(); i++) {
			String[] string = strings.get(i);
			System.out.println("第一层数据打印：" + JSON.toJSONString(string));
			for (int j = 0; j < string.length; j++) {
				if ("站点".equals(string[j])) {
					dto.setGasName(string[j + 1]);
					dto.setGasId(gasBaseInfoService.selectIdByName(dto.getGasName()));
				}
				if ("检查日期".equals(string[j])){
					dto.setInspectData(string[j + 1]);
				}

				if (string[j].equals("序号")){
					for (int k = i + 1; k < strings.size(); k++) {
						if (strings.get(k)[0].equals("采取的防范措施")){
							break;
						}
						DeviceRecordVo deviceRecordVo = new DeviceRecordVo();
						deviceRecordVo.setInspectItem(strings.get(k)[1]);
						deviceRecordVo.setInspectContent(strings.get(k)[2]);
						deviceRecordVo.setInspectResult(strings.get(k)[3]);
						deviceRecordVo.setResult(strings.get(k)[4]);
						deviceRecordVo.setRemark(strings.get(k)[5]);
						contentList.add(deviceRecordVo);
					}
					dto.setContent(JSON.toJSONString(contentList));
				}

				if ("采取的防范措施".equals(string[j])){
					dto.setTakeSteps(string[j + 1]);
				}
				if ("安全员".equals(string[j])){
					dto.setSafetyOfficer(string[j + 1]);
				}
				System.out.println("第二次数据打印：" + string[j]);
			}
		}
		return dto;
	}

	@Override
	public int updateFileUrlById(GasDeviceRecordDto gasDeviceRecordDto) {
		GasDeviceRecord gasDeviceRecord = gasDeviceRecordMapper.selectGasDeviceRecordById(Long.valueOf(gasDeviceRecordDto.getId()));
		String fileUrl = gasDeviceRecord.getFileUrl();
		if (StringUtils.isEmpty(fileUrl)){
			return gasDeviceRecordMapper.updateFileUrlById(gasDeviceRecordDto.getId(), JSON.toJSONString(gasDeviceRecordDto.getFileUrlList()));
		}
		ObjectMapper mapper = new ObjectMapper();
		List<String> fileList = new ArrayList<>();
		try {
			fileList = mapper.readValue(fileUrl, new TypeReference<List<String>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		fileList.addAll(gasDeviceRecordDto.getFileUrlList());
		return gasDeviceRecordMapper.updateFileUrlById(gasDeviceRecordDto.getId(), JSON.toJSONString(fileList));
	}
}
