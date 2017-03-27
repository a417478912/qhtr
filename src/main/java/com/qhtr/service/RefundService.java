package com.qhtr.service;

import com.qhtr.model.RefundOrder;

public interface RefundService {

	int addRefund(RefundOrder refundOrder);

	int addExpressInfo(String orderCode, String expressName, String expressCode);

}
