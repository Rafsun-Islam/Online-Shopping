public class Admin {
    private int id;
    private static int counter = 5;
    private String name;
    private String password;

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
        this.id = counter++;
    }

  
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

  
    public void updateCredentials(String newName, String newPassword) {
        this.name = newName;
        this.password = newPassword;
        System.out.println("Credentials updated successfully.");
    }

   
    public boolean isValidPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

   
    public void resetPassword(String newPassword) {
        if (newPassword.length() >= 8) {
            this.password = newPassword;
            System.out.println("Password reset successfully.");
        } else {
            System.out.println("Password must be at least 8 characters long.");
        }
    }


    public boolean equals(Admin otherAdmin) {
        return this.id == otherAdmin.id;
    }

  
    public void displayAdminDetails() {
        System.out.println("Admin Details: ");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", name=" + name + ", password=" + password + "]";
    }
}
