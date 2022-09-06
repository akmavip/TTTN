package com.tttn.saleweb.dao;

import java.util.List;

import com.tttn.saleweb.entity.Customer;
import com.tttn.saleweb.entity.Order;
import com.tttn.saleweb.service.impl.CartService;

public interface IOrderDAO extends IGeneralDAO<Order, Integer> {

//	void create(Order o, CartService cart);
	List<Order> findByUser(Customer user);

}
