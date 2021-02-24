package model.repositories.order;

import model.database.IDB;
import model.entities.Order;

import java.sql.*;
import java.util.ArrayList; // для того, чтобы мы могли использовать ArrayList



public class OrderRepository implements IOrderRepository {
    private final IDB db;


    public OrderRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void createOrder(Order order) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String insertOrder = "INSERT INTO \"order\"(customer_name, customer_phone) VALUES(?, ?)";
            PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrder);
            insertOrderStatement.setString(1, order.getCustomerName());
            insertOrderStatement.setString(2, order.getCustomerPhone());
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
    public int getOrderId(String customerName, String customerPhone) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT order_id FROM \"order\" WHERE customer_name=? AND customer_phone=? ORDER BY order_id DESC LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, customerPhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            connection = db.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT customer_name, customer_phone FROM \"order\"");
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getString(1), resultSet.getString(2)));
            }
            return orders;
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


}
