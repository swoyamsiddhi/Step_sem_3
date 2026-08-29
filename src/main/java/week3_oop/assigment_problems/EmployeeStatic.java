package week3_oop.assigment_problems;

/**
 * M5 — Instance vs Static: Splitting an Employee Class Correctly
 *
 * Demonstrates the difference between instance fields (per-object)
 * and static fields (shared across all objects). The company name and
 * employee count belong to the class, not to any single employee.
 */
public class EmployeeStatic {

    // ── Instance fields (one copy per object) ──
    String empName;
    double salary;

    // ── Static fields (shared by every object) ──
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    // ── Constructor ──
    EmployeeStatic(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;   // increments once per new employee
    }

    // ── Static method — must NOT reference instance fields ──
    static void printCompanyInfo() {
        System.out.println("Company: " + companyName);
        System.out.println("Total employees: " + employeeCount);
    }

    // ── Driver ──
    public static void main(String[] args) {

        EmployeeStatic e1 = new EmployeeStatic("Aarav",  55000);
        EmployeeStatic e2 = new EmployeeStatic("Meera",  62000);
        EmployeeStatic e3 = new EmployeeStatic("Rohan",  48000);

        // Called through the CLASS name, not through any object
        EmployeeStatic.printCompanyInfo();
    }
}
