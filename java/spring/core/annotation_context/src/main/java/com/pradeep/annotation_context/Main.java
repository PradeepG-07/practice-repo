package com.pradeep.annotation_context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Order order  = context.getBean(Order.class);
        order.placeOrder();

        ExternalModel model = context.getBean(ExternalModel.class);
        model.doSomething();

        Order order1 = (Order) context.getBean("orderBean");
        order1.placeOrder();
    }
}