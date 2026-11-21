
public class FourWheeler extends Vehicle {

    public FourWheeler(String vehicleNumber,
                       String ownerId, String model) {

        super(vehicleNumber, ownerId, model);
    }

    @Override
    public String getVehicleType() {
        return "Four-Wheeler";
    }
}
