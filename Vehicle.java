
public abstract class Vehicle {

    private String vehicleNumber;
    private String ownerId;
    private String model;

    public Vehicle(String vehicleNumber, String ownerId,
                   String model) {

        this.vehicleNumber = vehicleNumber;
        this.ownerId = ownerId;
        this.model = model;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getModel() {
        return model;
    }

    public abstract String getVehicleType();

    public void displayDetails() {
        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Owner ID       : " + ownerId);
        System.out.println("Model          : " + model);
        System.out.println("Vehicle Type   : " + getVehicleType());
    }
}
