package org.springblade.common.utils;

import com.alibaba.fastjson.JSON;
import com.spire.doc.Document;
import com.spire.xls.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class FindAndReplaceData {
	/**
	 * excel中替换指定的数值
	 * @param filePath   源文件地址
	 * @param uploadPath 导出文件地址
	 * @param i          工作表位置 默认从0开始
	 * @param map        参数
	 */
	public static void ExcelFindAndReplaceData(String filePath, String uploadPath, int i, Map<String, String> map) {
		//加载文档
		Workbook wb = new Workbook();
		wb.loadFromFile(filePath);
		//获取第一个工作表
		Worksheet worksheet = wb.getWorksheets().get(i);
		//遍历参数map中的key
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			//System.out.println(key);
			//查找指定数据
			CellRange[] ranges = worksheet.findAllString(key, true, true);
//		//创建单元格样式
		CellStyle style = wb.getStyles().addStyle("Style");
//		style.setIncludeNumberFormat(true);
		style.getFont().setSize(12f);
		style.getFont().setFontName("仿宋");
		//style.getFont().setColor(new Color(30, 144, 255));
//		style.getFont().isBold(true);
		style.setHorizontalAlignment(HorizontalAlignType.Center);
		style.setWrapText(true);
			//遍历单元格，替换数据
			for (CellRange range : ranges) {
				//替换为新的数据
				if(!"date".equals(key) && !key.contains("Type")) {
					if(map.get(key)!=null && map.get(key).startsWith("-")){
						range.setNumberValue(Integer.parseInt(map.get(key).replace("-","")));
						range.getStyle().setColor(Color.orange);
					}else{
						if(map.get(key)!=null){
							range.setNumberValue(Integer.parseInt(map.get(key)));
						}else{
							range.setNumberValue(0);
						}
					}
				}else{
					range.setText(map.get(key));
					if(key.contains("Type")){
						range.setStyle(style);
						range.getBorders().getByBordersLineType(BordersLineType.EdgeTop).setLineStyle(LineStyleType.Thin);
						range.getBorders().getByBordersLineType(BordersLineType.EdgeBottom).setLineStyle(LineStyleType.Thin);
						range.getBorders().getByBordersLineType(BordersLineType.EdgeRight).setLineStyle(LineStyleType.Thin);
						range.getStyle().setVerticalAlignment(VerticalAlignType.Center);
					}
				}
				//应用样式
//			range.setStyle(style);
			}
		}
		//保存文档
		wb.saveToFile(uploadPath, ExcelVersion.Version2010);
		wb.dispose();
	}

	/**
	 * word中替换指定的数值
	 * @param path
	 * @param map
	 */
	public static void WordFindAndReplaceData(String path,String uploadPath, Map<String, String> map ) {
		Document doc = new Document();
		doc.loadFromFile(path);
//		// 正则表达式，匹配所有的占位符 ${}
//		Pattern pattern = Pattern.compile("\\$\\{.*?}");
//		// 根据正则表达式获取所有文本
//		TextSelection[] allPattern = doc.findAllPattern(pattern);
//		// 逐个替换占位符
//		for (TextSelection textSelection : allPattern) {
//			String tmp = map.get(textSelection.getSelectedText());
//			System.out.print(textSelection.getSelectedText());
//			int res = doc.replace(textSelection.getSelectedText(), tmp, true, true);
//			System.out.println(":" + res);
//		}
		for(String key:map.keySet()){
			// 根据key得到value
			String value = map.get(key).toString();
			//使用新文本替换文档中的指定文本 也就是value替换key
			doc.replace(key, value, false, true);
		}
		doc.saveToFile(uploadPath);
		doc.dispose();
	}


	/**
	 * 读取excel
	 * @param workbook
	 * @param sheetIndex
	 * @return
	 */
	public static ArrayList<String[]> readRows(Workbook workbook, int sheetIndex) {
		ArrayList<String[]> list=new ArrayList<String[]>();
		/*Workbook workbook = new Workbook();
		workbook.loadFromFile(path);*/
		workbook.calculateAllValue();
		Worksheet sheet = workbook.getWorksheets().get(sheetIndex);
		CellRange[] rows=sheet.getRows();
		for(CellRange cr:rows){
			ArrayList<CellRange> row=cr.getCellList();
			String[] arr=new String[row.size()];
			for(int i=0;i<row.size();i++){
				if(row.get(i).getFormula()!=null&&row.get(i).hasFormula()){
					arr[i]=row.get(i).getFormulaValue().toString().replace(".0","");
				}else{
					arr[i]=row.get(i).getDisplayedText();
				}
			}
			list.add(arr);
		}
		return list;
	}



	/*public static void main(String[] args) throws Exception{
		String path = "D:\\upload\\model\\20230130\\205705147845900.xls";
		InputStream in = new FileInputStream(path);

		// 读取整个Excel
		XSSFWorkbook sheets = new XSSFWorkbook(in);
		// 获取第一个表单Sheet
		XSSFSheet sheetAt = sheets.getSheetAt(0);
		sheetAt.setForceFormulaRecalculation(true);
		//默认第一行为标题行，i = 0
		XSSFRow titleRow = sheetAt.getRow(0);

		//后面使用它来执行计算公式 核心代码
		FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) sheets);
		// 循环获取每一行数据
		for (int i = 0; i < sheetAt.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheetAt.getRow(i);
			String var = null;
			// 读取每一格内容
			StringBuilder sb = new StringBuilder();
			for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
				XSSFCell titleCell = titleRow.getCell(index);
				XSSFCell cell = row.getCell(index);
				//公式类型
				if (cell.getCellTypeEnum().equals(CellType.FORMULA)) {
					String cellFormula = cell.getCellFormula();
					System.out.println(cellFormula);
					//执行公式，此处cell的值就是公式  核心代码
					CellValue evaluate = formulaEvaluator.evaluate(cell);
					System.out.println(formulaEvaluator.evaluateFormulaCell(cell));
					System.out.println(evaluate.formatAsString());
				}

				cell.setCellType(CellType.STRING);
				if (cell.getStringCellValue().equals("")) {
					continue;
				}
				//sb.append(var + ",");
				sb.append(cell + ",");

			}
		}
	}*/
}
