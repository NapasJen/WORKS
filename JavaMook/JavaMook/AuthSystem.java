import java.util.ArrayList;

class AuthSystem {
    private ArrayList<User> users;
    private User loggedInUser = null;

    // Constructor
    public AuthSystem() {
        users = new ArrayList<>();
    }

    // Sign up a new user
    public void signUp(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }
        users.add(new User(username, password));
        System.out.println("User signed up successfully!");
    }

    // Log in a user
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    // Log out user
    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out.");
    }

    // Check if user is logged in
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Get logged in user
    public User getLoggedInUser() {
        return loggedInUser;
    }
}