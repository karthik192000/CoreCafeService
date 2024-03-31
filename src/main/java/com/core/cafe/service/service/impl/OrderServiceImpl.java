package com.core.cafe.service.service.impl;

import com.core.cafe.service.model.Order;
import com.core.cafe.service.repository.OrderRepository;
import com.core.cafe.service.service.OrderService;
import com.core.cafe.service.util.CafeServiceException;
import com.core.cafe.service.util.CafeServiceUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            String userName = CafeServiceUtil.getUserNameFromSecurityContext();
            if (!StringUtils.isEmpty(userName)) {
                order.setCustomerId(userName);
                String orderId = userName + "_" + UUID.randomUUID();
                order.setOrderId(orderId);
                order.setOrderStatus("INPROGRESS");
                order.setEpoch(System.currentTimeMillis());
                savedOrder = orderRepository.saveOrder(order);
            }
        }
        catch (Exception ex){
            throw new CafeServiceException("Exception occurred while Saving order details", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return savedOrder;

    }

    @Override
    public Order updateOrderStatus(String orderId, String orderStatus) {
        Order updatedOrder = null;
        try {
            updatedOrder =  orderRepository.updateOrderStatus(orderId, orderStatus);
        }
        catch (Exception ex){
            throw new CafeServiceException("Excepton occurred while updating order status",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return updatedOrder;
    }


    @Override
    public List<Order> getOrders() {
        List<Order> orderList = new ArrayList<>();
        try {
            String userRole = CafeServiceUtil.getUserRoleFromSecurityContext();
            if (userRole.equals("CUSTOMER")) {
                String userName = CafeServiceUtil.getUserNameFromSecurityContext();
                orderList = orderRepository.findByCustomerId(userName);
            } else {
                orderList = orderRepository.findAllOrders();
            }
            orderList.sort(Comparator.comparing(Order::getEpoch, Comparator.nullsLast(Comparator.reverseOrder())));

        }
        catch (Exception ex){
            throw new CafeServiceException("Exception occurred while Fetching order details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return orderList;
    }
}
