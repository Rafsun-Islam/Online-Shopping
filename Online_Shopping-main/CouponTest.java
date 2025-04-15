import org.junit.Test;
import static org.junit.Assert.*;

public class CouponTest {

    @Test
    public void testConstructor_ValidInput() {
        Coupon coupon = new Coupon(12345, 20.0f);
        assertEquals(12345, coupon.getCode());
        assertEquals(20.0f, coupon.getDiscount(), 0.01f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_InvalidDiscountLow() {
        new Coupon(12345, -10.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_InvalidDiscountHigh() {
        new Coupon(12345, 150.0f);
    }

    @Test
    public void testSetCode() {
        Coupon coupon = new Coupon(12345, 10.0f);
        coupon.setCode(54321);
        assertEquals(54321, coupon.getCode());
    }

    @Test
    public void testSetDiscount_ValidInput() {
        Coupon coupon = new Coupon(12345, 10.0f);
        coupon.setDiscount(25.0f);
        assertEquals(25.0f, coupon.getDiscount(), 0.01f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDiscount_InvalidLow() {
        Coupon coupon = new Coupon(12345, 10.0f);
        coupon.setDiscount(-5.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDiscount_InvalidHigh() {
        Coupon coupon = new Coupon(12345, 10.0f);
        coupon.setDiscount(120.0f);
    }

    @Test
    public void testIsValidCoupon_True() {
        Coupon coupon = new Coupon(12345, 10.0f);
        assertTrue(coupon.isValidCoupon());
    }

    @Test
    public void testIsValidCoupon_False() {
        Coupon coupon = new Coupon(12345, 0.0f);
        assertFalse(coupon.isValidCoupon());
    }

    @Test
    public void testApplyDiscount_ValidAmount() {
        Coupon coupon = new Coupon(12345, 20.0f);
        double discountedAmount = coupon.applyDiscount(100.0);
        assertEquals(80.0, discountedAmount, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApplyDiscount_NegativeAmount() {
        Coupon coupon = new Coupon(12345, 20.0f);
        coupon.applyDiscount(-50.0);
    }

    @Test
    public void testToString() {
        Coupon coupon = new Coupon(12345, 20.0f);
        assertEquals("Coupon [Code=12345, Discount=20.00%]", coupon.toString());
    }
}
