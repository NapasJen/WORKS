
class RobotProduct {
    private String model;
    private double price;
    private int stock;

 
    public RobotProduct(String model, double price, int stock) {
        this.model = model;
        this.price = price;
        this.stock = stock;
    }


    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

 
    public void setStock(int stock) {
        this.stock = stock;
    }


    public void purchase(int quantity) {
        stock -= quantity;
    }

  
    public void restock(int quantity) {
        stock += quantity;
    }

    @Override
    public String toString() {
        return model + " - $" + price + " (Stock: " + stock + ")";
    }
}
