package com.example.orderservice.controllers;

import com.example.orderservice.entity.Customer;
import com.example.orderservice.messagingrabbitmq.RabbitMQConfiguration;
import com.example.orderservice.repositories.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    private OrderRepository orderRepository;

    public OrderController(RabbitTemplate rabbitTemplate, OrderRepository orderRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderRepository = orderRepository;
    }


    @GetMapping("/orders")
    public List<Customer> orders(){
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    String orderPizza (@RequestBody Customer customer){
        orderRepository.save(customer);
        var orderId = (int)(Math.random() * 10000);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName,"foo.bar.baz", customer.getPizzaName());
        return "Thanks for ordering: " + customer.getPizzaName() + ", your order id is: " + orderId;
    }

}
