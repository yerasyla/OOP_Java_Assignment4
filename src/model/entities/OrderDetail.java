package model.entities;

public class OrderDetail {
    private final int orderId;
    private final String productName;
    private final int price;

    public OrderDetail(int orderId, String productName, int price) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
    }



    @Override
    public String toString() {
        return "OrderDetail{" + "orderId=" + orderId + ", productName='" + productName + "'" + ", price=" + price + "}";
    }
}
