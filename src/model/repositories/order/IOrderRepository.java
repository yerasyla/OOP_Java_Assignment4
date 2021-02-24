package model.repositories.order;

import model.entities.Order;

import java.util.List;

public interface IOrderRepository {
    void createOrder(Order order);
    int getOrderId(String customerName, String customerPhone);
    List<Order> getAllOrders();

    //todo

}