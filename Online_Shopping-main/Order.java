import java.util.ArrayList;

public class Order {
    private int orderId;
    private static int idCounter = 1; // Auto-incrementing ID
    private int customerId;
    private ArrayList<BillItem> items;
    private double totalAmount;

    // Constructor
    public Order(int customerId) {
        this.orderId = idCounter++;
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<BillItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Setters
    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative.");
        }
        this.totalAmount = totalAmount;
    }

    // Add an item to the order
    public void addItem(BillItem item) {
        items.add(item);
        recalculateTotal();
    }

    // Remove an item from the order by product ID
    public boolean removeItem(int productId) {
        for (BillItem item : items) {
            if (item.getProductId() == productId) {
                items.remove(item);
                recalculateTotal();
                return true;
            }
        }
        return false; // Item not found
    }

    // Update an item in the order
    public boolean updateBillItem(int productId, int newQuantity) {
        for (BillItem item : items) {
            if (item.getProductId() == productId) {
                item.setQuantity(newQuantity);
                recalculateTotal();
                return true;
            }
        }
        return false; // Item not found
    }

    // Recalculate the total amount of the order
    private void recalculateTotal() {
        totalAmount = 0.0;
        for (BillItem item : items) {
            totalAmount += item.getTotalCost();
        }
    }

    // Display order summary
    public String getOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Order ID: ").append(orderId).append("\n")
               .append("Customer ID: ").append(customerId).append("\n")
               .append("Items:\n");
        for (BillItem item : items) {
            summary.append(item).append("\n");
        }
        summary.append("Total Amount: ").append(totalAmount);
        return summary.toString();
    }
}
