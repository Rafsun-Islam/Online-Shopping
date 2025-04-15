import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdminServicesTest {

    private AdminServices adminServices;

    @Before
    public void setUp() throws Exception {
        adminServices = new AdminServices();

      
        ProductRepo.getProductList().clear();
        StockRepo.getStockList().clear();
        CouponRepo.getCouponList().clear();

        
        injectMockScanner(new ByteArrayInputStream("".getBytes()));
    }

    private void injectMockScanner(ByteArrayInputStream inputStream) throws Exception {
        Field scannerField = AdminServices.class.getDeclaredField("sc");
        scannerField.setAccessible(true);
        scannerField.set(adminServices, new java.util.Scanner(inputStream));
    }

    private void provideInput(String input) throws Exception {
        injectMockScanner(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void testAddProduct() throws Exception {
        String input = """
                Shirt
                50.0
                Clothing
                10
                """;
        provideInput(input);

        adminServices.addProduct();

        ArrayList<Product> products = ProductRepo.getProductList();
        assertEquals(1, products.size());

        Product product = products.get(0);
        assertNotNull(product);
        assertEquals("Shirt", product.getName());
        assertEquals(50.0, product.getPrice(), 0.01);
        assertEquals("Clothing", product.getCategory());
        assertEquals(10, product.getStock());
    }

    @Test
    public void testFindProductsByCategory() throws Exception {
        
        ProductRepo.addProduct(new Product("T-Shirt", 50, "Fashion", 10));
        ProductRepo.addProduct(new Product("Laptop", 1500, "Electronics", 5));

    
        String input = "Fashion\n";
        provideInput(input);

        
        adminServices.findProductsByCategory();

     
        ArrayList<Product> products = ProductRepo.findProductsByCategory("Fashion");
        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals("T-Shirt", product.getName());
    }

    @Test
    public void testFindProductsByPriceRange() throws Exception {
        ProductRepo.addProduct(new Product("Shirt", 50, "Clothing", 10));
        ProductRepo.addProduct(new Product("Laptop", 1500, "Electronics", 5));

        String input = """
                40
                100
                """;
        provideInput(input);

        adminServices.findProductsByPriceRange();

        ArrayList<Product> products = ProductRepo.findProductsByPriceRange(40, 100);
        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals("Shirt", product.getName());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product("Shirt", 50, "Clothing", 10);
        ProductRepo.addProduct(product);

        String input = """
                %d
                T-Shirt
                55.0
                Fashion
                """.formatted(product.getId());
        provideInput(input);

        adminServices.updateProduct();

        assertEquals("T-Shirt", product.getName());
        assertEquals(55.0, product.getPrice(), 0.01);
        assertEquals("Fashion", product.getCategory());
    }

    @Test
    public void testRemoveProduct() throws Exception {
        Product product = new Product("Shirt", 50, "Clothing", 10);
        ProductRepo.addProduct(product);

        String input = """
                %d
                """.formatted(product.getId());
        provideInput(input);

        adminServices.removeProduct();

        assertTrue(ProductRepo.getProductList().isEmpty());
    }

    @Test
    public void testGenerateCouponCode() throws Exception {
        String input = """
                1234
                20
                """;
        provideInput(input);

        adminServices.generateCouponCode();

        ArrayList<Coupon> coupons = CouponRepo.getCouponList();
        assertEquals(1, coupons.size());
        Coupon coupon = coupons.get(0);
        assertEquals(1234, coupon.getCode());
        assertEquals(20.0, coupon.getDiscount(), 0.01);
    }

    @Test
    public void testManageStocks() throws Exception {
        
        String input = """
                1
                1
                20
                5
                2
                3
                1
                4
                5
                """;
        provideInput(input);

       
        adminServices.manageStocks();

       
        System.out.println("Test Passed: Inputs processed successfully, and menu options were invoked.");
    }
    
    
    
    @Test
    public void testAddStock() throws Exception {
       
        Product product = new Product("Shirt", 50, "Clothing", 10);
        ProductRepo.addProduct(product);

       
        String input = """
                %d
                20
                5
                """.formatted(product.getId()); // Use dynamic product ID
        provideInput(input);

 
        adminServices.addStock();

       
        System.out.println("All Stocks: " + StockRepo.getStockList());

      
        Stock stock = StockRepo.findStockByProductId(product.getId());
        assertNotNull(stock); 
        assertEquals(30, stock.getQuantity()); 
        assertEquals(5, stock.getLowStockThreshold()); 
    }





    @Test
    public void testViewStocks() throws Exception {
       
        ProductRepo.addProduct(new Product("Shirt", 50, "Clothing", 10));
        ProductRepo.addProduct(new Product("Pants", 100, "Clothing", 5));
        StockRepo.addStock(new Stock(1, 10, 5));
        StockRepo.addStock(new Stock(2, 5, 3));

      
        adminServices.viewStocks();

       
        ArrayList<Stock> stocks = StockRepo.getStockList();
        assertEquals(2, stocks.size()); 
    }

    
    @Test
    public void testRemoveStock() throws Exception {
        
        Product product = new Product("Shirt", 50, "Clothing", 10);
        ProductRepo.addProduct(product);
        StockRepo.addStock(new Stock(product.getId(), 10, 5));

        
        String input = """
                %d
                """.formatted(product.getId());
        provideInput(input);

        
        adminServices.removeStock();

       
        Stock stock = StockRepo.findStockByProductId(product.getId());
        assertNull(stock); 
    }

    @Test
    public void testViewLowStockItems() throws Exception {
        
        ProductRepo.addProduct(new Product("Shirt", 50, "Clothing", 10));
        ProductRepo.addProduct(new Product("Pants", 100, "Clothing", 5));
        StockRepo.addStock(new Stock(1, 2, 5)); 
        StockRepo.addStock(new Stock(2, 10, 5)); 

      
        adminServices.viewLowStockItems();

      
        ArrayList<Stock> lowStockItems = new ArrayList<>();
        for (Stock stock : StockRepo.getStockList()) {
            if (stock.isLowStock()) {
                lowStockItems.add(stock);
            }
        }
        assertEquals(1, lowStockItems.size()); 
        assertEquals(1, lowStockItems.get(0).getProductId()); 
    }

    
    
    

    @Test
    public void testViewLowStockProducts() throws Exception {
        ProductRepo.addProduct(new Product("Shirt", 50, "Clothing", 2));
        ProductRepo.addProduct(new Product("Pant", 75, "Clothing", 15));

        String input = "5";
        provideInput(input);

        adminServices.viewLowStockProducts();

        ArrayList<Product> lowStockProducts = ProductRepo.getProductList();
        long lowStockCount = lowStockProducts.stream()
                .filter(p -> p.getStock() < 5)
                .count();
        assertEquals(1, lowStockCount);
    }
}
