import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

    private Order order;
    private BillItem item1;
    private BillItem item2;

    @Before
    public void setUp() {
        order = new Order(1); 
        item1 = new BillItem(101, "Laptop", 1000.0, 1);
        item2 = new BillItem(102, "Mouse", 50.0, 2);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(1, order.getCustomerId());
        Assert.assertTrue(order.getItems().isEmpty());
        Assert.assertEquals(0.0, order.getTotalAmount(), 0.01);
    }

    @Test
    public void testAddItem() {
        order.addItem(item1);
        Assert.assertEquals(1, order.getItems().size());
        Assert.assertTrue(order.getItems().contains(item1));
        Assert.assertEquals(1000.0, order.getTotalAmount(), 0.01);
    }

    @Test
    public void testRemoveItemSuccess() {
        order.addItem(item1);
        boolean removed = order.removeItem(item1.getProductId());
        Assert.assertTrue(removed);
        Assert.assertTrue(order.getItems().isEmpty());
        Assert.assertEquals(0.0, order.getTotalAmount(), 0.01);
    }

    @Test
    public void testRemoveItemNotFound() {
        order.addItem(item1);
        boolean removed = order.removeItem(999); 
        Assert.assertFalse(removed);
        Assert.assertEquals(1, order.getItems().size());
    }

    @Test
    public void testUpdateBillItemSuccess() {
        order.addItem(item1);
        boolean updated = order.updateBillItem(item1.getProductId(), 3); 
        Assert.assertTrue(updated);
        Assert.assertEquals(3, order.getItems().get(0).getQuantity());
        Assert.assertEquals(3000.0, order.getTotalAmount(), 0.01);
    }

    @Test
    public void testUpdateBillItemNotFound() {
        boolean updated = order.updateBillItem(999, 3); 
        Assert.assertFalse(updated);
    }

    @Test
    public void testRecalculateTotal() {
        order.addItem(item1);
        order.addItem(item2); 
        Assert.assertEquals(1100.0, order.getTotalAmount(), 0.01);
    }

    @Test
    public void testSetTotalAmountValid() {
        order.setTotalAmount(1500.0);
        Assert.assertEquals(1500.0, order.getTotalAmount(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTotalAmountNegative() {
        order.setTotalAmount(-100.0); 
    }

    @Test
    public void testGetOrderSummary() {
        order.addItem(item1);
        order.addItem(item2);

        String expectedSummary = "Order ID: " + order.getOrderId() + "\n" +
                                 "Customer ID: 1\n" +
                                 "Items:\n" +
                                 item1 + "\n" +
                                 item2 + "\n" +
                                 "Total Amount: 1100.0";

        Assert.assertEquals(expectedSummary, order.getOrderSummary());
    }
}
