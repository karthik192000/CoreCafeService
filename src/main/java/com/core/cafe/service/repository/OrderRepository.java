package com.core.cafe.service.repository;

import com.core.cafe.service.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
                orderToBeUpdated.setEpoch(System.currentTimeMillis());
                updatedOrder = saveOrder(orderToBeUpdated);
            }
        }
        return updatedOrder;
    }

    public List<Order> findByCustomerId(String customerId){
        List<Order> orders = null;
        if(!StringUtils.isEmpty(customerId)){
            Query query = new Query();
            query.addCriteria(Criteria.where("customerId").is(customerId));
            query.addCriteria(Criteria.where("orderStatus").is("INPROGRESS"));
            orders = mongoTemplate.find(query,Order.class,ORDER_COLLECTION);
        }

        return orders;
    }

    public List<Order> findAllOrders(){
        return mongoTemplate.findAll(Order.class,ORDER_COLLECTION);
    }


}
