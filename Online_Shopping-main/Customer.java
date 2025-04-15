import java.util.ArrayList;

public class Customer {
    private int id;
    private static int idCounter = 1; // Auto-incrementing ID
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password; // Password field
    private ArrayList<Order> purchaseHistory;

    // Constructor
    public Customer(String name, String email, String phone, String address) {
        this.id = idCounter++;
        setName(name);
        setEmail(email);
        setPhone(phone);
        this.address = address;
        this.purchaseHistory = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!phone.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Phone number must be 10 digits.");
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        this.password = password;
    }

    public ArrayList<Order> getPurchaseHistory() {
        return purchaseHistory;
    }

    // Add an order to purchase history
    public void addOrder(Order order) {
        this.purchaseHistory.add(order);
    }

    // Get total spending
    public double getTotalSpending() {
        double total = 0.0;
        for (Order order : purchaseHistory) {
            total += order.getTotalAmount();
        }
        return total;
    }

    // Check if the customer is eligible for a discount
    public boolean isEligibleForDiscount() {
        return getTotalSpending() > 1000; // Example threshold for eligibility
    }

    @Override
    public String toString() {
        return String.format("Customer [ID=%d, Name=%s, Email=%s, Phone=%s, Address=%s, Total Spending=%.2f]",
                             id, name, email, phone, address, getTotalSpending());
    }
}
