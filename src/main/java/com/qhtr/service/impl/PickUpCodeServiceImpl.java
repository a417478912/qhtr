package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.PickUpCodeMapper;
import com.qhtr.model.PickUpCode;
import com.qhtr.service.PickUpCodeService;

@Service
public class PickUpCodeServiceImpl implements PickUpCodeService{

	@Resource
	private PickUpCodeMapper pickUpCodeMapper;

	@Override
	public PickUpCode getPickUpCode(int storeOrderId) {
		
		return pickUpCodeMapper.getPickUpCode(storeOrderId);
	}

	@Override
	public int insertPickUpCode(PickUpCode pickUpCode) {
		
		try {
			pickUpCodeMapper.insertPickUpCode(pickUpCode);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deletePickUpCode(int storeOrderId) {
		
		try {
			pickUpCodeMapper.deletePickUpCode(storeOrderId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public PickUpCode getStoreOrderIdByPickUpCode(String pickUpCode) {
		return pickUpCodeMapper.getStoreOrderIdByPickUpCode(pickUpCode);
	}
	
}
