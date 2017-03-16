package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dto.BuyCartDto;
import com.qhtr.dao.BuyCartMapper;
import com.qhtr.model.BuyCart;
import com.qhtr.service.BuyCartService;

@Service
public class BuyCartServiceImpl implements BuyCartService{
	@Resource
	private BuyCartMapper buyCartMapper;

	@Override
	public List<BuyCartDto> selectCartsByUserId(int userId) {
		BuyCart buyCart = new BuyCart();
		buyCart.setUserId(userId);
		return buyCartMapper.selectCartsByUserId(buyCart);
	}

	@Override
	public int deleteById(int cartId) {
		return buyCartMapper.deleteByPrimaryKey(cartId);
	}

	@Override
	public int deleteByIds(String[] ids) {
		return buyCartMapper.deleteByIds(ids);
	}

	@Override
	public int addById(BuyCart cart) {
		BuyCart buyCartTem = new BuyCart();
		buyCartTem.setUserId(cart.getUserId());
		buyCartTem.setSkuId(cart.getSkuId());
		List<BuyCart> buyCartList = buyCartMapper.selectByConditions(buyCartTem);
		if(buyCartList.isEmpty()){
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

}