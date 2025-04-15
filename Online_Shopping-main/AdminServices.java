import java.util.Scanner;

public class AdminServices {
    static Scanner sc = new Scanner(System.in);

 
    public void addProduct() {
        try {
            System.out.print("Enter Product Name: ");
            String name = sc.nextLine().trim(); 

            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            sc.nextLine(); 

            System.out.print("Enter Category: ");
            String category = sc.nextLine().trim(); 

            System.out.print("Enter Initial Stock: ");
            int stock = sc.nextInt(); 
            sc.nextLine(); 

         
            if (price < 0 || stock < 0) {
                System.out.println("Error: Price and stock cannot be negative.");
                return;
            }

      
            Product product = new Product(name, price, category, stock);
            ProductRepo.addProduct(product);
            System.out.println("Product added successfully: " + product);
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            sc.nextLine(); 
        }
    }


    public void viewProducts() {
        ProductRepo.viewAllProducts();
    }

 
    public void viewLowStockProducts() {
        System.out.print("Enter Low Stock Threshold: ");
        int threshold = sc.nextInt();
        if (threshold < 0) {
            System.out.println("Error: Threshold cannot be negative.");
            return;
        }
        ProductRepo.viewLowStockProducts(threshold);
    }

 
    public void findProductsByCategory() {
        System.out.print("Enter Category: ");
        String category = sc.nextLine().trim(); 

      
        var products = ProductRepo.findProductsByCategory(category);
        if (products.isEmpty()) {
            System.out.println("No products found in category: " + category);
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }


    public void findProductsByPriceRange() {
        System.out.print("Enter Minimum Price: ");
        double minPrice = sc.nextDouble();
        System.out.print("Enter Maximum Price: ");
        double maxPrice = sc.nextDouble();

        if (minPrice < 0 || maxPrice < 0) {
            System.out.println("Error: Prices cannot be negative.");
            return;
        }

        var products = ProductRepo.findProductsByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            System.out.println("No products found in the specified price range.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }


    public void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();

        Product product = ProductRepo.findProductById(id);
        if (product == null) {
            System.out.println("Error: Product not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        sc.nextLine(); // Consume newline
        String newName = sc.nextLine();
        System.out.print("Enter New Price: ");
        double newPrice = sc.nextDouble();
        System.out.print("Enter New Category: ");
        sc.nextLine(); // Consume newline
        String newCategory = sc.nextLine();

        if (newPrice < 0) {
            System.out.println("Error: Price cannot be negative.");
            return;
        }

        product.setName(newName);
        product.setPrice(newPrice);
        product.setCategory(newCategory);
        System.out.println("Product updated successfully: " + product);
    }

    // Remove a product
    public void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        int id = sc.nextInt();
        boolean removed = ProductRepo.removeProductById(id);
        if (removed) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Error: Product not found.");
        }
    }

  
    public void generateCouponCode() {
        System.out.print("Enter Coupon Code: ");
        int code = sc.nextInt();
        System.out.print("Enter Discount Percentage: ");
        float discount = sc.nextFloat();

        if (discount < 0 || discount > 100) {
            System.out.println("Error: Discount percentage must be between 0 and 100.");
            return;
        }

        Coupon coupon = new Coupon(code, discount);
        CouponRepo.addCoupon(coupon);
        System.out.println("Coupon generated successfully: " + coupon);
    }

 
    public void viewCoupons() {
        CouponRepo.viewCoupons();
    }

 
    public void manageStocks() {
        boolean flag = true;
        while (flag) {
            System.out.println("""
                1. Add Stock
                2. View Stock
                3. Remove Stock
                4. View Low Stock Items
                5. Exit
                """);
            int input = sc.nextInt();
            switch (input) {
                case 1 -> addStock();
                case 2 -> viewStocks();
                case 3 -> removeStock();
                case 4 -> viewLowStockItems();
                case 5 -> flag = false;
                default -> System.out.println("Invalid Input. Please try again.");
            }
        }
    }
    
    
    public void addStock() {
        try {
            System.out.print("Enter Product ID: ");
            int productId = sc.nextInt(); // Read the product ID

            // Check if the product ID exists in ProductRepo
            Product product = ProductRepo.findProductById(productId);
            if (product == null) {
                System.out.println("Error: Product ID not found. Please check and try again.");
                return;
            }

            System.out.print("Enter Quantity to Add: ");
            int quantity = sc.nextInt(); // Read the quantity

            if (quantity < 0) {
                System.out.println("Error: Quantity cannot be negative.");
                return;
            }

          
            Stock stock = StockRepo.findStockByProductId(productId);
            if (stock == null) {
                System.out.print("Enter Low Stock Threshold: ");
                int lowStockThreshold = sc.nextInt(); 
                if (lowStockThreshold < 0) {
                    System.out.println("Error: Threshold cannot be negative.");
                    return;
                }

    
                int totalQuantity = product.getStock() + quantity;
                stock = new Stock(productId, totalQuantity, lowStockThreshold);
                StockRepo.addStock(stock);
                System.out.println("New Stock Added: " + stock);
            } else {
                // Update existing stock
                stock.addStock(quantity);
                System.out.println("Updated Stock: " + stock);
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            sc.nextLine(); 
    }
    }




    // View stocks
    public void viewStocks() {
        StockRepo.viewAllStock();
    }

    // Remove stock
    public void removeStock() {
        System.out.print("Enter Product ID: ");
        int productId = sc.nextInt();
        boolean removed = StockRepo.removeStockByProductId(productId);
        if (removed) {
            System.out.println("Stock removed successfully.");
        } else {
            System.out.println("Error: Stock not found.");
        }
    }

    // View low stock items
    public void viewLowStockItems() {
        StockRepo.viewLowStockItems();
    }

    // Shopping history placeholder (requires OrderRepo)
    public void shoppingHistory() {
        System.out.println("Shopping history feature is under development.");
    }
}
