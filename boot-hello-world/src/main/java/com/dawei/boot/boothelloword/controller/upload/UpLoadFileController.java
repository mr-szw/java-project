package com.dawei.boot.boothelloword.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dawei.boot.boothelloword.pojo.ResultDto;

/**
 * @author Dawei 2019/3/13
 * 文件上传
 */
@RestController("/upload")
public class UpLoadFileController {

	private static final Logger logger = LoggerFactory.getLogger(UpLoadFileController.class);


	/**
	 * 文件上传
	 *
	 * @param fileName 文件名
	 * @param file     文件
	 * @return 返回文件位置
	 */
	@PostMapping("/file")
	public String upLoadFile(String fileName, MultipartFile file) {
		logger.info("To up load file ....");
		ResultDto<String> resultDto = new ResultDto<>();
		resultDto.setError();
		if (file == null || file.isEmpty()) {
			resultDto.setParamError();
			return ResultDto.getResult(resultDto);
		}
		long fileSize = file.getSize();
		String originalFilename = file.getOriginalFilename();
		final String destFilePath = "D:\\home\\work\\temp\\" + UUID.randomUUID().toString() + originalFilename;

		File descFile = new File(destFilePath);
		if (!descFile.exists()) {
			boolean newFileResult;
			try {
				newFileResult = descFile.createNewFile();
			} catch (IOException e) {
				logger.error("Create New file destFilePath : {}  failed, e=", destFilePath, e);
				return ResultDto.getResult(resultDto);
			}
			logger.info("descFile ={} createNewFile result: {}", destFilePath, newFileResult);
		} else {
			logger.info("Save descFile path ={}", destFilePath);
		}
		//进行文件传输  此过程会阻塞
		Thread thread = new Thread(() -> {
			try {
				file.transferTo(descFile);
			} catch (IOException e) {
				logger.error("File to trans failed, e=", e);
			}
		});
		thread.start();
		try {
			//预计上传时间2S 超时就证明网络不好
			TimeUnit.SECONDS.timedJoin(thread, 2);
		} catch (InterruptedException e) {
			logger.error("Trans time is so long failed, e=", e);
			return ResultDto.getResult(resultDto);
		}
		//判断是不是已经执行完了
		if (thread.isAlive()) {  //还活跃 没有执行完
			//结束任务
			thread.interrupt();
			logger.error("Time out .... ");
		} else {
			logger.info("File name={} to destFilePath = {} finish !!!", file.getOriginalFilename(), destFilePath);
			resultDto.setSuccess();
		}

		return ResultDto.getResult(resultDto);
	}


}
