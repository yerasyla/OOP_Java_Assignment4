package model.repositories.product;

import model.database.IDB;
import model.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final IDB db;

    public ProductRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Product> getAllProducts() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = db.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM \"product\"");
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            return products;
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Product getProduct(String productName) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM product WHERE product_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            }
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }
}