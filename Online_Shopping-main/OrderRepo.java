import java.util.ArrayList;
import java.util.Date;

public class OrderRepo {
    private static ArrayList<Order> orderList = new ArrayList<>();

    // Getter for the order list
    public static ArrayList<Order> getOrderList() {
        return orderList;
    }

    // Add an order
    public static void addOrder(Order order) {
        orderList.add(order);
        System.out.println("Order added successfully: " + order);
    }

    // Find an order by ID
    public static Order findOrderById(int orderId) {
        for (Order order : orderList) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null; // Return null if not found
    }

    // Find orders by customer ID
    public static ArrayList<Order> findOrdersByCustomerId(int customerId) {
        ArrayList<Order> customerOrders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getCustomerId() == customerId) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    // Calculate total revenue
    public static double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        for (Order order : orderList) {
            totalRevenue += order.getTotalAmount();
        }
        return totalRevenue;
    }

    // View all orders
    public static void viewAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }
        for (Order order : orderList) {
            System.out.println(order.getOrderSummary());
        }
    }
}
