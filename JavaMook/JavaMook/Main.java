import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AdminData adminData = new AdminData();  // Manages admin credentials
        AuthSystem authSystem = new AuthSystem();  // Manages user authentication
        Cart cart = new Cart();  // User's shopping cart
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // User signup
                    System.out.print("Enter username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    authSystem.signUp(newUsername, newPassword);
                    break;

                case 2:
                    // User login
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (authSystem.login(username, password)) {
                        // User menu after login
                        boolean userLoggedIn = true;
                        while (userLoggedIn) {
                            System.out.println("1. Browse Robots");
                            System.out.println("2. Add Robot to Cart");
                            System.out.println("3. View Cart");
                            System.out.println("4. Remove Robot from Cart");
                            System.out.println("5. Update Robot Quantity in Cart");
                            System.out.println("6. Checkout");
                            System.out.println("7. Logout");
                            System.out.print("Select an option: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            switch (userChoice) {
                                case 1:
                                    // Browse robots (from all admins' inventories)
                                    for (Admin admin : adminData.getAdminList()) {
                                        admin.showInventory();
                                    }
                                    break;
                                case 2:
                                    // Add robot to cart with quantity
                                    System.out.print("Enter robot model to add to cart: ");
                                    String cartModel = scanner.nextLine();
                                    RobotProduct selectedRobot = null;

                                    // Find robot in the inventory
                                    for (Admin admin : adminData.getAdminList()) {
                                        for (RobotProduct robot : admin.getRobotInventory()) {
                                            if (robot.getModel().equalsIgnoreCase(cartModel)) {
                                                selectedRobot = robot;
                                                break;
                                            }
                                        }
                                    }

                                    if (selectedRobot != null) {
                                        System.out.print("Enter quantity to add to cart: ");
                                        int quantity = scanner.nextInt();

                                        // Add to cart and check stock
                                        cart.addRobotToCart(selectedRobot, quantity);
                                    } else {
                                        System.out.println("Robot model not found.");
                                    }
                                    break;
                                case 3:
                                    // View cart
                                    cart.showCart();
                                    break;
                                case 4:
                                    // Remove robot from cart
                                    System.out.print("Enter robot model to remove from cart: ");
                                    String removeModel = scanner.nextLine();
                                    cart.removeRobotFromCart(removeModel);
                                    break;
                                case 5:
                                    // Update robot quantity in cart
                                    System.out.print("Enter robot model to update quantity: ");
                                    String updateModel = scanner.nextLine();
                                    System.out.print("Enter new quantity: ");
                                    int newQuantity = scanner.nextInt();
                                    cart.updateRobotQuantity(updateModel, newQuantity);
                                    break;
                                case 6:
                                    // Checkout
                                    cart.checkout();
                                    break;
                                case 7:
                                    // Logout
                                    authSystem.logout();
                                    userLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        }
                    }
                    break;

                case 3:
                    // Admin login
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();
                    if (adminData.authenticate(adminUsername, adminPassword)) {
                        // Admin menu
                        boolean adminLoggedIn = true;
                        while (adminLoggedIn) {
                            System.out.println("Admin Interface:");
                            System.out.println("1. Add Robot");
                            System.out.println("2. Update Robot Stock");
                            System.out.println("3. Remove Robot");
                            System.out.println("4. View Inventory");
                            System.out.println("5. Logout");
                            System.out.print("Select an option: ");
                            int adminChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            switch (adminChoice) {
                                case 1:
                                    // Add robot
                                    System.out.print("Enter robot model: ");
                                    String model = scanner.nextLine();
                                    System.out.print("Enter robot price: ");
                                    double price = scanner.nextDouble();
                                    System.out.print("Enter robot stock: ");
                                    int stock = scanner.nextInt();
                                    Admin admin = adminData.getAdminList().stream()
                                            .filter(a -> a.getUsername().equals(adminUsername))
                                            .findFirst().orElse(null);
                                    if (admin != null) {
                                        admin.addRobot(new RobotProduct(model, price, stock));
                                    }
                                    break;
                                case 2:
                                    // Update robot stock
                                    System.out.print("Enter robot model: ");
                                    String updateModel = scanner.nextLine();
                                    System.out.print("Enter new stock: ");
                                    int newStock = scanner.nextInt();
                                    Admin updateAdmin = adminData.getAdminList().stream()
                                            .filter(a -> a.getUsername().equals(adminUsername))
                                            .findFirst().orElse(null);
                                    if (updateAdmin != null) {
                                        updateAdmin.updateRobotStock(updateModel, newStock);
                                    }
                                    break;
                                case 3:
                                    // Remove robot
                                    System.out.print("Enter robot model to remove: ");
                                    String removeModel = scanner.nextLine();
                                    Admin removeAdmin = adminData.getAdminList().stream()
                                            .filter(a -> a.getUsername().equals(adminUsername))
                                            .findFirst().orElse(null);
                                    if (removeAdmin != null) {
                                        removeAdmin.removeRobot(removeModel);
                                    }
                                    break;
                                case 4:
                                    // View inventory
                                    Admin viewAdmin = adminData.getAdminList().stream()
                                            .filter(a -> a.getUsername().equals(adminUsername))
                                            .findFirst().orElse(null);
                                    if (viewAdmin != null) {
                                        viewAdmin.showInventory();
                                    }
                                    break;
                                case 5:
                                    // Logout
                                    adminLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        }
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;
                case 4:
                    // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
