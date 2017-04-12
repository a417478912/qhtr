package com.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qhtr.common.Json;
import com.qhtr.utils.FileUploadUtils;

@Controller
@RequestMapping("/sell_picture")
public class PictureController {
	@ResponseBody
	@RequestMapping(value="/updateAvatar")
	public Json updateAvatar(Json j,MultipartFile avatar) throws Exception{
		String url = FileUploadUtils.uploadFile(avatar, "sellerAvatar");
		if(url != null){
			j.setData(url);
		}else{
			j.setCode(0);
			j.setMessage("图片上传失败!");
		}
		return j;
	}
}
