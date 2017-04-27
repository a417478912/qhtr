package com.qhtr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dto.StoreDto_App;
import com.qhtr.model.Address;
import com.qhtr.service.AddressService;
import com.qhtr.service.StoreService;
import com.qhtr.service.UUpaotuiService;
import com.qhtr.utils.UUPaotuiUtils;

@Service
public class UUpaotuiServiceImpl implements UUpaotuiService {
	@Resource
	public StoreService storeService;
	@Resource
	public AddressService addressService;

	@Override
	public String getExpressOrderPrice(int addressId, int storeId) {
		Address address = addressService.getAddressByid(addressId);
		StoreDto_App store = storeService.getStoreById(storeId);
		String result = UUPaotuiUtils.getOrderPrice(store.getLocation(), address.getDetails(), store.getLongitude(), store.getLatitude(), "0", "0");
		return result;
	}

	@Override
	public String addOrder(String price_token, String order_price, String balance_paymoney, String receiver,
			String receiver_phone, String note, String pubUserMobile) {
		return UUPaotuiUtils.addOrder(price_token, order_price, balance_paymoney, receiver, receiver_phone, note, pubUserMobile);
	}

	@Override
	public String cancelOrder(String order_code, String reason) {
		return UUPaotuiUtils.cancelOrder(order_code, reason);
	}

	@Override
	public String GetOrderDetail(String order_code) {
		return UUPaotuiUtils.GetOrderDetail(order_code);
	}
}
