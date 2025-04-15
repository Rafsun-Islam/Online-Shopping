import java.util.ArrayList;

public class WishlistRepo {
    private static ArrayList<WishList> wishLists = new ArrayList<>();

    // Get all wish lists
    public static ArrayList<WishList> getWishLists() {
        return wishLists;
    }

    // Add a wish list
    public static void addWishList(WishList wishList) {
        wishLists.add(wishList);
        System.out.println("Wish list added successfully for Customer ID: " + wishList.getCustomerId());
    }

    // Find a wish list by customer ID
    public static WishList findWishListByCustomerId(int customerId) {
        for (WishList wishList : wishLists) {
            if (wishList.getCustomerId() == customerId) {
                return wishList;
            }
        }
        return null; // Return null if not found
    }

    // Remove a wish list by customer ID
    public static boolean removeWishListByCustomerId(int customerId) {
        WishList wishList = findWishListByCustomerId(customerId);
        if (wishList != null) {
            wishLists.remove(wishList);
            System.out.println("Wish list removed successfully for Customer ID: " + customerId);
            return true;
        }
        System.out.println("Wish list for Customer ID " + customerId + " not found.");
        return false;
    }

    // View all wish lists
    public static void viewAllWishLists() {
        if (wishLists.isEmpty()) {
            System.out.println("No wish lists available.");
            return;
        }
        for (WishList wishList : wishLists) {
            wishList.viewWishList();
        }
    }
}
