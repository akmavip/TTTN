package com.tttn.saleweb.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.saleweb.dao.IProducerRabbitMQ;
import com.tttn.saleweb.entity.Order;
import com.tttn.saleweb.entity.Product;
import com.tttn.saleweb.model.OrderDetails;
import com.tttn.saleweb.model.OrderModel;
import com.tttn.saleweb.service.IRabbitmqService;

@Service
public class RabbitMqService implements IRabbitmqService {

	@Autowired
	private IProducerRabbitMQ producer;

	@Override
	public void converToSendRabbit(Order o, CartService cart) {
		OrderModel model = new OrderModel();
		model.setAddress(o.getAddress());
		model.setAmount(o.getAmount());
		model.setCustomer(o.getCustomer());
		model.setDescription(o.getDescription());
		model.setPhone(o.getPhone());
		model.setStatus(1);
		Collection<Product> items = cart.getItemsCart();
		List<OrderDetails> s = new ArrayList<OrderDetails>();
		for (Product p : items) {
			OrderDetails detail = new OrderDetails();
			detail.setProductID(p.getId());
			detail.setUnitPrice(p.getUnitPrice());
			detail.setDiscount(p.getDiscount());
			detail.setQuantity(p.getQuantity());
			s.add(detail);
		}
		model.setOrderDetails(s);
		producer.sendData(model);
	}

}
