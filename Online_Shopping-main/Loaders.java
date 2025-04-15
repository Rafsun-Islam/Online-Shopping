import java.util.ArrayList;

public class Loaders {

    public void loadCustomer() {
        // Creating and adding customers
        Customer c1 = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Address 1");
        Customer c2 = new Customer("Rafsan", "Rafsan@gmail.com", "0987654321", "Address 2");
        Customer c3 = new Customer("Joha", "Joha@gmail.com", "1122334455", "Address 3");

        CustomerRepo.addCustomer(c1);
        CustomerRepo.addCustomer(c2);
        CustomerRepo.addCustomer(c3);
    }

    public void loadProduct() {
        // Creating and adding products
        Product p1 = new Product("Shirt", 50, "Clothing", 10);
        Product p2 = new Product("Pant", 100, "Clothing", 10);
        Product p3 = new Product("Trouser", 75, "Clothing", 10);
        Product p4 = new Product("Saree", 200, "Clothing", 10);

        ProductRepo.addProduct(p1);
        ProductRepo.addProduct(p2);
        ProductRepo.addProduct(p3);
        ProductRepo.addProduct(p4);

        // Adding stocks for the products
        StockRepo.addStock(new Stock(p1.getId(), 10, 5));
        StockRepo.addStock(new Stock(p2.getId(), 10, 5));
        StockRepo.addStock(new Stock(p3.getId(), 10, 5));
        StockRepo.addStock(new Stock(p4.getId(), 10, 5));
    }

    public void loadWishlist() {
        // Retrieve products from ProductRepo
        ArrayList<Product> products = ProductRepo.getProductList();
        if (products.size() < 4) {
            throw new IllegalStateException("Not enough products in ProductRepo to create wishlists");
        }

 
        WishList wl1 = new WishList(1); // For Customer ID 1
        wl1.addProduct(products.get(0)); // "Shirt"
        wl1.addProduct(products.get(1)); // "Pant"

        WishList wl2 = new WishList(2); // For Customer ID 2
        wl2.addProduct(products.get(2)); // "Trouser"
        wl2.addProduct(products.get(3)); // "Saree"

        WishlistRepo.addWishList(wl1);
        WishlistRepo.addWishList(wl2);
    }

    public void loadCoupon() {
        // Creating and adding coupons
        Coupon c1 = new Coupon(999, 10);
        Coupon c2 = new Coupon(888, 20);
        Coupon c3 = new Coupon(777, 50);

        CouponRepo.addCoupon(c1);
        CouponRepo.addCoupon(c2);
        CouponRepo.addCoupon(c3);
    }
}
