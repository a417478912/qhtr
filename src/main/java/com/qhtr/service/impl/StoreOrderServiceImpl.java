package com.qhtr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.app.dto.BuyCartDto;
import com.app.dto.StoreDto_App;
import com.app.dto.StoreOrderDto_App;
import com.app.dto.StoreOrderDto1;
import com.app.param.Param1;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.JsonObject;
import com.qhtr.common.Constants;
import com.qhtr.dao.GoodsOrderMapper;
import com.qhtr.dao.SellerAccountMapper;
import com.qhtr.dao.StoreOrderMapper;
import com.qhtr.dao.UserMapper;
import com.qhtr.dto.GoodsDto;
import com.qhtr.dto.StoreOrderDetailsDto;
import com.qhtr.model.Address;
import com.qhtr.model.BuyCart;
import com.qhtr.model.Coupon;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.model.QueryCount;
import com.qhtr.model.SellerAccount;
import com.qhtr.model.Sku;
import com.qhtr.model.Store;
import com.qhtr.model.StoreOrder;
import com.qhtr.model.User;
import com.qhtr.service.AddressService;
import com.qhtr.service.BuyCartService;
import com.qhtr.service.CouponService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.SkuService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.service.StoreService;
import com.qhtr.service.UUpaotuiService;
import com.qhtr.service.UserService;
import com.qhtr.utils.GenerationUtils;
import com.qhtr.utils.JPushUtils;
import com.qhtr.utils.UUPaotuiUtils;
/**
 * @author Harry
 * @Description 订单业务层实现类
 * @date  2017年6月5日
 */
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
	@Resource
	public StoreService storeService;

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
		so.setUserRemark(userRemark);
		int totalPrice = sku.getPrice() * num;
		so.setTotalPrice(totalPrice);
		
		// 修改...
		// so.setAddressDetails(address.getDetails());
		
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
		
		// distributionType = 1 代表配送到家,计算配送费用
		if(distributionType == 1){
			
			so.setAddressId(addressId);
			
			Address address = addressService.getAddressByid(addressId);
			
			so.setReceivingName(address.getReceivingName());
			so.setReceivingPhone(address.getReceivingPhone());
			so.setAddressDetails(address.getDetails());
			
			String expressPrice = uUpaotuiService.getExpressOrderPrice(addressId,goodsDto.getStoreId());
			String returnCode = JSONObject.parseObject(expressPrice).getString("return_code");
			if(returnCode != null && returnCode.equals("ok")){
				so.setExpressPrice(new BigDecimal(JSONObject.parseObject(expressPrice).getString("need_paymoney")).multiply(new BigDecimal(100)).intValue());
			}
			
		// distributionType = 2  代表自提 
		}else if(distributionType == 2){
			String orderCode = so.getOrderCode();
			
			so.setAddressId(0);
			so.setAddressDetails("0");
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
		
		go.setSkuDetails(sku.getAttrDetails());
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
		User user = userMapper.selectByPrimaryKey(userId);
		
		if(bcDtoList.isEmpty()){
 			return null;
 		}
		
		// 计算跑腿费用
		BuyCartDto buyCartDto = bcDtoList.get(0);
		int storeId = buyCartDto.getStoreId();
		Store store = storeService.selectStoreById(storeId);
		String[] split = store.getLongitudeLatitude().split(",");
		// longitude 经度
		String longitude = split[0];
		// latitude 纬度
		String latitude = split[1];
		Address address = addressService.getAddressByid(addressId);
		String toAddress = address.getDetails();
		// 计算跑腿费用
		String orderPrice = UUPaotuiUtils.getOrderPrice(store.getLocation(), toAddress, longitude, latitude, "0", "0");
		JSONObject jsonObject = JSONObject.parseObject(orderPrice);
		String expressPrice = (String) jsonObject.get("total_money");
		System.out.println(expressPrice+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
				totalPrice += Integer.parseInt(bc1.getPrice()) * bc1.getNum();
				
				
				GoodsOrder go = new GoodsOrder();
				go.setOrderCode(GenerationUtils.getGenerationCode("GO", userId+""));
				go.setStoreOrderCode(so.getOrderCode());
				go.setGoodsId(bc1.getGoodsId());
				go.setUserId(userId);
				go.setStoreId(bc.getStoreId());
				go.setSkuId(bc1.getSkuId());
				go.setNum(bc1.getNum());
				go.setPrice(Integer.parseInt(bc1.getPrice()));
				goList.add(go);
			}
			so.setTotalPrice(totalPrice);
			so.setAddressId(addressId);
			int storeIdI = bc.getStoreId();
			/*// 1 : 配送  2 : 自提
			if(distributionType == 1){
				System.out.println((int) Double.parseDouble(expressPrice)+"++++++++++++++++++++++++++++++++++++++++++++++++++++");
				so.setExpressPrice((int) Double.parseDouble(expressPrice));
			}else{
				so.setExpressPrice(0);
			}*/
/*			
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
			*/
			dto.setBuyCartList(bc.getBuyCartList());
			dto.setStoreAvatar(bc.getStoreAvatar());
			dto.setStoreName(bc.getStoreName());
			dto.setStoreOrder(so.getOrderCode());
			if (user.getPhone() != null && !"".equals(user.getPhone())) {
				
				dto.setPhone(user.getPhone());
			}
			if (distributionType == 1) {
				
				dto.setExpressPrice((int) Double.parseDouble(expressPrice));
			}else{
				dto.setExpressPrice(0);
			}
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
		// 生 成orderCode
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
		// 修改...
		List<Map<String, Object>> list = storeOrderMapper.selectMapByConditions(so);// selectMapByConditions
		
		for (Map<String, Object> map : list) {
			
			if ((String) map.get("receiving_name") == null || "".equals((String) map.get("receiving_name"))) {
			
				Integer userId = (Integer) map.get("user_id");
				if (userId != null) {
					User user = userMapper.selectByPrimaryKey(userId);
					if (user != null) {
						if (user.getNickName() != null) {
							map.put("receivingName", user.getNickName());
						}else{
							if (user.getPhone() != null && !"".equals(user.getPhone())) {
								map.put("receivingName", user.getPhone());
							}else{
								map.put("receivingName", "买家未填写收货姓名 !");
							}
						}
					}else{
						map.put("receivingName", "买家未填写收货姓名 !");
					}
				}else{
					map.put("receivingName", "买家未填写收货姓名 !");
				}
			}
			int storeOrderId = Integer.parseInt(map.get("id").toString());
			GoodsOrder goTem = new GoodsOrder();
			goTem.setStoreOrderId(storeOrderId);
			List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
			if (!goList.isEmpty()) {
				//第一个小商品的缩略图
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

	@Resource
	private UserMapper userMapper;
	
	@Override
	public StoreOrderDetailsDto selectStoreOrderDetailsById(int orderId) {
		
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(orderId);
		StoreOrderDetailsDto soDto = new StoreOrderDetailsDto();
		
		if (so.getDistributionType() == 2) {
			
			User user = userMapper.selectByPrimaryKey(so.getUserId());
			
			if (user != null) {
				
				// 如果用户昵称不为空
				if (user.getNickName() != null && !"".equals(user.getNickName())) {
					// 将买家信息设置为用户昵称
					so.setReceivingName(user.getNickName());
					// 如果用户昵称不为空,手机号不为空
					if (user.getPhone() != null && !"".equals(user.getPhone())) {
						
						so.setReceivingPhone(user.getPhone());
					// 用户昵称不为空,手机号为空
					}else{
						
						so.setReceivingPhone("买家未填写相关信息 !");
					}
				// 如果用户昵称为空
				}else{
					// 用户手机号不为空
					if (user.getPhone() != null && !"".equals(user.getPhone())) {
						
						// 将买家信息设置为用户手机号
						so.setReceivingName(user.getPhone());
						
					}else{
						// 如果用户昵称为空,手机号也为空
						so.setReceivingName("买家未填写相关信息 !");
						so.setReceivingPhone("买家未填写相关信息 !");
					}
				}
			}
		}
		soDto.setStordeOrder(so);
		
		//店铺头像和 名字
		int storeId = so.getStoreId();
		StoreDto_App store = storeService.getStoreById(storeId);
		if (store != null) {
			
			soDto.setStoreAvatar(store.getAvatar());
			soDto.setStoreName(store.getName());
		}
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
		// 查询到店铺订单
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(storeOrderId);
		// 判断订单状态是否为20 : 已付款
		if(so.getStatus() == 20){
		// 修改订单状态
		so.setStatus(30);
		storeOrderMapper.updateByPrimaryKey(so);
		
		//更改商品订单为发货状态
		GoodsOrder goTem = new GoodsOrder();
		goTem.setStoreOrderCode(so.getOrderCode());
		// 通过店铺订单码查询商品订单
		List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
		Date nowTime = new Date();
		StringBuilder sb = new StringBuilder();
		for (GoodsOrder goodsOrder : goList) {
			
			if(goodsOrder.getStatus() != 20){
				return 0;
			}
			if (goodsOrder.getGoodsName() != null) {
				
				sb.append(goodsOrder.getGoodsName()+" ");
			}
			// 设置商品订单状态
			goodsOrder.setStatus(30);
			// 设置发货时间
			goodsOrder.setShipmentsTime(nowTime);
			if (goodsOrderService.updateGoodsOrder(goodsOrder) == 0) {
				return 0;
			}
		}
		// 向app推送消息
		Integer userId = so.getUserId();
		Set<String> alias = new HashSet<>();
		
		alias.add(userId + "");
		JPushUtils.sendPush(JPushUtils.pushToIOSByAlias("您购买的  " + sb.toString() + "已发货,请注意查看哦~", alias), LoggerFactory.getLogger(getClass()));;
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
			System.out.println(nowTime.getTime());
			System.out.println( sourceTime.getTime());
			System.out.println(nowTime.getTime() - sourceTime.getTime());
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

	@Override
	public int updateCancalUnpayOrder(int storeOrderId) {
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(storeOrderId);
		if(so.getStatus() != 10){
			//订单状态错误
			return -1;
		}
		Date nowTime = new Date();
		so.setStatus(200);
		so.setCancalTime(nowTime);
		
		GoodsOrder goTem = new GoodsOrder();
		goTem.setStoreOrderCode(so.getOrderCode());
		List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
		for (GoodsOrder goodsOrder2 : goList) {
			goodsOrder2.setStatus(200);
			goodsOrder2.setCancalTime(nowTime);
			goodsOrderService.updateGoodsOrder(goodsOrder2);
		}
				
		storeOrderMapper.updateByPrimaryKey(so);
		return 1;
	}

	@Resource
	private SellerAccountMapper sellerAccountMapper;
	@Override
	public int updateSureReceiveingGoods(int storeOrderId) {
		 // 10 : 待付款      20 : 已付款   21 : 已付款带自取    30 : 已发货      40 : 已收货   50 : 已评价
	    // 100 : 申请售后   110 : 已退款/已退货
		StoreOrder so = storeOrderMapper.selectByPrimaryKey(storeOrderId);
		//不是自取或者已发货。不能确认收货
		if(so.getStatus() == 21 || so.getStatus() == 30){
			
			Integer storeId = so.getStoreId();
			SellerAccount sellerAccount = sellerAccountMapper.getAccountByStoreId(storeId);
			if (sellerAccount != null) {
				
				Integer accountMoney = sellerAccount.getAccountMoney();
				sellerAccount.setAccountMoney(accountMoney + (so.getResultPrice()));
				sellerAccountMapper.updateByPrimaryKeySelective(sellerAccount);
			}
			Date nowTime = new Date();
			so.setStatus(40);
			
			GoodsOrder goTem = new GoodsOrder();
			goTem.setStoreOrderCode(so.getOrderCode());
			List<GoodsOrder> goList = goodsOrderService.selectByCondictions(goTem);
			for (GoodsOrder goodsOrder2 : goList) {
				if(goodsOrder2.getStatus() == 21 || goodsOrder2.getStatus() == 30){
					goodsOrder2.setStatus(40);
					goodsOrder2.setReceiveTime(nowTime);
					goodsOrderService.updateGoodsOrder(goodsOrder2);
				}else{
					return -1;
				}
			}
					
			storeOrderMapper.updateByPrimaryKey(so);
			return 1;
		}else{
			return -1;
		}
	}

	@Override
	public int deleteStoreOrder(Integer storeOrderId) {
		
		try {
			
			StoreOrder storeOrder = storeOrderMapper.selectByPrimaryKey(storeOrderId);
			String orderCode = storeOrder.getOrderCode();
			GoodsOrder goodsOrder = new GoodsOrder();
			goodsOrder.setStoreOrderCode(orderCode);
			
			List<GoodsOrder> goodsOrders = goodsOrderService.selectByCondictions(goodsOrder);
			
			if (!goodsOrders.isEmpty()) {
			// 删除商品订单
			for (GoodsOrder goodsOrder2 : goodsOrders) {
				
				Integer id = goodsOrder2.getId();
				
				goodsOrderService.deleteGoodsOrder(id);
			}
			
			}
			// 删除店铺订单
			storeOrderMapper.deleteByPrimaryKey(storeOrderId);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<StoreOrder> selectCountByTime(QueryCount qc) {
		
		return storeOrderMapper.selectListByTime(qc);
	}

	@Override
	public StoreOrderDetailsDto selectOrderDetailsByOrderCode(String orderCode) {
		
		StoreOrder storeOrder = new StoreOrder();
		storeOrder.setOrderCode(orderCode);
		List<StoreOrder> storeOrderList = storeOrderMapper.selectByConditions(storeOrder);
		if (!storeOrderList.isEmpty()) {
			
			StoreOrder so = storeOrderList.get(0);
			StoreOrderDetailsDto soDto = new StoreOrderDetailsDto();
			
			if (so.getDistributionType() == 2) {
				
				User user = userMapper.selectByPrimaryKey(so.getUserId());
				
				if (user != null) {
					
					if (user.getNickName() != null && !"".equals(user.getNickName())) {
						
						so.setReceivingName(user.getNickName());
					}else{
						if (user.getPhone() != null && !"".equals(user.getPhone())) {
							
							so.setReceivingName(user.getPhone());
						}else{
							
							so.setReceivingName("买家未填写姓名 !");
						}
					}
					/*if (user.getPhone() != null && !"".equals(user.getPhone())) {
						
						so.setReceivingPhone(user.getPhone());
					}*/
				}
			}
			soDto.setStordeOrder(so);
			//店铺头像和 名字
			int storeId = so.getStoreId();
			StoreDto_App store = storeService.getStoreById(storeId);
			if (store != null) {
				if (store.getAvatar() != null) {
					
					soDto.setStoreAvatar(store.getAvatar());
				}
				if (store.getName() != null) {
					
					soDto.setStoreName(store.getName());
				}
			}
			
			//商品订单
			GoodsOrder goTem = new GoodsOrder();
			goTem.setStoreOrderCode(so.getOrderCode());
			List<GoodsOrder> goodsOrderList = goodsOrderService.selectByCondictions(goTem);
			soDto.setGoodsOrderList(goodsOrderList);
			return soDto;
		}else{
			
			return null;
		}
		
	}
}
