import java.util.ArrayList;

public class CouponRepo {
    private static ArrayList<Coupon> couponList = new ArrayList<>();

    // Getter for the coupon list
    public static ArrayList<Coupon> getCouponList() {
        return couponList;
    }

    // Add a coupon to the repository
    public static void addCoupon(Coupon coupon) {
        couponList.add(coupon);
        System.out.println("Coupon added successfully: " + coupon);
    }

    // Find a coupon by code
    public static Coupon findCouponByCode(int code) {
        for (Coupon coupon : couponList) {
            if (coupon.getCode() == code) {
                return coupon;
            }
        }
        return null; // Return null if not found
    }

    // Remove a coupon by code
    public static boolean removeCouponByCode(int code) {
        Coupon coupon = findCouponByCode(code);
        if (coupon != null) {
            couponList.remove(coupon);
            System.out.println("Coupon removed successfully: " + coupon);
            return true;
        }
        System.out.println("Coupon with code " + code + " not found.");
        return false;
    }

    // Update an existing coupon
    public static boolean updateCoupon(int code, float newDiscount) {
        Coupon coupon = findCouponByCode(code);
        if (coupon != null) {
            coupon.setDiscount(newDiscount);
            System.out.println("Coupon updated successfully: " + coupon);
            return true;
        }
        System.out.println("Coupon with code " + code + " not found.");
        return false;
    }

    // Get all valid coupons
    public static ArrayList<Coupon> getValidCoupons() {
        ArrayList<Coupon> validCoupons = new ArrayList<>();
        for (Coupon coupon : couponList) {
            if (coupon.isValidCoupon()) {
                validCoupons.add(coupon);
            }
        }
        return validCoupons;
    }

    // Get the coupon with the highest discount
    public static Coupon getHighestDiscountCoupon() {
        if (couponList.isEmpty()) {
            return null;
        }

        Coupon highest = couponList.get(0);
        for (Coupon coupon : couponList) {
            if (coupon.getDiscount() > highest.getDiscount()) {
                highest = coupon;
            }
        }
        return highest;
    }

    // View all coupons
    public static void viewCoupons() {
        if (couponList.isEmpty()) {
            System.out.println("No coupons available.");
            return;
        }
        for (Coupon coupon : couponList) {
            System.out.println(coupon);
        }
    }
}
