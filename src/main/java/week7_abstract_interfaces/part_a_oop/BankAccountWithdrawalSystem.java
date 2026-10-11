package week7_abstract_interfaces.part_a_oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// ─── Part A – Problem 1: Bank Account Withdrawal System ──────────────────────

abstract class BankAccount {
    private final String id;
    private double balance;

    public BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() { return id; }
    public double getBalance() { return balance; }

    /** Template method: subclass validates, then calls super to deduct. */
    public String withdraw(double amount) {
        String rejection = validate(amount);
        if (rejection != null) {
            return id + " rejected: " + rejection;
        }
        balance -= amount;
        return id + " balance " + (int) balance;
    }

    /** Returns the rejection reason as a String, or null if allowed. */
    protected abstract String validate(double amount);
}

class SavingsAccount extends BankAccount {
    private static final double MIN_BALANCE = 1000.0;

    public SavingsAccount(String id, double balance) { super(id, balance); }

    @Override
    protected String validate(double amount) {
        if (getBalance() - amount < MIN_BALANCE) {
            return "minimum balance 1000";
        }
        return null;
    }
}

class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(String id, double balance) { super(id, balance); }

    @Override
    protected String validate(double amount) {
        if (getBalance() - amount < -OVERDRAFT_LIMIT) {
            return "overdraft limit 5000";
        }
        return null;
    }
}

public class BankAccountWithdrawalSystem {

    public static void main(String[] args) {
        System.out.println("--- Part A Q1: Bank Account Withdrawal System ---");

        // Parse from hard-coded sample (mirrors the required input/output)
        Map<String, BankAccount> accounts = new HashMap<>();

        String[] input = {
            "Savings S1 5000",
            "WITHDRAW S1 4500",
            "Current C1 2000",
            "WITHDRAW C1 6000",
            "WITHDRAW S1 3000",
            "WITHDRAW C1 2000"
        };

        for (String line : input) {
            String[] parts = line.trim().split("\\s+");
            switch (parts[0]) {
                case "Savings":
                    accounts.put(parts[1], new SavingsAccount(parts[1], Double.parseDouble(parts[2])));
                    break;
                case "Current":
                    accounts.put(parts[1], new CurrentAccount(parts[1], Double.parseDouble(parts[2])));
                    break;
                case "WITHDRAW": {
                    String id = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    if (!accounts.containsKey(id)) {
                        System.out.println("Account not found");
                    } else {
                        System.out.println(accounts.get(id).withdraw(amount));
                    }
                    break;
                }
            }
        }
    }
}
