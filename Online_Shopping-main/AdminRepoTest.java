import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AdminRepoTest {

    private AdminRepo adminRepo;
    private Admin admin1;
    private Admin admin2;

    @Before
    public void setUp() {
        adminRepo = new AdminRepo();
        admin1 = new Admin("Rafasn", "password123");
        admin2 = new Admin("Shawon", "securePass");

        adminRepo.addAdmin(admin1);
        adminRepo.addAdmin(admin2);
    }

    @Test
    public void testGetAdminList() {
        ArrayList<Admin> adminList = adminRepo.getAdminList();
        assertEquals(2, adminList.size());
        assertTrue(adminList.contains(admin1));
        assertTrue(adminList.contains(admin2));
    }

    @Test
    public void testSetAdminList() {
        ArrayList<Admin> newAdminList = new ArrayList<>();
        Admin admin3 = new Admin("Joha", "newPassword");
        newAdminList.add(admin3);

        adminRepo.setAdminList(newAdminList);
        assertEquals(1, adminRepo.getAdminList().size());
        assertTrue(adminRepo.getAdminList().contains(admin3));
    }

    @Test
    public void testViewAdmins() {
        adminRepo.viewAdmins();
       
    }

    @Test
    public void testAddAdmin() {
        Admin admin3 = new Admin("Joha", "newPassword");
        adminRepo.addAdmin(admin3);
        assertTrue(adminRepo.getAdminList().contains(admin3));
        assertEquals(3, adminRepo.getAdminList().size());
    }

    @Test
    public void testRemoveAdminById() {
        boolean removed = adminRepo.removeAdminById(admin1.getId());
        assertTrue(removed);
        assertFalse(adminRepo.getAdminList().contains(admin1));
        assertEquals(1, adminRepo.getAdminList().size());

        removed = adminRepo.removeAdminById(999); 
        assertFalse(removed);
    }

    @Test
    public void testFindAdminById() {
        Admin foundAdmin = adminRepo.findAdminById(admin2.getId());
        assertNotNull(foundAdmin);
        assertEquals(admin2.getId(), foundAdmin.getId());

        Admin notFoundAdmin = adminRepo.findAdminById(999); 
        assertNull(notFoundAdmin);
    }

    @Test
    public void testAdminExistsByName() {
        assertTrue(adminRepo.adminExistsByName("Shawon"));
        assertTrue(adminRepo.adminExistsByName("Shawon"));
        assertFalse(adminRepo.adminExistsByName("NonExistingAdmin"));
    }
}
