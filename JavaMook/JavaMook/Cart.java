import java.util.ArrayList;

// Cart class
class Cart {
    private ArrayList<CartItem> cartItems;

    // Constructor
    public Cart() {
        cartItems = new ArrayList<>();
    }

    // Add robot to cart with quantity check
    public void addRobotToCart(RobotProduct robot, int quantity) {
        if (robot.getStock() >= quantity) {
            CartItem cartItem = findCartItem(robot);
            if (cartItem != null) {
                // If the robot is already in the cart, just update the quantity
                cartItem.addQuantity(quantity);
            } else {
                // Otherwise, create a new cart item
                cartItems.add(new CartItem(robot, quantity));
            }
            robot.purchase(quantity);  // Reduce stock in inventory
            System.out.println(quantity + " unit(s) of " + robot.getModel() + " added to cart.");
        } else {
            System.out.println("Not enough stock available. Only " + robot.getStock() + " unit(s) in stock.");
        }
    }

    // Remove robot from cart
    public void removeRobotFromCart(String model) {
        CartItem cartItem = findCartItem(model);
        if (cartItem != null) {
            RobotProduct robot = cartItem.getRobot();
            robot.restock(cartItem.getQuantity());  // Return stock to the inventory
            cartItems.remove(cartItem);
            System.out.println("Removed " + model + " from cart.");
        } else {
            System.out.println("Robot not found in cart.");
        }
    }

    // Update quantity of a robot in the cart
    public void updateRobotQuantity(String model, int newQuantity) {
        CartItem cartItem = findCartItem(model);
        if (cartItem != null) {
            RobotProduct robot = cartItem.getRobot();
            int currentQuantity = cartItem.getQuantity();

            if (newQuantity > currentQuantity) {
                int additionalQuantity = newQuantity - currentQuantity;
                if (robot.getStock() >= additionalQuantity) {
                    cartItem.setQuantity(newQuantity);
                    robot.purchase(additionalQuantity);  // Reduce stock in inventory
                    System.out.println("Updated quantity of " + model + " to " + newQuantity);
                } else {
                    System.out.println("Not enough stock available. Only " + robot.getStock() + " unit(s) in stock.");
                }
            } else if (newQuantity < currentQuantity) {
                int returnQuantity = currentQuantity - newQuantity;
                cartItem.setQuantity(newQuantity);
                robot.restock(returnQuantity);  // Return stock to inventory
                System.out.println("Updated quantity of " + model + " to " + newQuantity);
            }
        } else {
            System.out.println("Robot not found in cart.");
        }
    }

    // Show items in the cart
    public void showCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Robots in Cart:");
            for (CartItem item : cartItems) {
                System.out.println(item);
            }
        }
    }

    // Checkout and calculate total cost
    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Add robots to cart before checkout.");
            return;
        }

        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getRobot().getPrice() * item.getQuantity();
        }
        System.out.println("Total price: $" + total);
        System.out.println("Thank you for shopping!");
        cartItems.clear();  // Empty the cart after checkout
    }

    // Helper method to find a CartItem by robot model
    private CartItem findCartItem(String model) {
        for (CartItem item : cartItems) {
            if (item.getRobot().getModel().equalsIgnoreCase(model)) {
                return item;
            }
        }
        return null;
    }

    private CartItem findCartItem(RobotProduct robot) {
        for (CartItem item : cartItems) {
            if (item.getRobot().equals(robot)) {
                return item;
            }
        }
        return null;
    }
}
