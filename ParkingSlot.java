
public class ParkingSlot {

    private String slotId;
    private String vehicleType;
    private boolean occupied;

    public ParkingSlot(String slotId, String vehicleType) {
        this.slotId = slotId;
        this.vehicleType = vehicleType;
        this.occupied = false;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        occupied = true;
    }

    public void release() {
        occupied = false;
    }
}
