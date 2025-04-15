import java.util.ArrayList;

public class StockRepo {
    private static ArrayList<Stock> stockList = new ArrayList<>();

    // Getter for the stock list
    public static ArrayList<Stock> getStockList() {
        return stockList;
    }

    // Add a stock entry
    public static void addStock(Stock stock) {
        stockList.add(stock);
        System.out.println("Stock added successfully: " + stock);
    }

    // Find stock by product ID
    public static Stock findStockByProductId(int productId) {
        for (Stock stock : stockList) {
            if (stock.getProductId() == productId) {
                return stock;
            }
        }
        return null; // Return null if not found
    }

    // Update stock quantity for a product
    public static boolean updateStockQuantity(int productId, int quantity) {
        Stock stock = findStockByProductId(productId);
        if (stock != null) {
            stock.setQuantity(quantity);
            System.out.println("Stock updated successfully: " + stock);
            return true;
        }
        System.out.println("Stock for Product ID " + productId + " not found.");
        return false;
    }

    // View all low-stock items
    public static void viewLowStockItems() {
        System.out.println("Low Stock Items:");
        for (Stock stock : stockList) {
            if (stock.isLowStock()) {
                System.out.println(stock);
            }
        }
    }

    // Remove stock by product ID
    public static boolean removeStockByProductId(int productId) {
        Stock stock = findStockByProductId(productId);
        if (stock != null) {
            stockList.remove(stock);
            System.out.println("Stock removed successfully: " + stock);
            return true;
        }
        System.out.println("Stock for Product ID " + productId + " not found.");
        return false;
    }

    // View all stock entries
    public static void viewAllStock() {
        if (stockList.isEmpty()) {
            System.out.println("No stock records available.");
            return;
        }
        for (Stock stock : stockList) {
            System.out.println(stock);
        }
    }
}
