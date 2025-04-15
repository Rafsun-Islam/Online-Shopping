import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminTest {

    private Admin admin;

    @Before
    public void setUp() {
      
        admin = new Admin("Rafsun", "password123");
    }

    @Test
    public void testGetId() {
        int id = admin.getId();
        assertTrue(id >= 5); 
    }

    @Test
    public void testGetName() {
        assertEquals("Rafsun", admin.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", admin.getPassword());
    }

  
    @Test
    public void testSetId() {
        admin.setId(10);
        assertEquals(10, admin.getId());
    }

    @Test
    public void testSetName() {
        admin.setName("Taskin");
        assertEquals("Taskin", admin.getName());
    }

    @Test
    public void testSetPassword() {
        admin.setPassword("newPassword123");
        assertEquals("newPassword123", admin.getPassword());
    }

  
    @Test
    public void testUpdateCredentials() {
        admin.updateCredentials("Shawon", "securePassword123");
        assertEquals("Shawon", admin.getName());
        assertEquals("securePassword123", admin.getPassword());
    }

    @Test
    public void testIsValidPassword() {
        assertTrue(admin.isValidPassword("password123"));
        assertFalse(admin.isValidPassword("wrongPassword"));
    }

    @Test
    public void testResetPassword() {
        admin.resetPassword("short");
        assertNotEquals("short", admin.getPassword());

        admin.resetPassword("newSecurePassword");
        assertEquals("newSecurePassword", admin.getPassword());
    }

    @Test
    public void testEquals() {
        Admin anotherAdmin = new Admin("AnotherAdmin", "anotherPassword");
        assertFalse(admin.equals(anotherAdmin));

        anotherAdmin.setId(admin.getId());
        assertTrue(admin.equals(anotherAdmin));
    }

    @Test
    public void testDisplayAdminDetails() {
        admin.displayAdminDetails();
      
    }

    @Test
    public void testToString() {
        String expectedString = "Admin [id=" + admin.getId() + ", name=Rafsun, password=password123]";
        assertEquals(expectedString, admin.toString());
    }
}
