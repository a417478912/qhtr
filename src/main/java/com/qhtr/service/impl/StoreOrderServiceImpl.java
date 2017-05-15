package com.qhtr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.app.dto.BuyCartDto;
import com.app.dto.StoreOrderDto_App;
import com.app.dto.StoreOrderDto1;
import com.app.param.Param1;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qhtr.common.Constants;
import com.qhtr.dao.GoodsOrderMapper;
import com.qhtr.dao.StoreOrderMapper;
import com.qhtr.dto.GoodsDto;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.Address;
import com.qhtr.model.BuyCart;
import com.qhtr.model.Coupon;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.model.Sku;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.AddressService;
import com.qhtr.service.BuyCartService;
import com.qhtr.service.CouponService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.SkuService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.service.UUpaotuiService;
import com.qhtr.service.UserService;
import com.qhtr.utils.GenerationUtils;

@Service
public class StoreOrderServiceImpl implements StoreOrderService {
	@Resource
	public StoreOrderMapper storeOrderMapper;
	@Resource
	public GoodsOrderService goodsOrderService;
	@Resource
	public SkuService skuService;
	@Resource
	public GoodsService goodsService;
	@Resource
	public CouponService couponService;
	@Resource
	public UserService userService;
	@Resource
	public BuyCartService buyCartService;
	@Resource
	public PayOrderService payOrderService;
	@Resource
	public AddressService addressService;
	@Resource
	public UUpaotuiService uUpaotuiService;

	@Override
	public StoreOrder selectByOrderCode(String soCode) {
		StoreOrder soTem = new StoreOrder();
		soTem.setOrderCode(soCode);
		List<StoreOrder> soList = storeOrderMapper.selectByConditions(soTem);
		if(soList.isEmpty()){
			return null;
		}else{
			return soList.get(0);
		}
	}

	@Override
	public StoreOrder selectById(int storeOrderId) {
		return storeOrderMapper.selectByPrimaryKey(storeOrderId);
	}

	@Override
	public int insert(StoreOrder record) {
		return storeOrderMapper.insert(record);
	}

