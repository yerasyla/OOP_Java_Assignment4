

import controllers.OrderController;
import controllers.OrderDetailController;
import controllers.ProductController;
import model.entities.OrderDetail;
import model.entities.Product;
import model.repositories.orderDetail.IOrderDetailRepository;
import model.repositories.order.IOrderRepository;
import model.repositories.product.IProductRepository;

import java.util.*;

public class Application {
    private final OrderController orderController;
    private final ProductController productController;
    private final OrderDetailController orderDetailController;

    private final Scanner scanner = new Scanner(System.in);

    public Application(IOrderRepository orderRepository, IProductRepository productRepository, IOrderDetailRepository orderDetailRepository) {
        orderController = new OrderController(orderRepository);
        productController = new ProductController(productRepository);
        orderDetailController = new OrderDetailController(orderDetailRepository);
    }

    public void start() {
        String userChoiceMainMenu; // переменная выбора пользователя
        do {
            System.out.println("1 – Purchase products");
            System.out.println("2 – See my orders");
            System.out.println("3 – See total price of my order");
            System.out.println("4 – Exit");
            userChoiceMainMenu = scanner.next();
            switch (userChoiceMainMenu) {
                case "1" -> {
                    System.out.println("Enter your name and phone number");
                    String name = scanner.next();
                    String phone = scanner.next();
                    purchaseProducts(name, phone);
                }
                case "2" -> {
                    System.out.println("Enter your name and phone number");
                    String name = scanner.next();
                    String phone = scanner.next();
                    seeOrders(name, phone);
                }
                case "3" -> {
                    System.out.println("Enter your name and phone number");
                    String name = scanner.next();
                    String phone = scanner.next();
                    System.out.println("Your total sum is " + orderDetailController.getTotalSum(name, phone));
                }
            }
        } while (!userChoiceMainMenu.equals("4"));
    }

    private void seeOrders(String customerName, String customerPhone) {
        List<OrderDetail> orderDetails = orderDetailController.getOrderDetails(customerName, customerPhone); // список объектов класса OrderDetail
        for (OrderDetail orderDetail : orderDetails)
            System.out.println(orderDetail);
    }

    private void purchaseProducts(String customerName, String customerPhone) {
        productController.displayAllProducts();
        System.out.println("Select products you want to purchase");
        System.out.println("Press 0 if you finished shopping");
        List<String> purchasedProducts = new ArrayList<>();
        String productName = scanner.next();
        while (!productName.equals("0")) {
            purchasedProducts.add(productName);
            productName = scanner.next();
        }
        orderController.createOrder(customerName, customerPhone);
        int orderId = orderController.getOrderId(customerName, customerPhone);
        List<Product> products = productController.getProductList(purchasedProducts);
        orderDetailController.purchaseProducts(orderId, products);
    }
}
