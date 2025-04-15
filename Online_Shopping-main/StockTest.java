import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockTest {

    private Stock stock;

    @Before
    public void setUp() {
       
        stock = new Stock(101, 50, 10); 
    }

    @Test
    public void testConstructorValidInputs() {
        Stock stock = new Stock(102, 100, 20);
        Assert.assertEquals(102, stock.getProductId());
        Assert.assertEquals(100, stock.getQuantity());
        Assert.assertEquals(20, stock.getLowStockThreshold());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeQuantity() {
        new Stock(103, -10, 5); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeThreshold() {
        new Stock(104, 10, -5); 
    }

    @Test
    public void testSetQuantityValid() {
        stock.setQuantity(70);
        Assert.assertEquals(70, stock.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetQuantityNegative() {
        stock.setQuantity(-10); 
    }

    @Test
    public void testSetLowStockThresholdValid() {
        stock.setLowStockThreshold(20);
        Assert.assertEquals(20, stock.getLowStockThreshold());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLowStockThresholdNegative() {
        stock.setLowStockThreshold(-5); 
    }

    @Test
    public void testAddStockValid() {
        stock.addStock(30); 
        Assert.assertEquals(80, stock.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddStockNegative() {
        stock.addStock(-10); 
    }

    @Test
    public void testRemoveStockValid() {
        stock.removeStock(20);
        Assert.assertEquals(30, stock.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveStockNegative() {
        stock.removeStock(-10); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveStockInsufficient() {
        stock.removeStock(60); 
    }

    @Test
    public void testIsLowStockTrue() {
        stock.setQuantity(10);
        Assert.assertTrue(stock.isLowStock());
    }

    @Test
    public void testIsLowStockFalse() {
        stock.setQuantity(20); 
        Assert.assertFalse(stock.isLowStock());
    }

    @Test
    public void testToString() {
        String expected = "Stock [Product ID=101, Quantity=50, Low Stock Threshold=10]";
        Assert.assertEquals(expected, stock.toString());
    }
}
