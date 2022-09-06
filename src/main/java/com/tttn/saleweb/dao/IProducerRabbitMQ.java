package com.tttn.saleweb.dao;

import com.tttn.saleweb.model.OrderModel;

public interface IProducerRabbitMQ {
	public void sendData(OrderModel order);
}
