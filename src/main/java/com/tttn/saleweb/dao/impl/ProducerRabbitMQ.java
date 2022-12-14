package com.tttn.saleweb.dao.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tttn.saleweb.dao.IProducerRabbitMQ;
import com.tttn.saleweb.model.OrderModel;


@Repository
public class ProducerRabbitMQ implements IProducerRabbitMQ {
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	@Value("${jsa.rabbitmq.routingkey}")
	private String routingkey;

	@Override
	public void sendData(OrderModel order) {
		amqpTemplate.convertAndSend(exchange, routingkey, order);
	}

}
