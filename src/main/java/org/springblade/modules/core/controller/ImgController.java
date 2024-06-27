package org.springblade.modules.core.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.boot.file.LocalFile;
import org.springblade.core.oss.MinioTemplate;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客房流水数据表 控制器
 *
 * @author Blade
 * @since 2022-09-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dev/img")
@Api( tags = "图片上传接口")
public class ImgController extends BladeController {

	private MinioTemplate minioTemplate;

	@SneakyThrows
	@PostMapping("put-object")
	@ApiOperationSupport(order = 1)
	public R put(@RequestParam MultipartFile file){
		BladeFile bladeFile = minioTemplate.putFile("bladex", file.getOriginalFilename(), file.getInputStream());
		return R.data(bladeFile);
	}

	/*@SneakyThrows
	@PostMapping("put-file")
	@ApiOperationSupport(order = 1)
	public R putFile(@RequestParam String buckeName){
		BladeFile bladeFile = minioTemplate.(buckeName, file.getOriginalFilename(), file.getInputStream());
		return R.data(bladeFile);
	}*/


	/**
	 * 上传图片
	 */
	@PostMapping("/upload")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "上传文件", notes = "上传文件")
	public R upload(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		if(fileName.endsWith(".jpg") || fileName.endsWith(".png")){
			LocalFile bf = getFile(file,"","/home/gas_station/upload/image","/home/gas_station/upload/image");
//			LocalFile bf = getFile(file,"","D://home/upload/image","D://home/upload/image");
			bf.transfer(false);
			bf.setUploadVirtualPath("/api"+bf.getUploadVirtualPath().replace("/upload/","file"));
			return R.data(bf);
		}else{
			return R.data("图片格式错误");
		}
	}
}
