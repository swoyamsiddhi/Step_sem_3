package week5_inheritance.assignment_problems;

public class GymMember {

    private static int memberCount = 0;
    private static int nextNumber = 2001;

    String memberId;
    int monthlyFee;
    private int sessionsAttended;
    private int[] lateFeeHistory;
    private int lateFeeCount;
    private int totalLateFees;
    final int membershipNumber;
    private int feesPaid;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID: " + memberId);
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.lateFeeHistory = new int[10];
        memberCount++;
        membershipNumber = nextNumber++;
    }

    public GymMember(int monthlyFee) {
        this.monthlyFee = monthlyFee;
        this.lateFeeHistory = new int[10];
        memberCount++;
        membershipNumber = nextNumber++;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    protected void chargeLateFee(int amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
        totalLateFees += amount;
    }

    public int[] getLateFeeHistory() {
        int[] copy = new int[lateFeeCount];
        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }
        return copy;
    }

    public int getTotalLateFees() {
        return totalLateFees;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getMembershipNumberStr() {
        return "GYM-" + membershipNumber;
    }

    public static int getMembersEnrolled() {
        return memberCount;
    }

    public static boolean isValidReferralCode(String code) {
        if (code.length() != 4) return false;
        if (code.charAt(0) != 'G') return false;
        if (!Character.isDigit(code.charAt(1))) return false;
        if (!Character.isDigit(code.charAt(2))) return false;
        if (!Character.isUpperCase(code.charAt(3))) return false;
        return true;
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedup = 0;
        int rejected = 0;
        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedup++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedup + " | Rejected: " + rejected;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }
}
