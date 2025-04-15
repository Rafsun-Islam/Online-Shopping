import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() {
        product = new Product("Laptop", 1500.0, "Electronics", 10);
    }

    @Test
    public void testConstructorValidInputs() {
        Product product = new Product("Phone", 800.0, "Electronics", 20);

        Assert.assertEquals("Phone", product.getName());
        Assert.assertEquals(800.0, product.getPrice(), 0.01);
        Assert.assertEquals("Electronics", product.getCategory());
        Assert.assertEquals(20, product.getStock());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativePrice() {
        new Product("Phone", -800.0, "Electronics", 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeStock() {
        new Product("Phone", 800.0, "Electronics", -5);
    }

    @Test
    public void testSetNameValid() {
        product.setName("Desktop");
        Assert.assertEquals("Desktop", product.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameInvalid() {
        product.setName("");
    }

    @Test
    public void testSetPriceValid() {
        product.setPrice(1200.0);
        Assert.assertEquals(1200.0, product.getPrice(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPriceNegative() {
        product.setPrice(-100.0); 
    }

    @Test
    public void testSetCategory() {
        product.setCategory("Home Appliances");
        Assert.assertEquals("Home Appliances", product.getCategory());
    }

    @Test
    public void testSetStockValid() {
        product.setStock(15);
        Assert.assertEquals(15, product.getStock());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetStockNegative() {
        product.setStock(-10); 
    }
    @Test
    public void testUpdateStockValid() {
        product.updateStock(5);
        Assert.assertEquals(15, product.getStock());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStockNegative() {
        product.updateStock(-5); 
    }

    @Test
    public void testApplyDiscountValid() {
        product.applyDiscount(10); 
        Assert.assertEquals(1350.0, product.getPrice(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApplyDiscountNegative() {
        product.applyDiscount(-10); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApplyDiscountOver100() {
        product.applyDiscount(110); 
    }

    @Test
    public void testToString() {
        String expected = "Product [ID=" + product.getId() + ", Name=Laptop, Price=1500.00, Category=Electronics, Stock=10]";
        Assert.assertEquals(expected, product.toString());
    }
}
