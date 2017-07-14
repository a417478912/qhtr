package com.sell.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Json;
import com.qhtr.model.BankCard;
import com.qhtr.service.impl.BankCardService;
/**
 * @author Harry
 * @Description 操作银行卡的 Controller
 * @date  2017年6月21日
 */
@Controller
@RequestMapping("/sell_bankcard")
public class Sell_BankCardController {
	
	@Resource
	public BankCardService bankCardService;
	
	/**
	 * 获取银行列表
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getBankList")
	public Json getBankList(Json j){
		j.setData(bankCardService.getBankList());
		return j;
	}
	
	/**
	 * 获取店铺银行卡列表
	 * @param j
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getBankListByStoreId",method=RequestMethod.POST)
	public Json getBankListByStoreId(Json j,@RequestParam int storeId){
		
		List<BankCard> BankCardList = bankCardService.selectBankCardByStoreId(storeId);
		
		if (BankCardList.isEmpty()) {
			
			j.setMessage("暂无银行卡的相关信息!");
			j.setCode(0);
		}else{
			
			j.setMessage("银行卡信息获取成功!");
			j.setData(BankCardList);
		}
		return j;
	}
	
	/**
	 * 添加银行卡
	 * @param j
	 * @param bankName
	 * @param cardholderName
	 * @param cardCode
	 * @param storeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insert",method=RequestMethod.POST)
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
	
	/**
	 * 删除银行卡
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Json delete(Json j,int id){
		int result = bankCardService.delete(id);
		if(result == 0){
			j.setCode(0);
			j.setMessage("删除银行卡失败!");
			return j;
		}
		j.setMessage("删除银行卡成功!");
		return j;
	}
	
	/**
	 * 修改银行卡信息
	 * @param j
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateBankCard",method=RequestMethod.POST)
	public Json updateBankCard(Json j,@ModelAttribute BankCard bankCard){
		
		int result = bankCardService.updateBankCard(bankCard);
		if (result == 1) {
			
			j.setMessage("修改成功 !");
		}else{
			
			j.setCode(0);
			j.setMessage("修改失败 !");
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="/getBankCardById")
	public Json getBankCardById(Json j,int bankCardId){
		
		BankCard bankCard = bankCardService.selectBankCardById(bankCardId);
		if (bankCard == null) {
			j.setMessage("无该银行卡信息 !");
		}else{
			
			j.setData(bankCard);
			j.setMessage("获取银行卡信息成功 !");
		}
		
		return j;
	}
}
