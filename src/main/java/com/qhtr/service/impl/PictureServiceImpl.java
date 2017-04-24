package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.PictureMapper;
import com.qhtr.model.Picture;
import com.qhtr.service.PictureService;

@Service("PictureService")
public class PictureServiceImpl implements PictureService{
	@Resource
	public PictureMapper pictureMapper;
	
	@Override
	public int insert(Picture picture) {
		return pictureMapper.insert(picture);
	}

	@Override
	public Picture getById(int picId) {
		return pictureMapper.selectByPrimaryKey(picId);
	}

}
