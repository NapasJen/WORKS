import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AdminData adminData = new AdminData();  
        AuthSystem authSystem = new AuthSystem();  
        Cart cart = new Cart();  
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                  
                    System.out.print("Enter username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    authSystem.signUp(newUsername, newPassword);
                    break;

                case 2:
              
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (authSystem.login(username, password)) {
                       
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
                            scanner.nextLine(); 

                            switch (userChoice) {
                                case 1:
                                   
                                    for (Admin admin : adminData.getAdminList()) {
                                        admin.showInventory();
                                    }
                                    break;
                                case 2:
                                   
                                    System.out.print("Enter robot model to add to cart: ");
                                    String cartModel = scanner.nextLine();
                                    RobotProduct selectedRobot = null;

                                  
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

                                       
                                        cart.addRobotToCart(selectedRobot, quantity);
                                    } else {
                                        System.out.println("Robot model not found.");
                                    }
                                    break;
                                case 3:
                                  
                                    cart.showCart();
                                    break;
                                case 4:
                                   
                                    System.out.print("Enter robot model to remove from cart: ");
                                    String removeModel = scanner.nextLine();
                                    cart.removeRobotFromCart(removeModel);
                                    break;
                                case 5:
                                   
                                    System.out.print("Enter robot model to update quantity: ");
                                    String updateModel = scanner.nextLine();
                                    System.out.print("Enter new quantity: ");
                                    int newQuantity = scanner.nextInt();
                                    cart.updateRobotQuantity(updateModel, newQuantity);
                                    break;
                                case 6:
                                  
                                    cart.checkout();
                                    break;
                                case 7:
                               
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
                
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();
                    if (adminData.authenticate(adminUsername, adminPassword)) {
                       
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
                            scanner.nextLine(); 

                            switch (adminChoice) {
                                case 1:
                                 
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
                                   
                                    Admin viewAdmin = adminData.getAdminList().stream()
                                            .filter(a -> a.getUsername().equals(adminUsername))
                                            .findFirst().orElse(null);
                                    if (viewAdmin != null) {
                                        viewAdmin.showInventory();
                                    }
                                    break;
                                case 5:
                                 
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
                 
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
