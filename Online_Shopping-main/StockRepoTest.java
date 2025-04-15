import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StockRepoTest {

    private Stock stock1;
    private Stock stock2;

    @Before
    public void setUp() {
       
        StockRepo.getStockList().clear();

        
        stock1 = new Stock(101, 50, 10); 
        stock2 = new Stock(102, 5, 10);  
    }

    @Test
    public void testAddStock() {
        StockRepo.addStock(stock1);
        Assert.assertEquals(1, StockRepo.getStockList().size());
        Assert.assertTrue(StockRepo.getStockList().contains(stock1));
    }

    @Test
    public void testFindStockByProductIdSuccess() {
        StockRepo.addStock(stock1);
        StockRepo.addStock(stock2);

        Stock foundStock = StockRepo.findStockByProductId(stock1.getProductId());
        Assert.assertNotNull(foundStock);
        Assert.assertEquals(stock1, foundStock);
    }

    @Test
    public void testFindStockByProductIdNotFound() {
        StockRepo.addStock(stock1);
        Stock foundStock = StockRepo.findStockByProductId(999);
        Assert.assertNull(foundStock);
    }

    @Test
    public void testUpdateStockQuantitySuccess() {
        StockRepo.addStock(stock1);
        boolean updated = StockRepo.updateStockQuantity(stock1.getProductId(), 100); 
        Assert.assertTrue(updated);
        Assert.assertEquals(100, stock1.getQuantity());
    }

    @Test
    public void testUpdateStockQuantityNotFound() {
        boolean updated = StockRepo.updateStockQuantity(999, 100); 
        Assert.assertFalse(updated);
    }

    @Test
    public void testViewLowStockItems() {
        StockRepo.addStock(stock1);
        StockRepo.addStock(stock2);

        System.out.println("Expected low stock items:");
        StockRepo.viewLowStockItems(); 
        Assert.assertTrue(stock2.isLowStock());
        Assert.assertFalse(stock1.isLowStock());
    }

    @Test
    public void testRemoveStockByProductIdSuccess() {
        StockRepo.addStock(stock1);
        boolean removed = StockRepo.removeStockByProductId(stock1.getProductId());
        Assert.assertTrue(removed);
        Assert.assertFalse(StockRepo.getStockList().contains(stock1));
    }

    @Test
    public void testRemoveStockByProductIdNotFound() {
        boolean removed = StockRepo.removeStockByProductId(999); 
        Assert.assertFalse(removed);
    }

    @Test
    public void testViewAllStock() {
        StockRepo.addStock(stock1);
        StockRepo.addStock(stock2);

        System.out.println("All stock records:");
        StockRepo.viewAllStock(); 
        Assert.assertEquals(2, StockRepo.getStockList().size());
    }

    @Test
    public void testViewAllStockEmpty() {
        System.out.println("No stock records expected:");
        StockRepo.viewAllStock(); 
        Assert.assertTrue(StockRepo.getStockList().isEmpty());
    }
}
