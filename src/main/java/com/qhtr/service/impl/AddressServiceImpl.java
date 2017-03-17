package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.AddressMapper;
import com.qhtr.model.Address;
import com.qhtr.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	@Resource
	public AddressMapper addressMapper;
	
	@Override
	public int addAddress(Address address) {
		if(address.getIsdefault() == null){
			address.setIsdefault(0);
		}
		if(address.getIsdefault() == 1){
			updateCheckIsDeafult(address);
		}
			return addressMapper.insert(address);
	}

	@Override
	public int deleteAddressById(int addId) {
		return addressMapper.deleteByPrimaryKey(addId);
	}

	@Override
	public int updateAddress(Address address) {
		if(address.getIsdefault() != null && address.getIsdefault() == 1){
			updateCheckIsDeafult(address);
		}
		return addressMapper.updateByPrimaryKeySelective(address);
	}

	@Override
	public List<Address> selectAddressByUserId(int userId) {
		Address addr = new Address();
		addr.setUserId(userId);
		return addressMapper.selectByConditions(addr);
	}

	public void updateCheckIsDeafult(Address address) {
		Address addTem = new Address();
		addTem.setUserId(address.getUserId());
		addTem.setIsdefault(1);
		List<Address> addList = addressMapper.selectByConditions(addTem);
		if(addList.isEmpty()){
		}else{
			Address add1 = addList.get(0);
			add1.setIsdefault(0);
			addressMapper.updateByPrimaryKey(add1);
		}
	}

	@Override
	public Address getDeafault(int userId) {
		Address addTem = new Address();
		addTem.setUserId(userId);
		addTem.setIsdefault(1);
		List<Address> addList = addressMapper.selectByConditions(addTem);
		if(addList.isEmpty()){
			return null;
		}else{
			return addList.get(0);
		}
	}

	@Override
	public int updateToDeafult(int addId) {
		Address address = addressMapper.selectByPrimaryKey(addId);
		updateCheckIsDeafult(address);
		address.setIsdefault(1);
		return addressMapper.updateByPrimaryKey(address);
	}

}
