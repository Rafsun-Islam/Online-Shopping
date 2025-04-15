public class BillItem {
    private int productId;
    private String productName;
    private double price;
    private int quantity;

    // Constructor
    public BillItem(int productId, String productName, double price, int quantity) {
        if (price < 0 || quantity < 0) {
            throw new IllegalArgumentException("Price and quantity cannot be negative.");
        }
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    // Calculate the total cost for this bill item
    public double getTotalCost() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("BillItem [Product ID=%d, Name=%s, Price=%.2f, Quantity=%d, Total Cost=%.2f]",
                             productId, productName, price, quantity, getTotalCost());
    }
}
