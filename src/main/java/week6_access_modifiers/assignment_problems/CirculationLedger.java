package week6_access_modifiers.assignment_problems;

import java.util.Arrays;

public class CirculationLedger {

    static String branchCode;

    static {
        branchCode = "SRM-LIB-001";
        System.out.println("CirculationLedger initialized for branch: " + branchCode);
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        System.out.println("\n--- Problem 5: LoanReceipt immutability ---");
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("After tampering returned array, r.getBookIds()[0]: " + r.getBookIds()[0]);

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Original: " + Arrays.toString(r.getBookIds()));
        System.out.println("Corrected: " + Arrays.toString(corrected.getBookIds()));

        System.out.println("\n--- Problem 5: processNightlyCirculation ---");
        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(receipts));
    }
}
