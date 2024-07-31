/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.flow.demo.leave.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.IFlowService;
import org.springblade.flow.core.constant.ProcessConstant;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.utils.FlowUtil;
import org.springblade.flow.core.utils.TaskUtil;
import org.springblade.flow.demo.leave.entity.ProcessLeave;
import org.springblade.flow.demo.leave.mapper.LeaveMapper;
import org.springblade.flow.demo.leave.service.ILeaveService;
import org.springblade.flow.engine.service.FlowEngineService;
import org.springblade.modules.core.service.GasBaseInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Slf4j
@Service
@AllArgsConstructor
public class LeaveServiceImpl extends BaseServiceImpl<LeaveMapper, ProcessLeave> implements ILeaveService {

	private final IFlowService flowService;

	private final GasBaseInfoService gasBaseInfoService;

	private final FlowEngineService flowEngineService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean startProcess(ProcessLeave leave) {
		String businessTable = FlowUtil.getBusinessTable(ProcessConstant.LEAVE_KEY);
		if (Func.isEmpty(leave.getId())) {
			// 保存leave
			leave.setApplyTime(DateUtil.now());
			save(leave);
			// 启动流程
			Kv variables = Kv.create()
				.set(ProcessConstant.TASK_VARIABLE_CREATE_USER, AuthUtil.getUserName())
				.set("taskUser", TaskUtil.getTaskUser(leave.getTaskUser()))
				.set("days", DateUtil.between(leave.getStartTime(), leave.getEndTime()).toDays());
			BladeFlow flow = flowService.startProcessInstanceById(leave.getProcessDefinitionId(),
				FlowUtil.getBusinessKey(businessTable, String.valueOf(leave.getId())), variables);
			if (Func.isNotEmpty(flow)) {
				log.debug("流程已启动,流程ID:" + flow.getProcessInstanceId());
				// 返回流程id写入leave
				leave.setProcessInstanceId(flow.getProcessInstanceId());
				updateById(leave);
			} else {
				throw new ServiceException("开启流程失败");
			}
		} else {

			updateById(leave);
		}
		return true;
	}

	@SneakyThrows
	@Override
	public void export(Long businessId, String processInstanceId, HttpServletResponse response) {
		//获取订单信息
		ProcessLeave detail = this.getById(businessId);
		Date applyTime = detail.getApplyTime();

		// 获取年份、月份和日期
		String year = String.format("%tY", applyTime);
		String month = String.format("%tm", applyTime); // 注意：月份是从0开始的
		String day = String.format("%td", applyTime);

		//获取订单创建人
		Long createUser = detail.getCreateUser();
		String gasName = gasBaseInfoService.selectNameByNumber(createUser.toString());

		String manage = "刘帅";
		String incharge = "白瑞";
		String generalmanager = "总经理";
		String chairman = "王永生";

		//获取订单审核信息
		List<BladeFlow> bladeFlowList = flowEngineService.historyList(processInstanceId);
		for (BladeFlow bladeFlow : bladeFlowList) {
			if (StringUtil.isEmpty(bladeFlow.getFlag())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String endTime = sdf.format(bladeFlow.getEndTime());
				switch (bladeFlow.getHistoryActivityId()){
					case "manage-reject":
						manage = "【" + bladeFlow.getStatus() + "】\n" + manage + "  " + endTime;
						break;
					case "incharge-reject":
						incharge = "【" + bladeFlow.getStatus() + "】\n" + incharge + "  " + endTime;
						break;
					case "generalmanager-reject":
						generalmanager = "【" + bladeFlow.getStatus() + "】\n" + generalmanager + "  " + endTime;
						break;
					case "chairman-reject":
						chairman = "【" + bladeFlow.getStatus() + "】\n" + chairman + "  " + endTime;
						break;
					case "manage-agree":
						manage = "【" + bladeFlow.getStatus() + "】\n" + manage + "  " + endTime;
						break;
					case "incharge-agree":
						incharge = "【" + bladeFlow.getStatus() + "】\n" + incharge + "  " + endTime;
						break;
					case "generalmanager-agree":
						generalmanager = "【" + bladeFlow.getStatus() + "】\n" + generalmanager + "  " + endTime;
						break;
					case "chairman-agree":
						chairman = "【" + bladeFlow.getStatus() + "】\n" + chairman + "  " + endTime;
						break;
				}
			}
		}

		try {
			// 创建新的 PDF 文档
			Document document = new Document();

			// 创建 PDF 写入器
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

			// 设置页边距
			document.setMargins(60, 60, 80, 80);

			// 打开文档
			document.open();

			// 设置字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfChinese, 25, Font.BOLD);
			Font contentFont = new Font(bfChinese, 15, Font.NORMAL);

			// 添加标题
			Paragraph title = new Paragraph("LNG加气站进液价格审批表", font);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			// 设置表格的列宽和列数
			float[] widths = {40f, 110f};
			PdfPTable table = new PdfPTable(widths);
			table.setSpacingBefore(30f);
			// 设置表格宽度为100%
			table.setWidthPercentage(100.0F);

			// 第一行
			PdfPCell cell1 = new PdfPCell(new Paragraph("内容", contentFont));
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setFixedHeight(60);
			table.addCell(cell1);


			PdfPCell cell2 = new PdfPCell(new Paragraph(gasName + "LNG加气站进液价格确认", contentFont));
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setFixedHeight(60);
			table.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Paragraph("日期·价格", contentFont));
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setFixedHeight(60);
			table.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Paragraph(year + "年" + month + "月" + day + "日，" + "进液价格" + detail.getPrice() + "元/吨", contentFont));
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell4.setFixedHeight(60);
			table.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("供货详情", contentFont));
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell5.setRowspan(2);
			cell5.setFixedHeight(120);
			table.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Paragraph(detail.getProcurementScheme(), contentFont));
			cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell6.setFixedHeight(60);
			table.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Paragraph("供货厂家" + detail.getLieferant(), contentFont));
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell7.setFixedHeight(60);
			table.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Paragraph("经营部审批", contentFont));
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell8.setFixedHeight(60);
			table.addCell(cell8);

			PdfPCell cell9 = new PdfPCell(new Paragraph(manage, contentFont));
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell9.setFixedHeight(70);
			table.addCell(cell9);

			PdfPCell cell10 = new PdfPCell(new Paragraph("分管领导审批", contentFont));
			cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell10.setFixedHeight(70);
			table.addCell(cell10);

			PdfPCell cell11 = new PdfPCell(new Paragraph(incharge, contentFont));
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setFixedHeight(70);
			table.addCell(cell11);

			/*PdfPCell cell12 = new PdfPCell(new Paragraph("总经理审批", contentFont));
			cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell12.setFixedHeight(70);
			table.addCell(cell12);*/

			/*PdfPCell cell13 = new PdfPCell(new Paragraph(generalmanager, contentFont));
			cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell13.setFixedHeight(70);
			table.addCell(cell13);*/

			PdfPCell cell14 = new PdfPCell(new Paragraph("董事长审批", contentFont));
			cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell14.setFixedHeight(70);
			table.addCell(cell14);

			PdfPCell cell15 = new PdfPCell(new Paragraph(chairman, contentFont));
			cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell15.setFixedHeight(70);
			table.addCell(cell15);

			PdfPCell cell16 = new PdfPCell(new Paragraph("备注", contentFont));
			cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell16.setFixedHeight(70);
			table.addCell(cell16);

			PdfPCell cell17 = new PdfPCell(new Paragraph(detail.getComment(), contentFont));
			cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell17.setFixedHeight(70);
			table.addCell(cell17);

			document.add(table);

			// 关闭文档
			document.close();
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}



}
