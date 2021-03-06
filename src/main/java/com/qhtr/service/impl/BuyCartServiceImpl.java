package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dto.BuyCartDto;
import com.qhtr.dao.BuyCartMapper;
import com.qhtr.dto.GoodsDto;
import com.qhtr.model.BuyCart;
import com.qhtr.model.Goods;
import com.qhtr.model.Sku;
import com.qhtr.service.BuyCartService;
import com.qhtr.service.GoodsService;
import com.qhtr.service.SkuService;

@Service
public class BuyCartServiceImpl implements BuyCartService{
	@Resource
	private BuyCartMapper buyCartMapper;
	@Resource
	public GoodsService goodsService;
	@Resource
	public SkuService skuService;

	@Override
	public List<BuyCartDto> selectCartsByUserId(int userId) {
		BuyCart buyCart = new BuyCart();
		buyCart.setUserId(userId);
		List<BuyCartDto> list = buyCartMapper.selectCartsByUserId(buyCart);
		for (BuyCartDto cartDto : list) {
			List<BuyCart> cartList = cartDto.getBuyCartList();
			for (BuyCart cart : cartList) {
				Integer goodsId = cart.getGoodsId();
				Integer skuId = cart.getSkuId();
				Goods goods = goodsService.selectGoodsByPrimaryKey(goodsId);
				Sku sku = skuService.selectSkuBySkuId(skuId);
				cart.setGoodsName(goods.getName());
				cart.setSkuDetail(sku.getAttrDetails());
				cart.setPrice(sku.getPrice()*cart.getNum()+"");
				cart.setGoodsCode(goods.getGoodsCode());
				cart.setGoodsThumbs(goods.getThumb());
			}
		}
		return list;
	}

	@Override
	public int deleteById(int cartId) {
		return buyCartMapper.deleteByPrimaryKey(cartId);
	}

	@Override
	public int deleteByIds(int[] ids) {
		return buyCartMapper.deleteByIds(ids);
	}

	@Override
	public int addById(BuyCart cart) {
		
		BuyCart buyCartTem = new BuyCart();
		buyCartTem.setUserId(cart.getUserId());
		buyCartTem.setSkuId(cart.getSkuId());
		List<BuyCart> buyCartList = buyCartMapper.selectByConditions(buyCartTem);
		System.out.println(buyCartList.size()+"================================================================");
		if(buyCartList.isEmpty()){
			
			Goods goods = goodsService.selectGoodsByPrimaryKey(cart.getGoodsId());
			System.out.println(goods.getName()+"==============================================================");
			cart.setGoodsName(goods.getName());
			Sku sku = skuService.selectSkuBySkuId(cart.getSkuId());
			cart.setSkuDetail(sku.getAttrDetails());
			cart.setGoodsCode(goods.getGoodsCode());
			cart.setPrice(sku.getPrice()+"");
			cart.setGoodsThumbs(goods.getThumb());
			return buyCartMapper.insert(cart);
		}else{
			
			BuyCart cart1 = buyCartList.get(0);
			cart1.setNum(cart1.getNum() + cart.getNum());
			return buyCartMapper.updateByPrimaryKey(cart1);
		}
	}

	@Override
	public int updateById(BuyCart cart) {
		return buyCartMapper.updateByPrimaryKeySelective(cart);
	}

	@Override
	public int updateBatch(List<BuyCart> carts) {
		if(carts.isEmpty()){
			return 0;
		}else{
			for (BuyCart b : carts) {
				if(buyCartMapper.updateByPrimaryKeySelective(b) == 0){
					return 0;
				};
			}
		}
		return 1;
	}

	@Override
	public BuyCart selectById(int id) {
		return buyCartMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BuyCartDto> selectByIds(int[] ids) {
		return buyCartMapper.selectByIds(ids);
	}

}
