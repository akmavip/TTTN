package com.tttn.saleweb.service;

import java.util.List;
import java.util.Map;

import com.tttn.saleweb.entity.Order;
import com.tttn.saleweb.entity.Product;
import com.tttn.saleweb.service.impl.CartService;


public interface IOrderSevice extends IGeneralService<Order, Integer> {
	Order createOrder();
	void addOrderAndOrderDetail(Order o, CartService cart); // thêm mới đơn hàng vào db
	List<Order> getAllOrderByUser();
	Order findById(Integer id);
	Map<Integer, Product> getPurchasedItems();

}
