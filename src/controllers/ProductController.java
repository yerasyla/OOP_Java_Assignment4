package controllers;

import model.entities.Product;
import model.repositories.product.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private final IProductRepository productRepository;

    public ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //method for all products
    public void displayAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        for (Product product : products)
            System.out.println(product.getProductName());
    }

    //getting purchased product names
    public List<Product> getProductList(List<String> purchasedProductNames) {
        List<Product> products = new ArrayList<>();
        for (String productName : purchasedProductNames)
            products.add(productRepository.getProduct(productName));
        return products;
    }
}