package com.sell.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.BankCard;
import com.qhtr.service.impl.BankCardService;

@Controller
@RequestMapping("/sell_bankcard")
public class Sell_BankCardController {
	@Resource
	public BankCardService bankCardService;
	
	@ResponseBody
	@RequestMapping(value="/getBankList")
	public Json getBankList(Json j){
		j.setData(bankCardService.getBankList());
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/insert")
	public Json insert(Json j,@RequestParam String bankName,@RequestParam String cardholderName,@RequestParam String cardCode,@RequestParam int storeId){
		BankCard bankCard = new BankCard();
		bankCard.setBankName(bankName);
		bankCard.setCardCode(cardCode);
		bankCard.setCardholderName(cardholderName);
		bankCard.setStoreId(storeId);
		int result = bankCardService.insert(bankCard);
		if(result == 0){
			j.setCode(0);
			j.setMessage("添加银行卡失败!");
		}
		j.setMessage("添加银行卡成功!");
		return j;
	};
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public Json delete(Json j,int id){
		int result = bankCardService.delete(id);
		if(result == 0){
			j.setCode(0);
			j.setMessage("删除银行卡失败!");
			return j;
		}
		j.setMessage("删除银行卡成功!");
		return j;
	};
}
