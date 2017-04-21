package com.qhtr.service;

import com.qhtr.model.Picture;

public interface PictureService {

	int insert(Picture picture);

	Picture getById(int picId);

}
