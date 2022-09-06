//package com.tttn.saleweb.config.queue;
//
//import com.tttn.saleweb.constant.RabbitMQConstant;
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitQueueDefine {
//
//    @Autowired
//    @Qualifier("amqpAdmin")
//    AmqpAdmin rabbitAdmin;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void initExchange() {
//        this.initExchangeRabbit();
//    }
//
//    private void initExchangeRabbit() {
//        DirectExchange exchange = new DirectExchange(RabbitMQConstant.TTTN_EXCHANGE);
//        this.declareQueue(null, exchange, null);
//
//        String queueNameCapture = RabbitMQConstant.TTTN_QUEUE;
//        Queue queueCapture = new Queue(queueNameCapture, false, false, false, null);
//        Binding bindingCapture = new Binding(queueNameCapture, Binding.DestinationType.QUEUE, RabbitMQConstant.TTTN_EXCHANGE, queueNameCapture, null);
//        this.declareQueue(queueCapture, null, bindingCapture);
//
//    }
//
//    public void declareQueue(Queue queue, Exchange exchange, Binding binding) {
//        if (queue != null) {
//            rabbitAdmin.declareQueue(queue);
//        }
//        if (exchange != null) {
//            rabbitAdmin.declareExchange(exchange);
//        }
//        if (binding != null) {
//            rabbitAdmin.declareBinding(binding);
//        }
//    }
//}