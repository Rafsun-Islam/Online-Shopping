import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class CouponRepoTest {
	
	 @Before
	    public void setUp() {
	 
	        CouponRepo.getCouponList().clear();
	    }

    @Test
    public void testAddCoupon() {
        Coupon coupon = new Coupon(1001, 20.0f);
        CouponRepo.addCoupon(coupon);
        assertTrue(CouponRepo.getCouponList().contains(coupon));
    }

    @Test
    public void testFindCouponByCode_Found() {
        Coupon coupon = new Coupon(1002, 15.0f);
        CouponRepo.addCoupon(coupon);
        Coupon found = CouponRepo.findCouponByCode(1002);
        assertNotNull(found);
        assertEquals(1002, found.getCode());
    }

    @Test
    public void testFindCouponByCode_NotFound() {
        Coupon found = CouponRepo.findCouponByCode(9999);
        assertNull(found);
    }

    @Test
    public void testRemoveCouponByCode_Found() {
        Coupon coupon = new Coupon(1003, 25.0f);
        CouponRepo.addCoupon(coupon);
        boolean removed = CouponRepo.removeCouponByCode(1003);
        assertTrue(removed);
        assertFalse(CouponRepo.getCouponList().contains(coupon));
    }

    @Test
    public void testRemoveCouponByCode_NotFound() {
        boolean removed = CouponRepo.removeCouponByCode(9999);
        assertFalse(removed);
    }

    @Test
    public void testUpdateCoupon_Found() {
        Coupon coupon = new Coupon(1004, 30.0f);
        CouponRepo.addCoupon(coupon);
        boolean updated = CouponRepo.updateCoupon(1004, 50.0f);
        assertTrue(updated);
        assertEquals(50.0f, CouponRepo.findCouponByCode(1004).getDiscount(), 0.01f);
    }

    @Test
    public void testUpdateCoupon_NotFound() {
        boolean updated = CouponRepo.updateCoupon(9999, 50.0f);
        assertFalse(updated);
    }

    @Test
    public void testGetValidCoupons() {
        Coupon validCoupon = new Coupon(1005, 10.0f);
        Coupon invalidCoupon = new Coupon(1006, 0.0f);
        CouponRepo.addCoupon(validCoupon);
        CouponRepo.addCoupon(invalidCoupon);
        ArrayList<Coupon> validCoupons = CouponRepo.getValidCoupons();
        assertTrue(validCoupons.contains(validCoupon));
        assertFalse(validCoupons.contains(invalidCoupon));
    }

    @Test
    public void testGetHighestDiscountCoupon() {
        Coupon coupon1 = new Coupon(1007, 10.0f);
        Coupon coupon2 = new Coupon(1008, 50.0f);
        Coupon coupon3 = new Coupon(1009, 30.0f);
        
        CouponRepo.addCoupon(coupon1);
        CouponRepo.addCoupon(coupon2);
        CouponRepo.addCoupon(coupon3);
        
        Coupon highest = CouponRepo.getHighestDiscountCoupon();
        assertNotNull(highest);
        assertEquals(1008, highest.getCode());
    }

    @Test
    public void testGetHighestDiscountCoupon_EmptyRepo() {
        CouponRepo.getCouponList().clear();
        Coupon highest = CouponRepo.getHighestDiscountCoupon();
        assertNull(highest);
    }

    @Test
    public void testViewCoupons() {
        Coupon coupon = new Coupon(1010, 20.0f);
        CouponRepo.addCoupon(coupon);
        CouponRepo.viewCoupons();
       
    }
}
