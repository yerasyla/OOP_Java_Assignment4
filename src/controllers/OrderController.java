package controllers;

import model.entities.Order;
import model.repositories.order.IOrderRepository;

import java.util.List;

public class    OrderController {
    private final IOrderRepository repository;

    public OrderController(IOrderRepository repository) {
        this.repository = repository;
    }

    public void createOrder(String customerName, String customerPhone) {
        //to avoid duplicating
        if (isOrderExist(customerName, customerPhone))
            return;
        repository.createOrder(new Order(customerName, customerPhone));
    }
    //check for order in DB
    private boolean isOrderExist(String customerName, String customerPhone) {
        List<Order> orders = repository.getAllOrders();
        for (Order order : orders)
            if (order.getCustomerName().equals(customerName) && order.getCustomerPhone().equals(customerPhone))
                return true;
        return false;
    }

    //getting order id by customer name and phone
    public int getOrderId(String customerName, String customerPhone) {
        return repository.getOrderId(customerName, customerPhone);
    }
}