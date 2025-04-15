import java.util.ArrayList;

public class AdminRepo {

    private ArrayList<Admin> adminList = new ArrayList<>();


    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

  
    public void viewAdmins() {
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
    }

    
    public void addAdmin(Admin admin) {
        adminList.add(admin);
        System.out.println("Admin added successfully: " + admin);
    }

 
    public boolean removeAdminById(int id) {
        for (Admin admin : adminList) {
            if (admin.getId() == id) {
                adminList.remove(admin);
                System.out.println("Admin removed successfully: " + admin);
                return true;
            }
        }
        System.out.println("Admin with ID " + id + " not found.");
        return false;
    }

  
    public Admin findAdminById(int id) {
        for (Admin admin : adminList) {
            if (admin.getId() == id) {
                return admin;
            }
        }
        return null; 
    }


    public boolean adminExistsByName(String name) {
        for (Admin admin : adminList) {
            if (admin.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
