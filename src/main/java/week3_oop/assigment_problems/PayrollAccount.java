package week3_oop.assigment_problems;

/**
 * M2 — Encapsulated Payroll Account
 *
 * Demonstrates encapsulation: private fields with controlled access,
 * validation inside mutator methods, and read-only getters.
 */
public class PayrollAccount {

    // ── Private fields ──
    private double basicSalary;
    private double bonus;

    // ── Constructor ──
    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: Negative salary provided. Starting at 0.");
            this.basicSalary = 0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0;
    }

    // ── Mutator: credit bonus ──
    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid bonus amount. Must be greater than 0.");
        } else {
            this.bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }
    }

    // ── Mutator: deduct tax ──
    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Invalid tax percentage. Must be between 0 and 100.");
        } else {
            basicSalary -= basicSalary * percent / 100;
            System.out.println("Tax deducted: " + (int) percent + "%");
        }
    }

    // ── Getter: read-only net salary ──
    public double getNetSalary() {
        return basicSalary + bonus;
    }

    // ── Driver ──
    public static void main(String[] args) {

        PayrollAccount account = new PayrollAccount(50000);

        account.creditBonus(5000);
        account.deductTax(10);

        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}
