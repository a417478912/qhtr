package com.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.utils.FileUploadUtils;

@Controller
@RequestMapping("/sell_picture")
public class PictureController {
	@ResponseBody
	@RequestMapping(value="/updateAvatar")
	public Json updateAvatar(Json j,String avatar) throws Exception{
		String url = FileUploadUtils.saveFromBase64String(avatar,"sellerAvatar");
		if(url != null){
			j.setData(url);
		}else{
			j.setCode(0);
			j.setMessage("图片上传失败!");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateGoodsPicture")
	public Json updateGoodsPicture(Json j,String picture) throws Exception{
		String url = FileUploadUtils.saveFromBase64String(picture,"goods");
		if(url != null){
			j.setData(url);
		}else{
			j.setCode(0);
			j.setMessage("图片上传失败!");
		}
		return j;
	}
}
