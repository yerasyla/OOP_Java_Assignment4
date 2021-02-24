package model.entities;

public class Order {
    private final String customerName;
    private final String customerPhone;

    public Order(String customerName, String customerPhone) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer name='" + customerName + "'" +
                ", customer phone='" + customerPhone + "'" +
                "}";
    }
}
