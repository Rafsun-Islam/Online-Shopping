public class Product {
    private int id;
    private static int idCounter = 1; // Auto-incrementing ID
    private String name;
    private double price;
    private String category;
    private int stock;

    // Constructor
    public Product(String name, double price, String category, int stock) {
        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException("Price and stock cannot be negative.");
        }
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        this.stock = stock;
    }

    // Update stock
    public void updateStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.stock += quantity;
    }

    // Apply discount to the price
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        this.price -= (price * discountPercentage / 100);
    }

    @Override
    public String toString() {
        return String.format("Product [ID=%d, Name=%s, Price=%.2f, Category=%s, Stock=%d]",
                             id, name, price, category, stock);
    }
}
