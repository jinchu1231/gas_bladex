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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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

	@SneakyThrows
	@PostMapping("put-object")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查看信息", notes = "传入名称")
	public R put(@RequestParam MultipartFile file){
		BladeFile bladeFile =   minioTemplate.putFile("bladex", file.getOriginalFilename(), file.getInputStream(),file.getContentType());
		bladeFile.setName(bladeFile.getLink().replace("172.16.11.185","220.194.189.169"));
		return R.data(bladeFile);
	}


}