	@Override
	public int updateByCondition(StoreOrder record) {
		return storeOrderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<StoreOrderDto_App> getOrdersByUser(int userId, int status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		if(status != 0){
			map.put("status", status);
		}
		return storeOrderMapper.selectByUser(map);
	}

	/**
	 *  立刻购买
	 */
	@Override
	public StoreOrder addtoBuy(int skuId, int num, int distributionType,int userId,int addressId,String userRemark) {
		StoreOrder so = new StoreOrder();
		so.setOrderCode(GenerationUtils.getGenerationCode("SO", userId+""));
		Sku sku = skuService.selectSkuBySkuId(skuId);
		GoodsDto goodsDto = goodsService.selectGoodsByGoodsId(sku.getGoodsId());
		so.setStoreId(goodsDto.getStoreId());
		so.setUserId(userId);
		so.setDistributionType(distributionType);
		so.setUserId(userId);
		so.setUserRemark(userRemark);
		int totalPrice = sku.getPrice() * num;
		so.setTotalPrice(totalPrice);
		so.setAddressId(addressId);
		Address address = addressService.getAddressByid(addressId);
		so.setReceivingName(address.getReceivingName());
		so.setReceivingPhone(address.getReceivingPhone());
		so.setAddressDetails(address.getProvince()+address.getCity()+address.getCountry()+address.getDetails());
		/*
		 * 购物券
		 Coupon cp = new Coupon();
		cp.setStoreId(goodsDto.getStoreId());
		cp.setUserId(userId);
		cp.setStatus(1);
		List<Coupon> cps = couponService.selectByConditions(cp);
		if(!cps.isEmpty()){
			dto1.setCouponId(cps.get(0).getId());
			dto1.setCouponName(cps.get(0).getName());
			dto1.setCouponMoney(cps.get(0).getMoney());
			so.setCouponPrice(cps.get(0).getMoney());
			so.setCouponId(cps.get(0).getId());
		}else{
			so.setCouponPrice(0);
		}*/
		if(distributionType == 1){
			String expressPrice = uUpaotuiService.getExpressOrderPrice(addressId,goodsDto.getStoreId());
			String returnCode = JSONObject.parseObject(expressPrice).getString("return_code");
			if(returnCode != null && returnCode.equals("ok")){
				so.setExpressPrice(new BigDecimal(JSONObject.parseObject(expressPrice).getString("need_paymoney")).multiply(new BigDecimal(100)).intValue());
			}
		}else if(distributionType == 2){
			so.setExpressPrice(0);
		}
		so.setResultPrice(totalPrice + so.getExpressPrice());
		so.setStatus(10);
		so.setCreateTime(new Date());
		storeOrderMapper.insert(so);
		
		GoodsOrder go = new GoodsOrder();
		go.setOrderCode(GenerationUtils.getGenerationCode("GO", userId+""));
		go.setStoreOrderCode(so.getOrderCode());
		go.setGoodsId(goodsDto.getId());
		go.setUserId(userId);
		go.setStoreId(goodsDto.getStoreId());
		go.setSkuId(skuId);
		go.setNum(num);
		go.setStoreOrderId(so.getId());
		go.setPrice(sku.getPrice());
		go.setStatus(10);
		go.setCreateTime(new Date());
		go.setGoodsName(goodsDto.getName());
		go.setGoodsPicture(goodsDto.getThumb());
		goodsOrderService.addGoodsOrder(go);
		return so;
	}
	
	/**
	 * 从购物车结算
	 */
	@Override
	public List<StoreOrderDto1> selectFromBuyCartIds(int userId, int[] ids,int distributionType,int addressId,HttpServletRequest request) {
		List<StoreOrderDto1> soDtoList = new ArrayList<StoreOrderDto1>();
		List<StoreOrder> soList = new ArrayList<StoreOrder>();
		List<GoodsOrder> goList = new ArrayList<GoodsOrder>();
		List<BuyCartDto> bcDtoList = buyCartService.selectByIds(ids);
		if(bcDtoList.isEmpty()){
 			return null;
 		}
		for (BuyCartDto bc : bcDtoList) {
			StoreOrderDto1 dto = new StoreOrderDto1();
			//session stroeOrder订单
			StoreOrder so = new StoreOrder();
			so.setOrderCode(GenerationUtils.getGenerationCode("SO", userId+""));
			so.setStoreId(bc.getStoreId());
			so.setDistributionType(distributionType);
			so.setUserId(userId);
			int totalPrice = 0;
			for (BuyCart bc1 : bc.getBuyCartList()) {
				totalPrice += Integer.parseInt(bc1.getString3()) * bc1.getNum();
				
				GoodsOrder go = new GoodsOrder();
				go.setOrderCode(GenerationUtils.getGenerationCode("GO", userId+""));
				go.setStoreOrderCode(so.getOrderCode());
				go.setGoodsId(bc1.getGoodsId());
				go.setUserId(userId);
				go.setStoreId(bc.getStoreId());
				go.setSkuId(bc1.getSkuId());
				go.setNum(bc1.getNum());
				go.setPrice(Integer.parseInt(bc1.getString3()));
				goList.add(go);
			}
			so.setTotalPrice(totalPrice);
			so.setAddressId(addressId);
			if(distributionType == 1){
				so.setExpressPrice(8);
			}else if(distributionType == 2){
				so.setExpressPrice(0);
			}
			
			int storeIdI = bc.getStoreId();
			//优惠券默认选择
			Coupon cpTem = new Coupon();
			cpTem.setUserId(userId);
			cpTem.setStatus(10);
			cpTem.setStoreId(storeIdI);
			List<Coupon> cps = couponService.selectByConditions(cpTem);
			if(!cps.isEmpty()){
				so.setCouponPrice(cps.get(0).getMoney());
				so.setCouponId(cps.get(0).getId());
				dto.setCouponId(cps.get(0).getId());
				dto.setCouponName(cps.get(0).getName());
			}else{
				so.setCouponPrice(0);
			}
			
			dto.setBuyCartList(bc.getBuyCartList());
			dto.setStoreAvatar(bc.getStoreAvatar());
			dto.setStoreName(bc.getStoreName());
			dto.setStoreOrder(so.getOrderCode());
			soDtoList.add(dto);
			
			soList.add(so);
		}
		
		request.getSession().setAttribute(Constants.CART_IDS, ids);
		request.getSession().setAttribute(Constants.STORE_ORDER_BUYCART, soList);
		request.getSession().setAttribute(Constants.GOODS_ORDER_BUYCART, goList);
		return soDtoList;
	}
	
	/**
	 * 购物车确认订单
	 */
	@Override
	public String addOrders(Param1[] params,int userId,HttpServletRequest request) {
		PayOrder po = new PayOrder();
		@SuppressWarnings("unchecked")
		List<StoreOrder> soList = (List<StoreOrder>)request.getSession().getAttribute(Constants.STORE_ORDER_BUYCART);
		
		po.setOrderCode(GenerationUtils.getGenerationCode("PO",userId+""));
		po.setUserId(userId);
		int payOrderPrice = 0;
		for (Param1 p: params) {
			StoreOrder so = null;
			for (StoreOrder storeOrder : soList) {
				if(p.getOrderCode().equals(storeOrder.getOrderCode())){
					so = storeOrder;
					break;
				}
			}
			so.setUserRemark(p.getUserRemark());
			so.setStatus(10);
			so.setCreateTime(new Date());
			so.setTotalPrice(so.getTotalPrice() + so.getExpressPrice() - so.getCouponPrice());
			storeOrderMapper.insert(so);
			payOrderPrice += so.getTotalPrice();
		}
		po.setStatus(10);
		po.setCreateTime(new Date());
		payOrderService.insert(po);
		
		//清除购物车的数据。添加商品订单
		@SuppressWarnings("unchecked")
		List<GoodsOrder> goList = (List<GoodsOrder>)request.getSession().getAttribute(Constants.GOODS_ORDER_BUYCART);
		for (GoodsOrder go1 : goList) {
				go1.setStatus(10);
				go1.setCreateTime(new Date());
				goodsOrderService.addGoodsOrder(go1);
		}
	
		int[] ids = (int[])request.getSession().getAttribute(Constants.CART_IDS);
		int result = buyCartService.deleteByIds(ids);
		
		if(result == 1){
			return po.getOrderCode();
		}else{
			return null;
		}
	}

	@Override
	public List<StoreOrder> selectByConditions(StoreOrder storeOrder) {
		return storeOrderMapper.selectByConditions(storeOrder);
	}

	@Override
	public List<Map<String, Object>> selectMapByConditions(StoreOrder so,int page) {
		Page<?> startPage = PageHelper.startPage(page, 10);
		List<Map<String, Object>> list = storeOrderMapper.selectMapByConditions(so);
		for (Map<String, Object> map : list) {
			//第一个小商品的缩略图
			int storeOrderId = Integer.parseInt(map.get("id").toString());
			GoodsOrder goTem = new GoodsOrder();
			goTem.setStoreOrderId(storeOrderId);
			List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
			if (!goList.isEmpty()) {
				String avatar = goList.get(0).getGoodsPicture();
				map.put("avatar", avatar);
			}else{
				map.put("avatar", "");
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total",startPage.getTotal());
		list.add(map);
		return list;
	}

	@Override
	public int selectCountByConditions(Map<String,Object> map) {
		return storeOrderMapper.selectCountByConditions(map);
	}

	@Override
	public int setSellerRemark(int orderId, String remark) {
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(orderId);
		so.setSellerRemark(remark);
		return storeOrderMapper.updateByPrimaryKey(so);
	}

	@Override
	public StoreOrderDetailsDto selectStoreOrderDetailsById(int orderId) {
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(orderId);
		StoreOrderDetailsDto soDto = new StoreOrderDetailsDto();
		soDto.setStordeOrder(so);
		
		//地址
		Address address = addressService.getAddressByid(so.getAddressId());
		soDto.setAddress(address.getProvince()+address.getCity()+address.getCountry()+address.getDetails());
		soDto.setReceivingName(address.getReceivingName());
		soDto.setReceivingPhone(address.getReceivingPhone());
		
		//商品订单
		GoodsOrder goTem = new GoodsOrder();
		goTem.setStoreOrderCode(so.getOrderCode());
		List<GoodsOrder> goodsOrderList = goodsOrderService.selectByCondictions(goTem);
		soDto.setGoodsOrderList(goodsOrderList);
		return soDto;
	}

	@Override
	public void changeToSendOutTask() {
		StoreOrder soTem = new StoreOrder();
		soTem.setStatus(31);
		List<StoreOrder> list = storeOrderMapper.selectByConditions(soTem);
		for (StoreOrder storeOrder : list) {
			GoodsOrder goTem = new GoodsOrder();
			goTem.setStoreOrderCode(storeOrder.getOrderCode());
			List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
			boolean tag = true;
			for (GoodsOrder goodsOrder : goList) {
				if(goodsOrder.getStatus() == 20){
					tag = false;
					break;
				}
			}
			if(tag) {
				storeOrder.setStatus(30);
				storeOrderMapper.updateByPrimaryKey(storeOrder);
			}
		}
	}

	@Override
	public int sendOutGoods(int storeOrderId) {
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(storeOrderId);
		if(so.getStatus() == 20){
		so.setStatus(30);
		storeOrderMapper.updateByPrimaryKey(so);
		
		//更改商品订单为发货状态
		GoodsOrder goTem = new GoodsOrder();
		goTem.setStoreOrderCode(so.getOrderCode());
		List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
		Date nowTime = new Date();
		for (GoodsOrder goodsOrder : goList) {
			if(goodsOrder.getStatus() != 20){
				return 0;
			}
			goodsOrder.setStatus(30);
			goodsOrder.setShipmentsTime(nowTime);
			if (goodsOrderService.updateGoodsOrder(goodsOrder) == 0) {
				return 0;
			}
		}
		return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int cancelSendOutGoods(int storeOrderId) {
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(storeOrderId);
		so.setStatus(20);
		storeOrderMapper.updateByPrimaryKey(so);
		
		//更改商品订单为发货状态
		GoodsOrder goTem = new GoodsOrder();
		goTem.setStoreOrderCode(so.getOrderCode());
		List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
		Date nowTime = new Date();
		for (GoodsOrder goodsOrder : goList) {
			goodsOrder.setStatus(20);
			goodsOrder.setShipmentsTime(nowTime);
			if (goodsOrderService.updateGoodsOrder(goodsOrder) == 0) {
				return 0;
			}
		}
		return 1;
	}

	@Override
	public void updateCancleUnPayOrder() {
		StoreOrder soTem = new StoreOrder();
		soTem.setStatus(10);
		List<StoreOrder> list = storeOrderMapper.selectByConditions(soTem);
		for (StoreOrder storeOrder : list) {
			Date nowTime = new Date();
			Date sourceTime = storeOrder.getCreateTime();
			if(nowTime.getTime() - sourceTime.getTime() > 30*60*1000){
				storeOrder.setStatus(200);
				storeOrder.setCancalTime(nowTime);
				
				GoodsOrder goTem = new GoodsOrder();
				goTem.setStoreOrderCode(storeOrder.getOrderCode());
				List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
				for (GoodsOrder goodsOrder2 : goList) {
					goodsOrder2.setStatus(200);
					goodsOrder2.setCancalTime(nowTime);
					goodsOrderService.updateGoodsOrder(goodsOrder2);
				}
						
				storeOrderMapper.updateByPrimaryKey(storeOrder);
			}
		}
	}
}
