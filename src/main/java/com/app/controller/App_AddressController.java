package com.app.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.Address;
import com.qhtr.service.AddressService;
import com.qhtr.service.SystemLogService;

@Controller
@RequestMapping("/app_address")
public class App_AddressController {
	@Resource
	public AddressService addressService;
	
	@ResponseBody
	@RequestMapping(value="/addAddress",method=RequestMethod.POST)
	public Json addAddress(Json j,Address address) throws Exception{
		int result = addressService.addAddress(address);
		if(result == 0){
			j.setCode(0);
			j.setMessage("增加地址失败!");
		}else{
			j.setMessage("增加成功!");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteAddress",method=RequestMethod.POST)
	public Json deleteAddress(Json j,@RequestParam int id){
		int result = addressService.deleteAddressById(id);
		if(result == 0){
			j.setCode(0);
			j.setMessage("删除地址失败!");
		}else{
			j.setMessage("删除成功!");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateAddress",method=RequestMethod.POST)
	public Json updateAddress(Json j,Address address){
		int result = addressService.updateAddress(address);
		if(result == 0){
			j.setCode(0);
			j.setMessage("修改地址失败!");
		}else{
			j.setMessage("修改成功!");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/selectAddressesByUserId")
	public Json selectAddressesByUserId(Json j,@RequestParam int userId){
		List<Address> addrList = addressService.selectAddressByUserId(userId);
		j.setData(addrList);
		return j;
	}

	@ResponseBody
	@RequestMapping(value="/setAddressDefault",method=RequestMethod.POST)
	public Json setAddressDefault(Json j,@RequestParam int id){
		int result = addressService.updateToDeafult(id);
		if(result == 0){
			j.setCode(0);
			j.setMessage("设置失败!");
		}else{
			j.setMessage("设置成功!");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/getDefaultAddress")
	public Json getDefaultAddress(Json j,@RequestParam int userId){
		Address addr = addressService.getDeafault(userId);
		j.setData(addr);
		return j;
	}
}
