
public abstract class User {

    private String userId;
    private String name;
    private String department;
    private String phone;

    // Constructor
    public User(String userId, String name,
                String department, String phone) {

        this.userId = userId;
        this.name = name;
        this.department = department;
        this.phone = phone;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }

    // Abstract method
    public abstract String getUserType();

    // Display user details
    public void displayDetails() {

        System.out.println("User ID    : " + userId);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);
        System.out.println("Phone      : " + phone);
        System.out.println("User Type  : " + getUserType());
    }
}
