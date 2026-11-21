
public class TwoWheeler extends Vehicle {

    public TwoWheeler(String vehicleNumber,
                      String ownerId, String model) {

        super(vehicleNumber, ownerId, model);
    }

    @Override
    public String getVehicleType() {
        return "Two-Wheeler";
    }
}
