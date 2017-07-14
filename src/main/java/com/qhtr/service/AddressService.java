package com.qhtr.service;

import java.util.List;

import com.qhtr.model.Address;

public interface AddressService {
	public int addAddress(Address address);
	public int deleteAddressById(int addId);
	public int updateAddress(Address address);
	
	/**
	 * 通过用户id查找地址
	 * @param userId
	 * @return
	 */
	public List<Address> selectAddressByUserId(int userId);
	
	/**
	 * 设为默认
	 * @param addId
	 * @return
	 */
	public int updateToDeafult(int addId);
	/**
	 * 获取默认地址
	 */
	public Address getDeafault(int userId);
	
	/**
	 * 通过地址id查找地址
	 * @param id
	 * @return
	 */
	public Address getAddressByid(int id);
	
}
