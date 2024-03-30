package com.core.cafe.service.service.impl;

import com.core.cafe.service.model.Order;
import com.core.cafe.service.repository.OrderRepository;
import com.core.cafe.service.service.OrderService;
import com.core.cafe.service.util.CafeServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order saveOrder(Order order) {
        Order savedOrder = null;
        String userName = CafeServiceUtil.getUserNameFromSecurityContext();
        if(!StringUtils.isEmpty(userName)){
            order.setCustomerId(userName);
            String orderId = userName + "_" +  UUID.randomUUID();
            order.setOrderId(orderId);
            order.setOrderStatus("INPROGRESS");
            savedOrder = orderRepository.saveOrder(order);
        }

        return savedOrder;

    }

    @Override
    public Order updateOrderStatus(String orderId, String orderStatus) {
        return orderRepository.updateOrderStatus(orderId,orderStatus);
    }
}
