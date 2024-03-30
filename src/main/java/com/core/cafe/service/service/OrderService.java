package com.core.cafe.service.service;

import com.core.cafe.service.model.Order;

import java.util.List;

public interface OrderService {

    public Order saveOrder(Order order);

    public Order updateOrderStatus(String orderId, String orderStatus);


    public List<Order> getOrders();
}
