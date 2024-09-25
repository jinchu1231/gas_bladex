package org.springblade.modules.core.util;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.SneakyThrows;
import org.apache.commons.codec.Charsets;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springblade.core.excel.listener.DataListener;
import org.springblade.core.excel.listener.ImportListener;
import org.springblade.core.excel.support.ExcelException;
import org.springblade.core.excel.support.ExcelImporter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
/**
 * Excel工具类
 *
 * @author Chill
 * @apiNote https://www.yuque.com/easyexcel/doc/easyexcel
 */
public class ExcelUtil {

	/**
	 * 默认根目录
	 */
	private static final String DEF_BASE_PATH = "file" + File.separator;

	/**
	 * 读取excel的所有sheet数据
	 *
	 * @param excel excel文件
	 * @return List<Object>
	 */
	public static <T> List<T> read(MultipartFile excel, Class<T> clazz) {
		DataListener<T> dataListener = new DataListener<>();
		ExcelReaderBuilder builder = getReaderBuilder(excel, dataListener, clazz);
		if (builder == null) {
			return null;
		}
		builder.doReadAll();
		return dataListener.getDataList();
	}

	/**
	 * 读取excel的指定sheet数据
	 *
	 * @param excel   excel文件
	 * @param sheetNo sheet序号(从0开始)
	 * @return List<Object>
	 */
	public static <T> List<T> read(MultipartFile excel, int sheetNo, Class<T> clazz) {
		return read(excel, sheetNo, 1, clazz);
	}

	/**
	 * 读取excel的指定sheet数据
	 *
	 * @param excel         excel文件
	 * @param sheetNo       sheet序号(从0开始)
	 * @param headRowNumber 表头行数
	 * @return List<Object>
	 */
	public static <T> List<T> read(MultipartFile excel, int sheetNo, int headRowNumber, Class<T> clazz) {
		DataListener<T> dataListener = new DataListener<>();
		ExcelReaderBuilder builder = getReaderBuilder(excel, dataListener, clazz);
		if (builder == null) {
			return null;
		}
		builder.sheet(sheetNo).headRowNumber(headRowNumber).doRead();
		return dataListener.getDataList();
	}

	/**
	 * 读取并导入数据
	 *
	 * @param excel    excel文件
	 * @param importer 导入逻辑类
	 * @param <T>      泛型
	 */
	public static <T> void save(MultipartFile excel, ExcelImporter<T> importer, Class<T> clazz) {
		ImportListener<T> importListener = new ImportListener<>(importer);
		ExcelReaderBuilder builder = getReaderBuilder(excel, importListener, clazz);
		if (builder != null) {
			builder.doReadAll();
		}
	}

	/**
	 * 导出excel
	 *
	 * @param response 响应类
	 * @param dataList 数据列表
	 * @param clazz    class类
	 * @param <T>      泛型
	 */
	@SneakyThrows
	public static <T> void export(HttpServletResponse response, List<T> dataList, Class<T> clazz) {
		export(response, DateUtils.format(new Date(), DateUtils.DATE_FORMAT_14), "导出数据", dataList, clazz);
	}

	/**
	 * 导出excel
	 *
	 * @param response  响应类
	 * @param fileName  文件名
	 * @param sheetName sheet名
	 * @param dataList  数据列表
	 * @param clazz     class类
	 * @param <T>       泛型
	 */
	@SneakyThrows
	public static <T> void export(HttpServletResponse response, String fileName, String sheetName, List<T> dataList, Class<T> clazz) {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding(Charsets.UTF_8.name());
		fileName = URLEncoder.encode(fileName, Charsets.UTF_8.name());
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		//内容策略
		WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
		//设置 水平居中
		contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
		contentWriteCellStyle.setWrapped(true);
		HorizontalCellStyleStrategy horizontalCellStyleStrategy =
			new HorizontalCellStyleStrategy(null, contentWriteCellStyle);
		EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName)
			.registerWriteHandler(horizontalCellStyleStrategy).doWrite(dataList);
	}

	/**
	 * 获取构建类
	 *
	 * @param excel        excel文件
	 * @param readListener excel监听类
	 * @return ExcelReaderBuilder
	 */
	public static <T> ExcelReaderBuilder getReaderBuilder(MultipartFile excel, ReadListener<T> readListener, Class<T> clazz) {
		String filename = excel.getOriginalFilename();
		if (StringUtils.isEmpty(filename)) {
			throw new ExcelException("请上传文件!");
		}
		if ((!StringUtils.endsWithIgnoreCase(filename, ".xls") && !StringUtils.endsWithIgnoreCase(filename, ".xlsx"))) {
			throw new ExcelException("请上传正确的excel文件!");
		}
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(excel.getInputStream());
			return EasyExcel.read(inputStream, clazz, readListener);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 导出隧道设备列表
	 */
	@SneakyThrows
	public static void download(HttpServletResponse response, String projectId) {

		//获取模板
//		ClassPathResource classPathResource = new ClassPathResource("/static/特种设备安全检查.xlsx");
		InputStream inputStream = ResourceUtil.getResourceObj("static/" + projectId).getStream();
		//输入流
//		InputStream inputStream = null;
		//输出流
		ServletOutputStream outputStream = null;
		//Excel对象
		ExcelWriter excelWriter = null;
		try {
			//输入流
//			inputStream = classPathResource.getInputStream();
			// 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
			String fileName = URLEncoder.encode("特种设备安全检查", "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
			outputStream = response.getOutputStream();
			//设置输出流和模板信息
			excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).excelType(ExcelTypeEnum.XLSX).build();
//			excelWriter.finish();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 千万别忘记finish 会帮忙关闭流
			if (excelWriter != null) {
				excelWriter.finish();
			}
			//关闭流
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 文件下载
	 *
	 * @param response HttpServletResponse
	 * @param filePath 文件路径
	 * @throws IOException IOException
	 */
	/*public static void download(HttpServletResponse response, String filePath) {
		// 参数检查：
		if (StrUtil.isEmpty(filePath)) {
			return;
		}
		// 获取输入流
		InputStream inputStream = null;
		// 获取文件路径
		File file;
		try {
			//获取模板
			ClassPathResource classPathResource = new ClassPathResource("/static/交控运营事件报表.xlsx");
			String path = ResourceUtils.getURL("classpath:").getPath() + "static/" + filePath;
			*//** 获取文件的名称 . *//*
			String fileName = path.substring(path.lastIndexOf("/") +1);
			file = new File(fileName);
			if (!file.exists()){
				System.out.println("路径有误，文件不存在！");
			}
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
			response.setContentType("content-type:octet-stream");
			BufferedInputStream inputStream1 = new BufferedInputStream(new FileInputStream(file));
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) != -1){ *//** 将流中内容写出去 .*//*
			outputStream.write(buffer ,0 , len);
			}
			inputStream.close();
			outputStream.close();
			*//*inputStream = ExcelUtil.class.getClassLoader().getResourceAsStream(DEF_BASE_PATH + filePath);
			response.reset(); // 必须reset，否则会出现文件不完整
//			response.setCharacterEncoding("UTF-16LE");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);*//*
			// 循环取出流中的数据
			*//*byte[] b = new byte[4096];
			for (int len = inputStream.read(b); len > 0; len = inputStream.read(b)) {
				response.getOutputStream().write(b, 0, len);
			}*//*
		} catch (IOException e) {
			e.getMessage();
		} finally {
			// 关闭流
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}
