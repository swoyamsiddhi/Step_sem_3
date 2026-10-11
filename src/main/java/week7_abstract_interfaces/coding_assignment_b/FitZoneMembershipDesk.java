package week7_abstract_interfaces.coding_assignment_b;

// ─── Q4: The FitZone Membership Desk ─────────────────────────────────────────

interface MembershipPlan {
    String planName();
    int durationMonths();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {
    private static final double BASE = 1000.0;
    @Override public String planName() { return "Monthly"; }
    @Override public int durationMonths() { return 1; }
    @Override public double calculateFee() { return BASE * durationMonths(); }
}

class QuarterlyPlan implements MembershipPlan {
    private static final double BASE = 1000.0;
    @Override public String planName() { return "Quarterly"; }
    @Override public int durationMonths() { return 3; }
    @Override public double calculateFee() { return BASE * durationMonths() * 0.90; }
}

class AnnualPlan implements MembershipPlan {
    private static final double BASE = 1000.0;
    @Override public String planName() { return "Annual"; }
    @Override public int durationMonths() { return 12; }
    @Override public double calculateFee() { return BASE * durationMonths() * 0.75; }
}

enum MembershipStatus { ACTIVE, FROZEN, EXPIRED }

class Membership {
    private final String memberName;
    private final MembershipPlan plan;
    private MembershipStatus status;

    public Membership(String memberName, MembershipPlan plan) {
        this.memberName = memberName;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: Active%n",
                plan.planName(), memberName, plan.calculateFee());
    }

    public void checkIn() {
        switch (status) {
            case ACTIVE:
                System.out.println(memberName + " checked in successfully.");
                break;
            case FROZEN:
                System.out.println("Check-in denied: " + memberName + "'s membership is Frozen.");
                break;
            case EXPIRED:
                System.out.println("Check-in denied: " + memberName + "'s membership is Expired.");
                break;
        }
    }

    public void freeze() {
        if (status == MembershipStatus.ACTIVE) {
            status = MembershipStatus.FROZEN;
            System.out.println(memberName + "'s membership frozen. Status: Frozen.");
        } else if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
        } else {
            System.out.println(memberName + "'s membership is already Frozen.");
        }
    }

    public void unfreeze() {
        if (status == MembershipStatus.FROZEN) {
            status = MembershipStatus.ACTIVE;
            System.out.println(memberName + "'s membership unfrozen. Status: Active.");
        } else if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else {
            System.out.println(memberName + "'s membership is already Active.");
        }
    }

    /** Called externally to simulate expiry (e.g., end of plan duration). */
    public void expire() {
        if (status == MembershipStatus.EXPIRED) return;
        status = MembershipStatus.EXPIRED;
        System.out.println(memberName + "'s membership expired. Status: Expired.");
    }
}

public class FitZoneMembershipDesk {

    public static void main(String[] args) {
        System.out.println("--- Q4: The FitZone Membership Desk ---");

        Membership asha = new Membership("Asha", new QuarterlyPlan());
        Membership ravi = new Membership("Ravi", new MonthlyPlan());

        asha.checkIn();       // Active – succeeds
        asha.freeze();        // Freeze
        asha.checkIn();       // Frozen – denied

        ravi.expire();        // Simulate expiry
        ravi.freeze();        // Cannot freeze expired
    }
}
