package com.qhtr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dto.BuyCartDto;
import com.qhtr.dao.GoodsOrderMapper;
import com.qhtr.model.BuyCart;
import com.qhtr.model.Goods;
import com.qhtr.model.GoodsOrder;
import com.qhtr.model.PayOrder;
import com.qhtr.model.Sku;
import com.qhtr.model.StoreOrder;
import com.qhtr.service.BuyCartService;
import com.qhtr.service.GoodsOrderService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.PayOrderService;
import com.qhtr.service.SkuService;
import com.qhtr.service.StoreOrderService;
import com.qhtr.utils.GenerationUtils;

@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
	@Resource
	public GoodsOrderMapper goodsOrderMapper;
	@Resource 
	public BuyCartService buyCartService;
	@Resource
	public StoreOrderService storeOrderService;
	@Resource
	public PayOrderService payOrderService;
	@Resource
	public GoodsService goodsService;
	@Resource
	public SkuService skuService;
	
	@Override
	public String addGoodsOrder(GoodsOrder goodsOrder) {
		goodsOrder.setOrderCode(GenerationUtils.getGenerationCode("GO", goodsOrder.getUserId()+""));
		goodsOrder.setCreateTime(new Date());
		goodsOrder.setStatus(1);
		int result = goodsOrderMapper.insert(goodsOrder);
		if(result == 1){
			return goodsOrder.getOrderCode();
		}else{
			return null;
		}
	}
	
	@Override
	public List<GoodsOrder> selectByCondictions(GoodsOrder goodsOrder) {
		return goodsOrderMapper.selectByConditions(goodsOrder);
	}

	@Override
	public int updateGoodsOrder(GoodsOrder goodsOrder) {
		return goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
	}

	@Override
	public GoodsOrder selectById(int id) {
		return goodsOrderMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<BuyCartDto> selectFromBuyCartIds(int userId, int[] ids) {
		return buyCartService.selectByIds(ids);
	}

	@Override
	public String addGoodsOrders(int userId, int[] ids, String[] userRemark, int distributionType,int addressId) {
		PayOrder po = new PayOrder();
		po.setOrderCode(GenerationUtils.getGenerationCode("PO", userId+""));
		po.setUserId(userId);
		int payOrderPrice = 0;
 		List<BuyCartDto> bcDtoList = buyCartService.selectByIds(ids);
 		if(bcDtoList.isEmpty()){
 			return null;
 		}
		for (BuyCartDto bc : bcDtoList) {
			StoreOrder so = new StoreOrder();
			so.setPayOrderCode(po.getOrderCode());
			so.setOrderCode(GenerationUtils.getGenerationCode("SO", userId+""));
			so.setStoreId(bc.getStoreId());
			so.setUserId(userId);
			int totalPrice = 0;
			for (BuyCart bc1 : bc.getBuyCartList()) {
				GoodsOrder go = new GoodsOrder();
				go.setOrderCode(GenerationUtils.getGenerationCode("GO", userId+""));
				go.setStoreOrderCode(so.getOrderCode());
				go.setGoodsId(bc1.getGoodsId());
				go.setUserId(userId);
				go.setAddressId(addressId);
				go.setStoreId(bc1.getStoreId());
				go.setSkuId(bc1.getSkuId());
				go.setStatus(1);
				go.setNum(bc1.getNum());
				int theIndex = 0;
				for (int i = 0; i < ids.length; i++) {
					if(ids[i] == bc1.getId()){
						theIndex = i;
						break;
					}
				}
				go.setUserRemark(userRemark[theIndex]);
				go.setPrice(Integer.parseInt(bc1.getString3()));
				go.setDistributionType(distributionType);
				go.setCreateTime(new Date());
				totalPrice += Integer.parseInt(bc1.getString3()) * bc1.getNum();
				goodsOrderMapper.insert(go);
			}
			payOrderPrice += totalPrice;
			so.setTotalPrice(totalPrice);
			so.setStatus(1);
			so.setCreateTime(new Date());
			storeOrderService.insert(so);
		}
		po.setTotalPrice(payOrderPrice);
		po.setStatus(1);
		po.setCreateTime(new Date());
		int result = payOrderService.insert(po);
		if(result == 1){
			buyCartService.deleteByIds(ids);
			return po.getOrderCode();
		}else{
			return null;
		}
	}
	
}
