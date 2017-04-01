package com.qhtr.service;

import com.qhtr.model.Seller;

public interface SellerService {
	public Seller getSellerById(int id);

	public int addRegister(String phone, String password);
}
