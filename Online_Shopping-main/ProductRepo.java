import java.util.ArrayList;

public class ProductRepo {
    private static ArrayList<Product> productList = new ArrayList<>();

    // Getter for the product list
    public static ArrayList<Product> getProductList() {
        return productList;
    }

    // Add a product
    public static void addProduct(Product product) {
        productList.add(product);
        System.out.println("Product added successfully: " + product);
    }

    // Find a product by ID
    public static Product findProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // Return null if not found
    }

    // Find products by name (case-insensitive)
    public static ArrayList<Product> findProductsByName(String name) {
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Find products by category
    public static ArrayList<Product> findProductsByCategory(String category) {
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // Find products in a price range
    public static ArrayList<Product> findProductsByPriceRange(double minPrice, double maxPrice) {
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    // View all low-stock products (stock below threshold)
    public static void viewLowStockProducts(int threshold) {
        for (Product product : productList) {
            if (product.getStock() < threshold) {
                System.out.println(product);
            }
        }
    }

    // Remove a product by ID
    public static boolean removeProductById(int id) {
        Product product = findProductById(id);
        if (product != null) {
            productList.remove(product);
            System.out.println("Product removed successfully: " + product);
            return true;
        }
        System.out.println("Product with ID " + id + " not found.");
        return false;
    }

    // View all products
    public static void viewAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}
