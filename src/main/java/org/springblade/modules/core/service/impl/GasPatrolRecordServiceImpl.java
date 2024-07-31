package org.springblade.modules.core.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springblade.modules.core.dto.GasPatrolRecordDto;
import org.springblade.modules.core.dto.patrol.RecordDto;
import org.springblade.modules.core.entity.GasPatrolRecord;
import org.springblade.modules.core.mapper.GasPatrolRecordMapper;
import org.springblade.modules.core.service.GasPatrolRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 加气站巡查记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class GasPatrolRecordServiceImpl extends ServiceImpl<GasPatrolRecordMapper, GasPatrolRecord> implements GasPatrolRecordService {
    @Autowired
    private GasPatrolRecordMapper gasPatrolRecordMapper;

    /**
     * 查询加气站巡查记录
     *
     * @param id 加气站巡查记录主键
     * @return 加气站巡查记录
     */
    @Override
    public GasPatrolRecordDto selectGasPatrolRecordById(Long id)
    {
		GasPatrolRecordDto dto = new GasPatrolRecordDto();
		GasPatrolRecord gasPatrolRecords = gasPatrolRecordMapper.selectGasPatrolRecordById(id);
		ObjectMapper mapper = new ObjectMapper();
		List<RecordDto> recordDtoList = null;
		try {
			recordDtoList = mapper.readValue(gasPatrolRecords.getContent(), new TypeReference<List<RecordDto>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		dto.setGasId(gasPatrolRecords.getGasId());
		dto.setGasName(gasPatrolRecords.getGasName());
		dto.setFillDate(gasPatrolRecords.getFillDate());
		dto.setRecordDtoList(recordDtoList);
		dto.setPrincipal(gasPatrolRecords.getPrincipal());
		dto.setReviewPerson(gasPatrolRecords.getReviewPerson());
		dto.setAbarbeitung(gasPatrolRecords.getAbarbeitung());
		dto.setFileUrl(gasPatrolRecords.getFileUrl());
		return dto;
    }

    /**
     * 查询加气站巡查记录列表
     *
     * @param page page
     * @param gasPatrolRecord 加气站巡查记录
     * @return 加气站巡查记录
     */
    @Override
    public IPage<GasPatrolRecord> selectGasPatrolRecordList(IPage<GasPatrolRecord> page,GasPatrolRecord gasPatrolRecord)
    {
        return page.setRecords(gasPatrolRecordMapper.selectGasPatrolRecordList(page,gasPatrolRecord));
    }

    /**
     * 新增加气站巡查记录
     *
     * @param gasPatrolRecords 加气站巡查记录
     * @return 结果
     */
    @Override
    public int insertGasPatrolRecord(GasPatrolRecordDto gasPatrolRecords)
    {
		GasPatrolRecord gasPatrolRecord = new GasPatrolRecord();
		gasPatrolRecord.setGasId(gasPatrolRecords.getGasId());
		gasPatrolRecord.setGasName(gasPatrolRecords.getGasName());
		gasPatrolRecord.setFillDate(gasPatrolRecords.getFillDate());
		gasPatrolRecord.setPrincipal(gasPatrolRecords.getPrincipal());
		gasPatrolRecord.setReviewPerson(gasPatrolRecords.getReviewPerson());
		gasPatrolRecord.setAbarbeitung(gasPatrolRecords.getAbarbeitung());
		gasPatrolRecord.setCreateTime(new Date());
		gasPatrolRecord.setContent(JSON.toJSONString(gasPatrolRecords.getRecordDtoList()));
		gasPatrolRecord.setFileUrl(gasPatrolRecords.getFileUrl());
		return gasPatrolRecordMapper.insertGasPatrolRecord(gasPatrolRecord);
    }

    /**
     * 修改加气站巡查记录
     *
     * @param gasPatrolRecords 加气站巡查记录
     * @return 结果
     */
    @Override
    public int updateGasPatrolRecord(GasPatrolRecordDto gasPatrolRecords)
    {
		GasPatrolRecord gasPatrolRecord = new GasPatrolRecord();
		gasPatrolRecord.setId(gasPatrolRecords.getId());
		gasPatrolRecord.setGasId(gasPatrolRecords.getGasId());
		gasPatrolRecord.setGasName(gasPatrolRecords.getGasName());
		gasPatrolRecord.setFillDate(gasPatrolRecords.getFillDate());
		gasPatrolRecord.setPrincipal(gasPatrolRecords.getPrincipal());
		gasPatrolRecord.setReviewPerson(gasPatrolRecords.getReviewPerson());
		gasPatrolRecord.setAbarbeitung(gasPatrolRecords.getAbarbeitung());
		gasPatrolRecord.setUpdateTime(new Date());
		gasPatrolRecord.setContent(JSON.toJSONString(gasPatrolRecords.getRecordDtoList()));
		gasPatrolRecord.setFileUrl(gasPatrolRecords.getFileUrl());
		return gasPatrolRecordMapper.updateGasPatrolRecord(gasPatrolRecord);
    }

    /**
     * 批量删除加气站巡查记录
     *
     * @param ids 需要删除的加气站巡查记录主键
     * @return 结果
     */
    @Override
    public int deleteGasPatrolRecordByIds(String ids)
    {
        return gasPatrolRecordMapper.deleteGasPatrolRecordByIds(ids);
    }

	@Override
	public List<GasPatrolRecord> selectGasPatrolRecordAllList() {
		return gasPatrolRecordMapper.selectGasPatrolRecordAllList();
	}

	@Override
	public int updateFileUrlById(GasPatrolRecordDto gasPatrolRecords) {
		return gasPatrolRecordMapper.updateFileUrlById(gasPatrolRecords.getId().toString(), gasPatrolRecords.getFileUrl());
	}
}
