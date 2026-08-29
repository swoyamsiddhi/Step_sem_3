package week4_oop.assigment_problems;

class FeeAccount {
}

class HostelFeeAccount extends FeeAccount {
}

public class AccountBatchPayments {
    static int hostelCount = 0;
    static int dayScholarCount = 0;

    public static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
            hostelCount++;
        } else if (account instanceof FeeAccount) {
            System.out.println("Paid in one go (day-scholar account)");
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        for (int i = 0; i < accounts.length; i++) {
            processPayment(accounts[i], 60000);
        }

        System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
    }
}
