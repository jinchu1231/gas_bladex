package org.springblade.modules.core.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.oss.MinioTemplate;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.flow.demo.leave.service.ILeaveService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * 上传服务
 *
 * @author Blade
 * @since 2022-09-23
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping(AppConstant.DEV_CODE + "/file")
@Api(value = "上传接口",tags = "上传接口")
public class FileController extends BladeController {

	private MinioTemplate minioTemplate;

	private final ILeaveService leaveService;


	@SneakyThrows
	@PostMapping("put-object")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查看信息", notes = "传入名称")
	public R put(@RequestParam MultipartFile file){
		BladeFile bladeFile =   minioTemplate.putFile("bladex", file.getOriginalFilename(), file.getInputStream(),file.getContentType());
		bladeFile.setName(bladeFile.getLink().replace("172.16.11.185","220.194.189.169"));
		return R.data(bladeFile);
	}


	@SneakyThrows
	@GetMapping("export")
	public void export(@RequestParam("businessId") Long businessId, @RequestParam("processInstanceId") String processInstanceId,
					   HttpServletResponse response, HttpServletRequest request) {
		try {
			// 防止日志记录获取session异常
			request.getSession();
			// 设置编码格式
			response.setContentType("application/pdf;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			String fileName = URLEncoder.encode("购液订单审批表", "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".pdf");
			leaveService.export(businessId, processInstanceId, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


}

