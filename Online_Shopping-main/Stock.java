public class Stock {
    private int productId;
    private int quantity;
    private int lowStockThreshold;

    // Constructor
    public Stock(int productId, int quantity, int lowStockThreshold) {
        if (quantity < 0 || lowStockThreshold < 0) {
            throw new IllegalArgumentException("Quantity and threshold cannot be negative.");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.lowStockThreshold = lowStockThreshold;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
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

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        if (lowStockThreshold < 0) {
            throw new IllegalArgumentException("Threshold cannot be negative.");
        }
        this.lowStockThreshold = lowStockThreshold;
    }

    // Add stock
    public void addStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity to add cannot be negative.");
        }
        this.quantity += quantity;
    }

    // Remove stock
    public void removeStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity to remove cannot be negative.");
        }
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("Insufficient stock.");
        }
        this.quantity -= quantity;
    }

    // Check if stock is low
    public boolean isLowStock() {
        return this.quantity <= this.lowStockThreshold;
    }

    @Override
    public String toString() {
        return String.format("Stock [Product ID=%d, Quantity=%d, Low Stock Threshold=%d]",
                             productId, quantity, lowStockThreshold);
    }
}
