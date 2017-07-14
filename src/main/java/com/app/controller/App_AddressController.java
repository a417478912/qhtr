package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qhtr.common.Constants;
import com.qhtr.common.Json;
import com.qhtr.model.Address;
import com.qhtr.service.AddressService;
import com.qhtr.utils.JPushUtils;
import com.qhtr.utils.weixinMessage.model.TemplateData;
import com.qhtr.utils.weixinMessage.model.WxTemplate;
import com.qhtr.utils.weixinMessage.utils.WeiXinUtils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
/**
 * 地址相关操作
 * @author wjx
 *
 * @date  2017年6月2日
 */
@Controller
@RequestMapping("/app_address")
public class App_AddressController {
	
	@Resource
	private AddressService addressService;
	
	
	
	@ResponseBody
	@RequestMapping(value="/addAddress")
	public Json addAddress(Json j,Address address) throws Exception{
		
		/* 
		  JPushClient jpushClient = new JPushClient(Constants.JPUSH_MASTER_SECRET, Constants.JPUSH_APP_KEY, null, ClientConfig.getInstance());

		    // For push, all you need do is to build PushPayload object.
		    PushPayload payload = JPushUtils.buildPushObject_all_all_alert("消息推送！！！");

		    try {
		        PushResult result = jpushClient.sendPush(payload);
		        System.out.println("result++++++++++++++++++"+result);

		    } catch (APIConnectionException e) {
		    	System.out.println(e);

		    } catch (APIRequestException e) {
		    	System.out.println(e);
		    }
		    
		*/
		// 调用业务层添加地址方法,返回 Result , Result = 0 代表添加失败
		int result = addressService.addAddress(address);
		
		if(result == 0){
			
			j.setCode(0);
			j.setMessage("增加地址失败!");
		}else{
			j.setMessage("增加成功!");
		}
		return j;
	}
	
	/**
	 * 删除地址
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteAddress")
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
	
	/**
	 * 修改地址
	 * @param j
	 * @param address
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAddress",method=RequestMethod.POST)//method=RequestMethod.POST
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
	
	/**
	 * 根据用户id查询地址
	 * @param j
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectAddressesByUserId")
	public Json selectAddressesByUserId(Json j,@RequestParam int userId){
		
		List<Address> addrList = addressService.selectAddressByUserId(userId);
		j.setData(addrList);
		
		return j;
	}

	/**
	 * 将地址设置为默认地址
	 * @param j
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 获取默认地址
	 * @param j
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getDefaultAddress")
	public Json getDefaultAddress(Json j,@RequestParam int userId){
		Address addr = addressService.getDeafault(userId);
		j.setData(addr);
		return j;
	}
	
	/**
	 * 根据地址id查询地址
	 * @param j
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAddressByid")
	public Json getAddressByid(Json j,@RequestParam int id){
		Address addr = addressService.getAddressByid(id);
		j.setData(addr);
		return j;
	}
	
	@RequestMapping(value="/harry")
	public String harry(HttpServletRequest req){
		
		WxTemplate temp = new WxTemplate();
    	
    	temp.setTemplate_id("6dua0sxoLSexeEN8niW-B2ysIzklA4yL28M8_SISbkI");
    	temp.setTouser("ogUf-wVxJ_qkhBn61MM0nyizJ8fc");
    	temp.setUrl("http://sale.7htr.com/#orders-20");
    	
    	Map<String, TemplateData> data = new HashMap<>();
    	
    	TemplateData first = new TemplateData();
    	
    	first.setValue("哈哈哈哈");
    	data.put("first", first);
    	
    	TemplateData orderMoneySum = new TemplateData();
    	
    	orderMoneySum.setValue("100元");
    	data.put("orderMoneySum", orderMoneySum);
    	
    	TemplateData orderProductName = new TemplateData();
    	
    	orderProductName.setValue("哈哈哈哈");
    	data.put("orderProductName", orderProductName);
    	
    	temp.setData(data);
    	
    	WeiXinUtils.send_template_message(Constants.WEIXINPUBLIC_APPID, Constants.WEIXINPUBLIC_APPSECRET, temp);
    	
    	return "harry";
	}
}
