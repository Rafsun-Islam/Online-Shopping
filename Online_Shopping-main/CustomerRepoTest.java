import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CustomerRepoTest {

    @Before
    public void setUp() {
       
        CustomerRepo.getCustomerList().clear();
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        Assert.assertEquals(1, CustomerRepo.getCustomerList().size());
        Assert.assertEquals(customer, CustomerRepo.getCustomerList().get(0));
    }

    @Test
    public void testFindCustomerByIdFound() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        Customer foundCustomer = CustomerRepo.findCustomerById(customer.getId());
        Assert.assertNotNull(foundCustomer);
        Assert.assertEquals(customer, foundCustomer);
    }

    @Test
    public void testFindCustomerByIdNotFound() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        Customer foundCustomer = CustomerRepo.findCustomerById(999); 
        Assert.assertNull(foundCustomer);
    }

    @Test
    public void testFindCustomerByEmailFound() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        Customer foundCustomer = CustomerRepo.findCustomerByEmail("Shawon@gmail.com");
        Assert.assertNotNull(foundCustomer);
        Assert.assertEquals(customer, foundCustomer);
    }

    @Test
    public void testFindCustomerByEmailNotFound() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        Customer foundCustomer = CustomerRepo.findCustomerByEmail("nonexistent@example.com");
        Assert.assertNull(foundCustomer);
    }

    @Test
    public void testRemoveCustomerByIdSuccess() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        boolean removed = CustomerRepo.removeCustomerById(customer.getId());
        Assert.assertTrue(removed);
        Assert.assertTrue(CustomerRepo.getCustomerList().isEmpty());
    }

    @Test
    public void testRemoveCustomerByIdNotFound() {
        boolean removed = CustomerRepo.removeCustomerById(999); 
        Assert.assertFalse(removed);
    }

    @Test
    public void testUpdateCustomerDetailsSuccess() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        boolean updated = CustomerRepo.updateCustomerDetails(customer.getId(), "Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Assert.assertTrue(updated);

        Customer updatedCustomer = CustomerRepo.findCustomerById(customer.getId());
        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals("Shawon", updatedCustomer.getName());
        Assert.assertEquals("Shawon@gmail.com", updatedCustomer.getEmail());
        Assert.assertEquals("1234567890", updatedCustomer.getPhone());
        Assert.assertEquals("Rampura", updatedCustomer.getAddress());
    }

    @Test
    public void testUpdateCustomerDetailsNotFound() {
        boolean updated = CustomerRepo.updateCustomerDetails(999, "Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Assert.assertFalse(updated);
    }

    @Test
    public void testGetDiscountEligibleCustomers() {
        Customer customer1 = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Customer customer2 = new Customer("Rafsan", "Rafsan@gmail.com", "0987654321", "Aftabnagar");

        Order order1 = new Order(customer1.getId());
        order1.addItem(new BillItem(1, "Laptop", 1500.0, 1));
        customer1.addOrder(order1);

        Order order2 = new Order(customer2.getId());
        order2.addItem(new BillItem(2, "Monitor", 500.0, 1));
        customer2.addOrder(order2);

        CustomerRepo.addCustomer(customer1);
        CustomerRepo.addCustomer(customer2);

        ArrayList<Customer> eligibleCustomers = CustomerRepo.getDiscountEligibleCustomers();
        Assert.assertEquals(1, eligibleCustomers.size());
        Assert.assertTrue(eligibleCustomers.contains(customer1));
        Assert.assertFalse(eligibleCustomers.contains(customer2));
    }

    @Test
    public void testViewCustomers() {
    	Customer customer1 = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        Customer customer2 = new Customer("Rafsan", "Rafsan@gmail.com", "0987654321", "Aftabnagar");

        CustomerRepo.addCustomer(customer1);
        CustomerRepo.addCustomer(customer2);

        CustomerRepo.viewCustomers(); 
        Assert.assertEquals(2, CustomerRepo.getCustomerList().size());
    }

    @Test
    public void testToStringWithDynamicId() {
        Customer customer = new Customer("Shawon", "Shawon@gmail.com", "1234567890", "Rampura");
        CustomerRepo.addCustomer(customer);

        String actual = customer.toString();
        String expectedPrefix = String.format("Customer [ID=%d, Name=Shawon, Email=Shawon@gmail.com, Phone=1234567890, Address=Rampura, Total Spending=", customer.getId());
        Assert.assertTrue(actual.startsWith(expectedPrefix));
        Assert.assertTrue(actual.endsWith("0.00]"));
    }
}
