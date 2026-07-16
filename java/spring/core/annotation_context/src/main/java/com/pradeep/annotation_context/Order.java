package com.pradeep.annotation_context;

import org.springframework.stereotype.Component;

@Component("orderBean")
public class Order {
    public void placeOrder(){
        System.out.println("Order Placed..");
    }
}
