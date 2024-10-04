import java.util.ArrayList;


class AdminData {
    private ArrayList<Admin> adminList;


    public AdminData() {
        adminList = new ArrayList<>();
        addAdmin("admin1", "admin123");
        addAdmin("admin2", "admin123");
        addAdmin("admin3", "admin123");
        addAdmin("admin4", "admin123");
        addAdmin("admin5", "admin123");
        addAdmin("admin6", "admin123");
    }


    public void addAdmin(String username, String password) {
        Admin newAdmin = new Admin(username, password);
        adminList.add(newAdmin);
    }


    public boolean authenticate(String username, String password) {
        for (Admin admin : adminList) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

 
    public ArrayList<Admin> getAdminList() {
        return adminList;
    }
}

