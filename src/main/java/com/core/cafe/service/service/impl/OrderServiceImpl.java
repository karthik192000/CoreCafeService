package com.core.cafe.service.service.impl;

import com.core.cafe.service.model.Order;
import com.core.cafe.service.repository.OrderRepository;
import com.core.cafe.service.service.OrderService;
import com.core.cafe.service.util.CafeServiceUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
            order.setEpoch(System.currentTimeMillis());
            savedOrder = orderRepository.saveOrder(order);
        }

        return savedOrder;

    }

    @Override
    public Order updateOrderStatus(String orderId, String orderStatus) {
        return orderRepository.updateOrderStatus(orderId,orderStatus);
    }


    @Override
    public List<Order> getOrders() {
        List<Order> orderList = new ArrayList<>();
        String userRole = CafeServiceUtil.getUserRoleFromSecurityContext();
        if(userRole.equals("CUSTOMER")){
            String userName = CafeServiceUtil.getUserNameFromSecurityContext();
           orderList =  orderRepository.findByCustomerId(userName);
        }
        else{
            orderList = orderRepository.findAllOrders();
        }
        orderList.sort(Comparator.comparing(Order::getEpoch,Comparator.nullsLast(Comparator.reverseOrder())));

        return orderList;
    }
}
