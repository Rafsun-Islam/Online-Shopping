import java.util.ArrayList;

public class CustomerRepo {
    private static ArrayList<Customer> customerList = new ArrayList<>();

    // Getter for the customer list
    public static ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    // Add a customer
    public static void addCustomer(Customer customer) {
        customerList.add(customer);
        System.out.println("Customer added successfully: " + customer);
    }

    // Find a customer by ID
    public static Customer findCustomerById(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null; // Return null if not found
    }

    // Find a customer by email
    public static Customer findCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        return null; // Return null if not found
    }

    // Remove a customer by ID
    public static boolean removeCustomerById(int id) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customerList.remove(customer);
            System.out.println("Customer removed successfully: " + customer);
            return true;
        }
        System.out.println("Customer with ID " + id + " not found.");
        return false;
    }

    // Update customer details
    public static boolean updateCustomerDetails(int id, String name, String email, String phone, String address) {
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setAddress(address);
            System.out.println("Customer updated successfully: " + customer);
            return true;
        }
        System.out.println("Customer with ID " + id + " not found.");
        return false;
    }

    // Get all customers eligible for discounts
    public static ArrayList<Customer> getDiscountEligibleCustomers() {
        ArrayList<Customer> eligibleCustomers = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.isEligibleForDiscount()) {
                eligibleCustomers.add(customer);
            }
        }
        return eligibleCustomers;
    }

    // View all customers
    public static void viewCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }
}
