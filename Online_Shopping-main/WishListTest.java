import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class WishListTest {

    private WishList wishList;
    private Product product1;
    private Product product2;

    @Before
    public void setUp() {
        wishList = new WishList(1); 
        product1 = new Product("Laptop", 1500.0, "Electronics", 10);
        product2 = new Product("Phone", 800.0, "Electronics", 20);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(1, wishList.getCustomerId());
        Assert.assertTrue(wishList.getProducts().isEmpty());
    }

    @Test
    public void testAddProductSuccess() {
        wishList.addProduct(product1);
        ArrayList<Product> products = wishList.getProducts();

        Assert.assertEquals(1, products.size());
        Assert.assertTrue(products.contains(product1));
    }

    @Test
    public void testAddProductDuplicate() {
        wishList.addProduct(product1);
        wishList.addProduct(product1); 

        ArrayList<Product> products = wishList.getProducts();
        Assert.assertEquals(1, products.size()); 
    }

    @Test
    public void testRemoveProductSuccess() {
        wishList.addProduct(product1);
        boolean removed = wishList.removeProduct(product1.getId());

        Assert.assertTrue(removed);
        Assert.assertFalse(wishList.getProducts().contains(product1));
    }

    @Test
    public void testRemoveProductNotFound() {
        boolean removed = wishList.removeProduct(999); 
        Assert.assertFalse(removed);
    }

    @Test
    public void testContainsProductTrue() {
        wishList.addProduct(product1);
        boolean contains = wishList.containsProduct(product1.getId());

        Assert.assertTrue(contains);
    }

    @Test
    public void testContainsProductFalse() {
        boolean contains = wishList.containsProduct(999); 
        Assert.assertFalse(contains);
    }

    @Test
    public void testViewWishListWithProducts() {
        wishList.addProduct(product1);
        wishList.addProduct(product2);

        wishList.viewWishList(); 
        Assert.assertEquals(2, wishList.getProducts().size());
    }

    @Test
    public void testViewWishListEmpty() {
        wishList.viewWishList(); 
        Assert.assertTrue(wishList.getProducts().isEmpty());
    }
}
