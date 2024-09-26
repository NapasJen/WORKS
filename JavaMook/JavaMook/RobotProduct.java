// RobotProduct class representing a product (robot)
class RobotProduct {
    private String model;
    private double price;
    private int stock;

    // Constructor
    public RobotProduct(String model, double price, int stock) {
        this.model = model;
        this.price = price;
        this.stock = stock;
    }

    // Getters and setters
    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Method to set the stock of the robot
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Purchase method reduces the stock
    public void purchase(int quantity) {
        stock -= quantity;
    }

    // Restock method increases the stock
    public void restock(int quantity) {
        stock += quantity;
    }

    @Override
    public String toString() {
        return model + " - $" + price + " (Stock: " + stock + ")";
    }
}
