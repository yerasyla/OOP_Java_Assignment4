package model.repositories.product;

import model.entities.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getAllProducts();
    Product getProduct(String productName);
}
