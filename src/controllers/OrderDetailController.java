package controllers;

import model.entities.OrderDetail;
import model.entities.Product;
import model.repositories.orderDetail.IOrderDetailRepository;

import java.util.List;

public class OrderDetailController {
    private final IOrderDetailRepository orderDetailRepository;

    public OrderDetailController(IOrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public void purchaseProducts(int orderId, List<Product> purchasedProducts) {
        for (Product product : purchasedProducts)
            orderDetailRepository.createOrderDetail(product, orderId);
    }

    public List<OrderDetail> getOrderDetails(String customerName, String customerPhone) {
        return orderDetailRepository.getOrderDetail(customerName, customerPhone);
    }

        public int getTotalSum(String customerName, String customerPhone) {
            return orderDetailRepository.getTotalPrice(customerName, customerPhone);
        }
}
