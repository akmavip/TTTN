package com.tttn.saleweb.service;

import com.tttn.saleweb.entity.Order;
import com.tttn.saleweb.service.impl.CartService;

public interface IRabbitmqService {
	public void converToSendRabbit(Order o, CartService cart);

}
