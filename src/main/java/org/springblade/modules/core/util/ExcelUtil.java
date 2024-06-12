package org.springblade.modules.core.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.SneakyThrows;
import org.apache.commons.codec.Charsets;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springblade.core.excel.listener.DataListener;
import org.springblade.core.excel.listener.ImportListener;
import org.springblade.core.excel.support.ExcelException;
import org.springblade.core.excel.support.ExcelImporter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
	 * 文件下载
	 *
	 * @param response HttpServletResponse
	 * @param filePath 文件路径
	 * @throws IOException IOException
	 */
	public static void download(HttpServletResponse response, String filePath) {
		// 参数检查：
		if (StrUtil.isEmpty(filePath)) {
			return;
		}
		// 获取输入流
		InputStream inputStream = null;
		// 获取文件路径
		File file;
		try {
			file = new File(filePath);
			inputStream = ExcelUtil.class.getClassLoader().getResourceAsStream(DEF_BASE_PATH + filePath);
			response.reset(); // 必须reset，否则会出现文件不完整
			response.setCharacterEncoding("UTF-8");
			response.setContentType("multipart/octet-stream");
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			// 循环取出流中的数据
			byte[] b = new byte[4096];
			for (int len = inputStream.read(b); len > 0; len = inputStream.read(b)) {
				response.getOutputStream().write(b, 0, len);
			}
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
	}
}
