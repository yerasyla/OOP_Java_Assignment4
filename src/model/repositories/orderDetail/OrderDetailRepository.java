package model.repositories.orderDetail;

import model.database.IDB;
import model.entities.OrderDetail;
import model.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailRepository implements IOrderDetailRepository {
    private final IDB db;

    public OrderDetailRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void createOrderDetail(Product product, int orderId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String insertOrder = "INSERT INTO \"order-detail\" VALUES(?, ?)";
            PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrder);
            insertOrderStatement.setInt(1, orderId);
            insertOrderStatement.setInt(2, product.getProductId());
            insertOrderStatement.execute();
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
    }

    @Override
    public ArrayList<OrderDetail> getOrderDetail(String customerName, String customerPhone) {
        Connection connection = null;
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        try {
            connection = db.getConnection();
            String sql = "SELECT \"order\".order_id, product.product_name, product.price FROM \"order\" INNER JOIN \"order-detail\" ON \"order\".order_id = \"order-detail\".order_id INNER JOIN product ON \"order-detail\".product_id = product.product_id WHERE \"order\".customer_name = ? AND \"order\".customer_phone = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, customerPhone);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderDetails.add(new OrderDetail(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            return orderDetails;
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int getTotalPrice(String customerName, String customerPhone) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT SUM(PRODUCT.price) AS total_price FROM \"order-detail\" INNER JOIN PRODUCT ON \"order-detail\".product_id = PRODUCT.product_id INNER JOIN \"order\" as o on \"order-detail\".order_id = o.order_id WHERE o.customer_name = ? AND  o.customer_phone = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, customerPhone);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}