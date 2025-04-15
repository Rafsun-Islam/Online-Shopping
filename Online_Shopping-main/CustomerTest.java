import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void testConstructorValidInputs() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Assert.assertEquals("Shawon", customer.getName());
        Assert.assertEquals("Shawon@gmail.com", customer.getEmail());
        Assert.assertEquals("1234567890", customer.getPhone());
        Assert.assertEquals("Rampura", customer.getAddress());
        Assert.assertTrue(customer.getPurchaseHistory().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidName() {
        new Customer("", "Shawon@gmail.com", "1234567890", "Rampura");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidEmail() {
        new Customer("Shawon", "invalid-email", "1234567890", "Rampura");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidPhone() {
        new Customer("Shawon", "Shawon@gmail.com", "12345", "Rampura");
    }

    @Test
    public void testSetNameValid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setName("Rafsan");
        Assert.assertEquals("Rafsan", customer.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameInvalid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setName("");
    }

    @Test
    public void testSetEmailValid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setEmail("Rafsan@gmail.com");
        Assert.assertEquals("Rafsan@gmail.com", customer.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmailInvalid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setEmail("invalid-email");
    }

    @Test
    public void testSetPhoneValid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setPhone("0987654321");
        Assert.assertEquals("0987654321", customer.getPhone());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPhoneInvalid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setPhone("12345");
    }

    @Test
    public void testSetPasswordValid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setPassword("secure123");
        Assert.assertEquals("secure123", customer.getPassword());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPasswordInvalid() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        customer.setPassword("123");
    }

    @Test
    public void testAddOrder() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Order order = new Order(customer.getId());
        customer.addOrder(order);
        Assert.assertEquals(1, customer.getPurchaseHistory().size());
        Assert.assertEquals(order, customer.getPurchaseHistory().get(0));
    }

    @Test
    public void testGetTotalSpending() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Order order1 = new Order(customer.getId());
        Order order2 = new Order(customer.getId());

        order1.addItem(new BillItem(1, "Laptop", 500.0, 1));
        order2.addItem(new BillItem(2, "Monitor", 300.0, 2));

        customer.addOrder(order1);
        customer.addOrder(order2);

        Assert.assertEquals(1100.0, customer.getTotalSpending(), 0.01);
    }

    @Test
    public void testIsEligibleForDiscountTrue() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Order order = new Order(customer.getId());
        order.addItem(new BillItem(1, "Laptop", 1500.0, 1));
        customer.addOrder(order);
        Assert.assertTrue(customer.isEligibleForDiscount());
    }

    @Test
    public void testIsEligibleForDiscountFalse() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Order order = new Order(customer.getId());
        order.addItem(new BillItem(1, "Laptop", 500.0, 1));
        customer.addOrder(order);
        Assert.assertFalse(customer.isEligibleForDiscount());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Order order = new Order(customer.getId());
        order.addItem(new BillItem(1, "Laptop", 500.0, 1));
        customer.addOrder(order);

        int actualId = customer.getId();
        String expected = String.format("Customer [ID=%d, Name=Shawon, Email=Shawon@gmail.com, Phone=1234567890, Address=Rampura, Total Spending=500.00]", actualId);
        Assert.assertEquals(expected, customer.toString());
    }
}
