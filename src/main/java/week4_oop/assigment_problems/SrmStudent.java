package week4_oop.assigment_problems;

public class SrmStudent {
    static String collegeName;
    static String academicYear;
    String studentName;

    static {
        collegeName = "SRM";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    public SrmStudent(String studentName) {
        this.studentName = studentName;
        System.out.println("Student record created: " + this.studentName);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        SrmStudent[] students = new SrmStudent[names.length];

        for (int i = 0; i < names.length; i++) {
            students[i] = new SrmStudent(names[i]);
        }
    }
}
