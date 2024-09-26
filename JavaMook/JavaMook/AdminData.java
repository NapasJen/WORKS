import java.util.ArrayList;

// AdminData class to store admin usernames and passwords
class AdminData {
    private ArrayList<Admin> adminList;

    // Constructor
    public AdminData() {
        adminList = new ArrayList<>();
        // Default admin credentials (you can add more if needed)
        addAdmin("admin1", "admin123");
        addAdmin("admin2", "admin123");
        addAdmin("admin3", "admin123");
        addAdmin("admin4", "admin123");
        addAdmin("admin5", "admin123");
        addAdmin("admin6", "admin123");
    }

    // Add a new admin
    public void addAdmin(String username, String password) {
        Admin newAdmin = new Admin(username, password);
        adminList.add(newAdmin);
    }

    // Validate admin login
    public boolean authenticate(String username, String password) {
        for (Admin admin : adminList) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Get admin list
    public ArrayList<Admin> getAdminList() {
        return adminList;
    }
}

