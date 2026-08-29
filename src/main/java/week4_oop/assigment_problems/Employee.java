package week4_oop.assigment_problems;

public class Employee {
    String empId;
    double salary;

    public Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    public void printFinalSalary() {
        System.out.println(this.empId + " | Final Salary: Rs " + this.salary);
    }

    public static void main(String[] args) {
        String[] empIds = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000, 55000, 62000, 48000};

        Employee[] employees = new Employee[empIds.length];

        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(empIds[i], startingSalaries[i]);
        }

        for (int i = 0; i < employees.length; i++) {
            employees[i].raiseSalary(5000);
            employees[i].printFinalSalary();
        }
    }
}
