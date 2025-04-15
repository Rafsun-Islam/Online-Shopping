public class Coupon {
    private int code;
    private float discount;

    // Constructor
    public Coupon(int code, float discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100.");
        }
        this.code = code;
        this.discount = discount;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100.");
        }
        this.discount = discount;
    }

    // Check if the coupon is valid (discount > 0)
    public boolean isValidCoupon() {
        return discount > 0;
    }

    // Apply discount to a given amount
    public double applyDiscount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return amount - (amount * discount / 100);
    }

    @Override
    public String toString() {
        return String.format("Coupon [Code=%d, Discount=%.2f%%]", code, discount);
    }
}
