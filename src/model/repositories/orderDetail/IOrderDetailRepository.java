package model.repositories.orderDetail;

import model.entities.OrderDetail;
import model.entities.Product;

import java.util.List;

public interface IOrderDetailRepository {
    void createOrderDetail(Product product, int orderId);
    List<OrderDetail> getOrderDetail(String customerName, String customerPhone);
    int getTotalPrice(String customerName, String customerPhone);
}