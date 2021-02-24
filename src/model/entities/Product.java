package model.entities;

public class Product {
    private final int productId;
    private final String productName;
    private final int price;

    public Product(int productId, String productName, int price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + productId +
                ", productName='" + productName + "'" +
                ", price=" + price +
                "}";
    }
}