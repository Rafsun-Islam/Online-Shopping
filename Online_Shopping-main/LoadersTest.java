import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class   {

    private Loaders loaders;

    @Before
    public void setUp() {
        loaders = new Loaders();


        CustomerRepo.getCustomerList().clear();
        ProductRepo.getProductList().clear();
        StockRepo.getStockList().clear();
        WishlistRepo.getWishLists().clear();
        CouponRepo.getCouponList().clear();
    }

    @Test
    public void testLoadCustomer() {
        loaders.loadCustomer();

        ArrayList<Customer> customers = CustomerRepo.getCustomerList();
        Assert.assertEquals(3, customers.size());

        Assert.assertEquals("Shawon", customers.get(0).getName());
        Assert.assertEquals("Shawon@gmail.com", customers.get(0).getEmail());
        Assert.assertEquals("Rafsan", customers.get(1).getName());
        Assert.assertEquals("Joha", customers.get(2).getName());
    }

    @Test
    public void testLoadProduct() {
        loaders.loadProduct();

        ArrayList<Product> products = ProductRepo.getProductList();
        Assert.assertEquals(4, products.size());

        Assert.assertEquals("Shirt", products.get(0).getName());
        Assert.assertEquals(50.0, products.get(0).getPrice(), 0.01);
        Assert.assertEquals("Pant", products.get(1).getName());
        Assert.assertEquals(100.0, products.get(1).getPrice(), 0.01);

        ArrayList<Stock> stocks = StockRepo.getStockList();
        Assert.assertEquals(4, stocks.size());

        Assert.assertEquals(products.get(0).getId(), stocks.get(0).getProductId());
        Assert.assertEquals(products.get(1).getId(), stocks.get(1).getProductId());
    }

    @Test
    public void testLoadWishlist() {
        loaders.loadProduct();  
        loaders.loadCustomer(); 
        loaders.loadWishlist(); 

        ArrayList<WishList> wishLists = WishlistRepo.getWishLists();
        Assert.assertEquals(2, wishLists.size());

        WishList wl1 = wishLists.get(0);
        Assert.assertNotNull("wl1 is null!", wl1);
        Assert.assertEquals(1, wl1.getCustomerId());
        Assert.assertEquals(2, wl1.getProducts().size());

        ArrayList<Product> products = ProductRepo.getProductList();
        Assert.assertEquals(products.get(0).getId(), wl1.getProducts().get(0).getId());
        Assert.assertEquals(products.get(1).getId(), wl1.getProducts().get(1).getId());

        WishList wl2 = wishLists.get(1);
        Assert.assertNotNull("wl2 is null!", wl2);
        Assert.assertEquals(2, wl2.getCustomerId());
        Assert.assertEquals(2, wl2.getProducts().size());

        Assert.assertEquals(products.get(2).getId(), wl2.getProducts().get(0).getId());
        Assert.assertEquals(products.get(3).getId(), wl2.getProducts().get(1).getId());
    }

    @Test
    public void testLoadCoupon() {
        loaders.loadCoupon();

        ArrayList<Coupon> coupons = CouponRepo.getCouponList();
        Assert.assertEquals(3, coupons.size());

        Assert.assertEquals(999, coupons.get(0).getCode());
        Assert.assertEquals(10.0, coupons.get(0).getDiscount(), 0.01);
        Assert.assertEquals(888, coupons.get(1).getCode());
        Assert.assertEquals(20.0, coupons.get(1).getDiscount(), 0.01);
        Assert.assertEquals(777, coupons.get(2).getCode());
        Assert.assertEquals(50.0, coupons.get(2).getDiscount(), 0.01);
    }

    @Test
    public void testLoadCouponWithHighestDiscount() {
        loaders.loadCoupon();

        Coupon highestDiscountCoupon = CouponRepo.getHighestDiscountCoupon();
        Assert.assertNotNull(highestDiscountCoupon);
        Assert.assertEquals(50.0, highestDiscountCoupon.getDiscount(), 0.01);
        Assert.assertEquals(777, highestDiscountCoupon.getCode());
    }
}
