
import model.database.IDB;
import model.database.PostgresDB;
import model.repositories.order.IOrderRepository;
import model.repositories.order.OrderRepository;
import model.repositories.orderDetail.IOrderDetailRepository;
import model.repositories.orderDetail.OrderDetailRepository;
import model.repositories.product.IProductRepository;
import model.repositories.product.ProductRepository;


import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IOrderRepository orderRepository = new OrderRepository(db);
        IProductRepository productRepository = new ProductRepository(db);
        IOrderDetailRepository orderDetailRepository = new OrderDetailRepository(db);
        Application application = new Application(orderRepository, productRepository, orderDetailRepository);
        application.start();

    }
}