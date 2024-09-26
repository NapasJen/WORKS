// CartItem class to store a robot and its quantity in the cart
class CartItem {
    private RobotProduct robot;
    private int quantity;

    // Constructor
    public CartItem(RobotProduct robot, int quantity) {
        this.robot = robot;
        this.quantity = quantity;
    }

    // Getters and setters
    public RobotProduct getRobot() {
        return robot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Add quantity to the current item
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + robot.getModel() + " - $" + robot.getPrice() + " each";
    }
}
