package com.qhtr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.app.dto.BuyCartDto;
import com.app.dto.StoreOrderDto;
import com.app.dto.StoreOrderDto1;
import com.app.param.Param1;
import com.qhtr.common.Constants;
import com.qhtr.dao.StoreOrderMapper;
import com.qhtr.model.BuyCart;
import com.qhtr.model.Coupon;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.model.Sku;
import com.qhtr.model.StoreOrder;
import com.qhtr.model.User;
import com.qhtr.service.BuyCartService;
import com.qhtr.service.CouponService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.SkuService;
import com.qhtr.service.StoreOrderService;
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
	public List<StoreOrderDto> getOrdersByUser(int userId, int status) {
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
	public StoreOrderDto1 addtoBuy(int skuId, int num, int distributionType,int userId,int addressId,HttpServletRequest request) {
		StoreOrder so = new StoreOrder();
		so.setOrderCode(GenerationUtils.getGenerationCode("SO", userId+""));
		Sku sku = skuService.selectSkuBySkuId(skuId);
		Goods goods = goodsService.selectGoodsByGoodsId(sku.getGoodsId()).getGoods();
		so.setStoreId(goods.getStoreId());
		so.setUserId(userId);
		so.setDistributionType(distributionType);
		so.setUserId(userId);
		
		so.setTotalPrice(sku.getPrice() * num);
		so.setAddressId(addressId);
		
		StoreOrderDto1 dto1 = new StoreOrderDto1();
		
		Coupon cp = new Coupon();
		cp.setStoreId(goods.getStoreId());
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
		}
		if(distributionType == 1){
			so.setExpressPrice(8);
			dto1.setExpressPrice(8);
		}else if(distributionType == 2){
			so.setExpressPrice(0);
			dto1.setExpressPrice(0);
		}
		
		User user = userService.getUserById(userId);
		dto1.setPhone(user.getPhone());
		dto1.setStoreOrder(so.getOrderCode());
		
		GoodsOrder go = new GoodsOrder();
		go.setOrderCode(GenerationUtils.getGenerationCode("GO", userId+""));
		go.setStoreOrderCode(so.getOrderCode());
		go.setGoodsId(goods.getId());
		go.setUserId(userId);
		go.setStoreId(goods.getStoreId());
		go.setSkuId(skuId);
		go.setNum(num);
		go.setPrice(sku.getPrice());
		request.getSession().setAttribute(Constants.STORE_ORDER, so);
		request.getSession().setAttribute(Constants.GOODS_ORDER, go);
		return dto1;
	}
	
	/**
	 * 立刻购买  -->确认订单
	 */
	@Override
	public String addOrder(String userRemark, HttpServletRequest request) {
		PayOrder po = new PayOrder();
		StoreOrder so = (StoreOrder)request.getSession().getAttribute(Constants.STORE_ORDER);
		so.setUserRemark(userRemark);
		so.setStatus(1);
		so.setCreateTime(new Date());
		so.setResultPrice(so.getTotalPrice() + so.getExpressPrice() - so.getCouponPrice());
		
		GoodsOrder go = (GoodsOrder)request.getSession().getAttribute(Constants.GOODS_ORDER);
		go.setStatus(1);
		go.setCreateTime(new Date());
		
		po.setOrderCode(GenerationUtils.getGenerationCode("PO", so.getUserId()+""));
		po.setUserId(so.getUserId());
		po.setTotalPrice(so.getTotalPrice());
		po.setCreateTime(new Date());
		po.setStatus(1);
		
		so.setPayOrderCode(po.getOrderCode());
		
		int result1 = storeOrderMapper.insert(so);
		int result2 = goodsOrderService.addGoodsOrder(go);
		int result3 = payOrderService.insert(po);
		if(result1 == 1 && result2 == 1 && result3 == 1){
			request.getSession().removeAttribute(Constants.STORE_ORDER);
			request.getSession().removeAttribute(Constants.GOODS_ORDER);
			return po.getOrderCode();
		}else{
			return "";
		}
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
			cpTem.setStatus(1);
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
			so.setStatus(1);
			so.setCreateTime(new Date());
			so.setTotalPrice(so.getTotalPrice() + so.getExpressPrice() - so.getCouponPrice());
			storeOrderMapper.insert(so);
			payOrderPrice += so.getTotalPrice();
		}
		po.setStatus(1);
		po.setCreateTime(new Date());
		payOrderService.insert(po);
		
		//清除购物车的数据。添加商品订单
		@SuppressWarnings("unchecked")
		List<GoodsOrder> goList = (List<GoodsOrder>)request.getSession().getAttribute(Constants.GOODS_ORDER_BUYCART);
		for (GoodsOrder go1 : goList) {
				go1.setStatus(1);
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
}
