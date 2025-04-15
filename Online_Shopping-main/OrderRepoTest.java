import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OrderRepoTest {

    private Order order1;
    private Order order2;
    private BillItem item1;
    private BillItem item2;

    @Before
    public void setUp() {
      
        OrderRepo.getOrderList().clear();

    
        item1 = new BillItem(101, "Laptop", 1000.0, 1);
        item2 = new BillItem(102, "Mouse", 50.0, 2);

        order1 = new Order(1);
        order1.addItem(item1);

        order2 = new Order(2); 
        order2.addItem(item2);
    }

    @Test
    public void testAddOrder() {
        OrderRepo.addOrder(order1);
        Assert.assertEquals(1, OrderRepo.getOrderList().size());
        Assert.assertTrue(OrderRepo.getOrderList().contains(order1));
    }

    @Test
    public void testFindOrderByIdSuccess() {
        OrderRepo.addOrder(order1);
        OrderRepo.addOrder(order2);

        Order foundOrder = OrderRepo.findOrderById(order1.getOrderId());
        Assert.assertNotNull(foundOrder);
        Assert.assertEquals(order1, foundOrder);
    }

    @Test
    public void testFindOrderByIdNotFound() {
        OrderRepo.addOrder(order1);
        Order foundOrder = OrderRepo.findOrderById(999); 
        Assert.assertNull(foundOrder);
    }

    @Test
    public void testFindOrdersByCustomerIdSuccess() {
        OrderRepo.addOrder(order1);
        OrderRepo.addOrder(order2);

        ArrayList<Order> customerOrders = OrderRepo.findOrdersByCustomerId(1);
        Assert.assertEquals(1, customerOrders.size());
        Assert.assertTrue(customerOrders.contains(order1));
    }

    @Test
    public void testFindOrdersByCustomerIdNoOrders() {
        ArrayList<Order> customerOrders = OrderRepo.findOrdersByCustomerId(3); 
        Assert.assertTrue(customerOrders.isEmpty());
    }

    @Test
    public void testCalculateTotalRevenue() {
        OrderRepo.addOrder(order1);
        OrderRepo.addOrder(order2);

        double expectedRevenue = order1.getTotalAmount() + order2.getTotalAmount();
        Assert.assertEquals(expectedRevenue, OrderRepo.calculateTotalRevenue(), 0.01);
    }

    @Test
    public void testCalculateTotalRevenueEmptyRepo() {
        Assert.assertEquals(0.0, OrderRepo.calculateTotalRevenue(), 0.01);
    }

    @Test
    public void testViewAllOrders() {
        OrderRepo.addOrder(order1);
        OrderRepo.addOrder(order2);

        OrderRepo.viewAllOrders(); 
        Assert.assertEquals(2, OrderRepo.getOrderList().size());
    }

    @Test
    public void testViewAllOrdersEmptyRepo() {
        OrderRepo.viewAllOrders(); 
        Assert.assertTrue(OrderRepo.getOrderList().isEmpty());
    }
}
