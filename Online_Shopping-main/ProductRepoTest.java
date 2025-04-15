import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProductRepoTest {

    private Product product1;
    private Product product2;
    private Product product3;

    @Before
    public void setUp() {

        ProductRepo.getProductList().clear();

  
        product1 = new Product("Laptop", 1000.0, "Electronics", 5);
        product2 = new Product("Phone", 800.0, "Electronics", 15);
        product3 = new Product("Shirt", 50.0, "Clothing", 10);
    }

    @Test
    public void testAddProduct() {
        ProductRepo.addProduct(product1);
        Assert.assertEquals(1, ProductRepo.getProductList().size());
        Assert.assertTrue(ProductRepo.getProductList().contains(product1));
    }

    @Test
    public void testFindProductByIdSuccess() {
        ProductRepo.addProduct(product1);
        ProductRepo.addProduct(product2);

        Product foundProduct = ProductRepo.findProductById(product1.getId());
        Assert.assertNotNull(foundProduct);
        Assert.assertEquals(product1, foundProduct);
    }

    @Test
    public void testFindProductByIdNotFound() {
        ProductRepo.addProduct(product1);
        Product foundProduct = ProductRepo.findProductById(999); 
        Assert.assertNull(foundProduct);
    }

    @Test
    public void testFindProductsByNameSuccess() {
        ProductRepo.addProduct(product1);
        ProductRepo.addProduct(product2);

        ArrayList<Product> matchingProducts = ProductRepo.findProductsByName("Laptop");
        Assert.assertEquals(1, matchingProducts.size());
        Assert.assertTrue(matchingProducts.contains(product1));
    }

    @Test
    public void testFindProductsByNameNotFound() {
        ProductRepo.addProduct(product1);
        ArrayList<Product> matchingProducts = ProductRepo.findProductsByName("Tablet");
        Assert.assertTrue(matchingProducts.isEmpty());
    }

    @Test
    public void testFindProductsByCategorySuccess() {
        ProductRepo.addProduct(product1);
        ProductRepo.addProduct(product2);
        ProductRepo.addProduct(product3);

        ArrayList<Product> electronics = ProductRepo.findProductsByCategory("Electronics");
        Assert.assertEquals(2, electronics.size());
        Assert.assertTrue(electronics.contains(product1));
        Assert.assertTrue(electronics.contains(product2));
    }

    @Test
    public void testFindProductsByCategoryNotFound() {
        ProductRepo.addProduct(product1);
        ArrayList<Product> furniture = ProductRepo.findProductsByCategory("Furniture");
        Assert.assertTrue(furniture.isEmpty());
    }

    @Test
    public void testFindProductsByPriceRangeSuccess() {
        ProductRepo.addProduct(product1);
        ProductRepo.addProduct(product2);
        ProductRepo.addProduct(product3);

        ArrayList<Product> productsInRange = ProductRepo.findProductsByPriceRange(50.0, 1000.0);
        Assert.assertEquals(3, productsInRange.size());
        Assert.assertTrue(productsInRange.contains(product1));
        Assert.assertTrue(productsInRange.contains(product2));
        Assert.assertTrue(productsInRange.contains(product3));
    }

    @Test
    public void testFindProductsByPriceRangeEmpty() {
        ProductRepo.addProduct(product1);
        ProductRepo.addProduct(product2);

        ArrayList<Product> productsInRange = ProductRepo.findProductsByPriceRange(1500.0, 2000.0);
        Assert.assertTrue(productsInRange.isEmpty());
    }

    @Test
    public void testViewLowStockProducts() {
        ProductRepo.addProduct(product1);
        ProductRepo.addProduct(product2);
        ProductRepo.addProduct(product3);

        ProductRepo.viewLowStockProducts(10); 
        Assert.assertTrue(ProductRepo.getProductList().contains(product1));
    }

    @Test
    public void testRemoveProductByIdSuccess() {
        ProductRepo.addProduct(product1);
        boolean removed = ProductRepo.removeProductById(product1.getId());
        Assert.assertTrue(removed);
        Assert.assertFalse(ProductRepo.getProductList().contains(product1));
    }

    @Test
    public void testRemoveProductByIdNotFound() {
        boolean removed = ProductRepo.removeProductById(999); 
        Assert.assertFalse(removed);
    }

    @Test
    public void testViewAllProducts() {
        ProductRepo.addProduct(product1);
        ProductRepo.addProduct(product2);

        ProductRepo.viewAllProducts(); 
        Assert.assertEquals(2, ProductRepo.getProductList().size());
    }

    @Test
    public void testViewAllProductsEmpty() {
        ProductRepo.viewAllProducts(); 
        Assert.assertTrue(ProductRepo.getProductList().isEmpty());
    }
}
