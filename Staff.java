
public class Staff extends User {

    public Staff(String userId, String name,
                 String department, String phone) {

        super(userId, name, department, phone);
    }

    @Override
    public String getUserType() {
        return "Staff";
    }
}

