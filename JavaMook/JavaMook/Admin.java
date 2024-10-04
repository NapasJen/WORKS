import java.util.ArrayList;

class Admin {
    private String username;
    private String password;
    private ArrayList<RobotProduct> robotInventory;


    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.robotInventory = new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void addRobot(RobotProduct robot) {
        robotInventory.add(robot);
        System.out.println("Robot added: " + robot.getModel());
    }

 
    public void updateRobotStock(String model, int newStock) {
        for (RobotProduct robot : robotInventory) {
            if (robot.getModel().equalsIgnoreCase(model)) {
                robot.setStock(newStock);
                System.out.println("Updated stock for " + model + ": " + newStock);
                return;
            }
        }
        System.out.println("Robot not found.");
    }

 
    public void removeRobot(String model) {
        robotInventory.removeIf(robot -> robot.getModel().equalsIgnoreCase(model));
        System.out.println("Robot removed: " + model);
    }


    public void showInventory() {
        if (robotInventory.isEmpty()) {
            System.out.println("No robots in inventory.");
        } else {
            System.out.println("Robots in Inventory:");
            for (RobotProduct robot : robotInventory) {
                System.out.println(robot);
            }
        }
    }


    public ArrayList<RobotProduct> getRobotInventory() {
        return robotInventory;
    }
}
