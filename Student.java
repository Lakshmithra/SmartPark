
public class Student extends User {

    private int year;

    // Constructor
    public Student(String userId, String name,
                   String department, int year, String phone) {

        super(userId, name, department, phone);
        this.year = year;
    }

    // Getter
    public int getYear() {
        return year;
    }

    // Overriding abstract method
    @Override
    public String getUserType() {
        return "Student";
    }

    // Display student details
    @Override
    public void displayDetails() {

        super.displayDetails();
        System.out.println("Year       : " + year);
    }
}
