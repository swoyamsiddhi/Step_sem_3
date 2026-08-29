package week3_oop.assigment_problems;

/**
 * M3 — Overloaded Constructors for an Employee
 *
 * Demonstrates constructor overloading and this() chaining so that
 * an intern constructor reuses the permanent-employee constructor
 * instead of duplicating initialization logic.
 */
public class Employee {

    // ── Fields ──
    String empId;
    String empName;
    double salary;
    boolean isIntern;

    // ── 3-arg constructor (permanent employee) ──
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    // ── 2-arg constructor (intern) — chains via this() ──
    public Employee(String empId, String empName) {
        this(empId, empName, 0);   // reuse 3-arg constructor with salary = 0
        this.isIntern = true;       // then override isIntern
    }

    // ── Instance method ──
    void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }

    // ── Driver ──
    public static void main(String[] args) {

        Employee permanent = new Employee("E-101", "Divya", 65000);
        Employee intern    = new Employee("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}
