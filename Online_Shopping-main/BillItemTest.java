import org.junit.Assert;
import org.junit.Test;

public class BillItemTest {

    @Test
    public void testConstructorValidInputs() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        Assert.assertEquals(1, item.getProductId());
        Assert.assertEquals("Laptop", item.getProductName());
        Assert.assertEquals(500.0, item.getPrice(), 0.01);
        Assert.assertEquals(2, item.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativePrice() {
        new BillItem(1, "Laptop", -500.0, 2); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeQuantity() {
        new BillItem(1, "Laptop", 500.0, -2); 
    }

    @Test
    public void testSetProductName() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        item.setProductName("Desktop");
        Assert.assertEquals("Desktop", item.getProductName());
    }

    @Test
    public void testSetPriceValid() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        item.setPrice(600.0);
        Assert.assertEquals(600.0, item.getPrice(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPriceNegative() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        item.setPrice(-100.0); 
    }

    @Test
    public void testSetQuantityValid() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        item.setQuantity(3);
        Assert.assertEquals(3, item.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetQuantityNegative() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        item.setQuantity(-1); 
    }

    @Test
    public void testGetTotalCost() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        Assert.assertEquals(1000.0, item.getTotalCost(), 0.01);
    }

    @Test
    public void testToString() {
        BillItem item = new BillItem(1, "Laptop", 500.0, 2);
        String expected = "BillItem [Product ID=1, Name=Laptop, Price=500.00, Quantity=2, Total Cost=1000.00]";
        Assert.assertEquals(expected, item.toString());
    }
}
