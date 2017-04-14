package com.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.utils.FileUploadUtils;

@Controller
@RequestMapping("/sell_picture")
public class PictureController {
	@ResponseBody
	@RequestMapping(value="/upLoad")
	public Json upLoad(Json j,@RequestParam String picture,@RequestParam String thePath,Integer x,Integer y,Integer width,Integer height) throws Exception{
		String url = FileUploadUtils.saveFromBase64String(picture,thePath);
		if(url != null){
			j.setData(url);
		}else{
			j.setCode(0);
			j.setMessage("图片上传失败!");
		}
		
		/**
		 * 图片剪切
		 */
		if(x != null){
			String path = FileUploadUtils.cut(url, x, y, width, height, thePath);
			FileUploadUtils.deleteFile(url);
			j.setData(path);
		}
		return j;
	}
}
