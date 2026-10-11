package week7_abstract_interfaces.part_a_oop;

import java.util.ArrayList;
import java.util.List;

// ─── Part A – Problem 5: Payroll Register System ─────────────────────────────

abstract class StaffMember {
    protected final String name;

    public StaffMember(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract double pay();
    public abstract String typeName();

    @Override
    public String toString() {
        return "Payslip[name=" + name + ", type=" + typeName() + ", pay=" + (int) pay() + "]";
    }
}

class FullTimeStaff extends StaffMember {
    private final double salary;
    public FullTimeStaff(String name, double salary) { super(name); this.salary = salary; }
    @Override public double pay() { return salary; }
    @Override public String typeName() { return "FullTime"; }
}

class PartTimeStaff extends StaffMember {
    private final int hours;
    private final double rate;
    public PartTimeStaff(String name, int hours, double rate) {
        super(name); this.hours = hours; this.rate = rate;
    }
    @Override public double pay() { return hours * rate; }
    @Override public String typeName() { return "PartTime"; }
}

class InternStaff extends StaffMember {
    private final double stipend;
    public InternStaff(String name, double stipend) { super(name); this.stipend = stipend; }
    @Override public double pay() { return stipend; }
    @Override public String typeName() { return "Intern"; }
}

public class PayrollRegisterSystem {

    public static void main(String[] args) {
        System.out.println("--- Part A Q5: Payroll Register System ---");

        List<StaffMember> staff = new ArrayList<>();
        staff.add(new FullTimeStaff("Asha", 50000));
        staff.add(new PartTimeStaff("Ravi", 80, 300));
        staff.add(new InternStaff("Neha", 15000));

        double total = 0;
        StaffMember topEarner = staff.get(0);

        for (StaffMember s : staff) {
            System.out.println(s);
            total += s.pay();
            if (s.pay() > topEarner.pay()) topEarner = s;
        }

        System.out.println("Total " + (int) total);
        System.out.println("top earner " + topEarner.getName());
    }
}
