
class CartItem {
    private RobotProduct robot;
    private int quantity;


    public CartItem(RobotProduct robot, int quantity) {
        this.robot = robot;
        this.quantity = quantity;
    }


    public RobotProduct getRobot() {
        return robot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + robot.getModel() + " - $" + robot.getPrice() + " each";
    }
}
