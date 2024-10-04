import java.util.ArrayList;


class Cart {
    private ArrayList<CartItem> cartItems;

    
    public Cart() {
        cartItems = new ArrayList<>();
    }


    public void addRobotToCart(RobotProduct robot, int quantity) {
        if (robot.getStock() >= quantity) {
            CartItem cartItem = findCartItem(robot);
            if (cartItem != null) {
              
                cartItem.addQuantity(quantity);
            } else {
               
                cartItems.add(new CartItem(robot, quantity));
            }
            robot.purchase(quantity); 
            System.out.println(quantity + " unit(s) of " + robot.getModel() + " added to cart.");
        } else {
            System.out.println("Not enough stock available. Only " + robot.getStock() + " unit(s) in stock.");
        }
    }


    public void removeRobotFromCart(String model) {
        CartItem cartItem = findCartItem(model);
        if (cartItem != null) {
            RobotProduct robot = cartItem.getRobot();
            robot.restock(cartItem.getQuantity());  
            cartItems.remove(cartItem);
            System.out.println("Removed " + model + " from cart.");
        } else {
            System.out.println("Robot not found in cart.");
        }
    }


    public void updateRobotQuantity(String model, int newQuantity) {
        CartItem cartItem = findCartItem(model);
        if (cartItem != null) {
            RobotProduct robot = cartItem.getRobot();
            int currentQuantity = cartItem.getQuantity();

            if (newQuantity > currentQuantity) {
                int additionalQuantity = newQuantity - currentQuantity;
                if (robot.getStock() >= additionalQuantity) {
                    cartItem.setQuantity(newQuantity);
                    robot.purchase(additionalQuantity);  
                    System.out.println("Updated quantity of " + model + " to " + newQuantity);
                } else {
                    System.out.println("Not enough stock available. Only " + robot.getStock() + " unit(s) in stock.");
                }
            } else if (newQuantity < currentQuantity) {
                int returnQuantity = currentQuantity - newQuantity;
                cartItem.setQuantity(newQuantity);
                robot.restock(returnQuantity);  
                System.out.println("Updated quantity of " + model + " to " + newQuantity);
            }
        } else {
            System.out.println("Robot not found in cart.");
        }
    }

  
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
        cartItems.clear();  
    }

  
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
