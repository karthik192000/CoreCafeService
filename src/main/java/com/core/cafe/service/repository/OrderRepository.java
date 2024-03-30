package com.core.cafe.service.repository;

import com.core.cafe.service.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class OrderRepository {


    private static final String ORDER_COLLECTION = "orders";


    @Autowired
    MongoTemplate mongoTemplate;


    public Order saveOrder(Order order){
        Order savedOrder = null;
        if(order != null){
            savedOrder = mongoTemplate.save(order,ORDER_COLLECTION);
        }
        return savedOrder;
    }


    public Order updateOrderStatus(String orderId,String status){
        Order updatedOrder = null;
        if(!StringUtils.isEmpty(orderId) && !StringUtils.isEmpty(status)){
            Order orderToBeUpdated = mongoTemplate.findById(orderId,Order.class,ORDER_COLLECTION);
            if(orderToBeUpdated != null){
                orderToBeUpdated.setOrderStatus(status);
                updatedOrder = saveOrder(orderToBeUpdated);
            }
        }
        return updatedOrder;
    }


}
