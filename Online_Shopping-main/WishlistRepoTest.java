import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class WishlistRepoTest {

    private WishList wishList1;
    private WishList wishList2;
    private Product product1;
    private Product product2;

    @Before
    public void setUp() {
        
        WishlistRepo.getWishLists().clear();


        product1 = new Product("Laptop", 1500.0, "Electronics", 10);
        product2 = new Product("Phone", 800.0, "Electronics", 20);

      
        wishList1 = new WishList(1); 
        wishList1.addProduct(product1);

        wishList2 = new WishList(2); 
        wishList2.addProduct(product2);
    }

    @Test
    public void testAddWishList() {
        WishlistRepo.addWishList(wishList1);
        Assert.assertEquals(1, WishlistRepo.getWishLists().size());
        Assert.assertTrue(WishlistRepo.getWishLists().contains(wishList1));
    }

    @Test
    public void testFindWishListByCustomerIdSuccess() {
        WishlistRepo.addWishList(wishList1);
        WishlistRepo.addWishList(wishList2);

        WishList foundWishList = WishlistRepo.findWishListByCustomerId(1);
        Assert.assertNotNull(foundWishList);
        Assert.assertEquals(wishList1, foundWishList);
    }

    @Test
    public void testFindWishListByCustomerIdNotFound() {
        WishlistRepo.addWishList(wishList1);
        WishList foundWishList = WishlistRepo.findWishListByCustomerId(999); 
        Assert.assertNull(foundWishList);
    }

    @Test
    public void testRemoveWishListByCustomerIdSuccess() {
        WishlistRepo.addWishList(wishList1);
        boolean removed = WishlistRepo.removeWishListByCustomerId(1); 
        Assert.assertTrue(removed);
        Assert.assertFalse(WishlistRepo.getWishLists().contains(wishList1));
    }

    @Test
    public void testRemoveWishListByCustomerIdNotFound() {
        boolean removed = WishlistRepo.removeWishListByCustomerId(999); 
        Assert.assertFalse(removed);
    }

    @Test
    public void testViewAllWishLists() {
        WishlistRepo.addWishList(wishList1);
        WishlistRepo.addWishList(wishList2);

        System.out.println("All wish lists:");
        WishlistRepo.viewAllWishLists(); 
        Assert.assertEquals(2, WishlistRepo.getWishLists().size());
    }

    @Test
    public void testViewAllWishListsEmpty() {
        System.out.println("No wish lists expected:");
        WishlistRepo.viewAllWishLists(); 
        Assert.assertTrue(WishlistRepo.getWishLists().isEmpty());
    }
}
