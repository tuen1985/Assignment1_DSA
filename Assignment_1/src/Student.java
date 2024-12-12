public class Student {
    private String studentID;
    private String name;
    private double marks;

    public Student(String studentID, String name, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.marks = marks;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return this.marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getRank() {
        if (this.marks >= 0.0 && this.marks <= 5.0) {
            return "Fail";
        } else if (this.marks > 5.0 && this.marks <= 6.5) {
            return "Medium";
        } else if (this.marks > 6.5 && this.marks <= 7.5) {
            return "Good";
        } else {
            return this.marks > 7.5 && this.marks <= 9.0 ? "Very Good" : "Excellent";
        }
    }

    public String toString() {
        String var10000 = this.studentID;
        return "ID: " + var10000 + ", Name: " + this.name + ", Marks: " + this.marks + ", Rank: " + this.getRank();
    }
}
