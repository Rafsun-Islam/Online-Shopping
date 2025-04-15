import java.util.ArrayList;

public class WishList {
    private int customerId;
    private ArrayList<Product> products;

    // Constructor
    public WishList(int customerId) {
        this.customerId = customerId;
        this.products = new ArrayList<>();
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    // Add a product to the wish list
    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            System.out.println("Product added to the wish list: " + product);
        } else {
            System.out.println("Product is already in the wish list: " + product);
        }
    }

    // Remove a product from the wish list
    public boolean removeProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                products.remove(product);
                System.out.println("Product removed from the wish list: " + product);
                return true;
            }
        }
        System.out.println("Product with ID " + productId + " not found in the wish list.");
        return false;
    }

    // Check if a product is in the wish list
    public boolean containsProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return true;
            }
        }
        return false;
    }

    // View all products in the wish list
    public void viewWishList() {
        if (products.isEmpty()) {
            System.out.println("Wish list is empty.");
            return;
        }
        System.out.println("Wish List for Customer ID: " + customerId);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
