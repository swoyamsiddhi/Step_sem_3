package week5_inheritance.assignment_problems;

import java.util.Arrays;

public class GymApp {

    static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Multilevel descendant (2 generations deep)";
        } else {
            return "Base member (1 generation)";
        }
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember m : members) {
            total += m.getSessionsAttended();
        }
        return total;
    }

    static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (GymMember m : members) {
            sb.append(m.displayInfo());
            if (m instanceof PremiumMember) {
                PremiumMember pm = (PremiumMember) m;
                sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (GymMember m : members) {
            if (m == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (m instanceof GroupClassMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1: signUpBatch ---");
        String[] ids = {"MEM1", "GM1", "MEM2", " ", "MEM3"};
        System.out.println(GymMember.signUpBatch(ids, 1000));

        System.out.println("\n--- Problem 1: attendSession ---");
        PremiumMember p1 = new PremiumMember("MEM01", 2000, "Coach Riya");
        p1.attendSession();
        p1.attendSession();
        System.out.println("Sessions attended: " + p1.getSessionsAttended());

        System.out.println("\n--- Problem 2: displayInfo ---");
        System.out.println(new GymMember("MEM1", 1000).displayInfo());
        System.out.println(new PremiumMember("MEM2", 2000, "Coach Riya").displayInfo());
        System.out.println(new EliteMember("MEM3", 3000, "Coach Arjun", "L12").displayInfo());
        System.out.println(new GroupClassMember("MEM4", 1500, "Zumba").displayInfo());

        System.out.println("\n--- Problem 2: classifyGeneration ---");
        EliteMember elite = new EliteMember("MEM5", 3000, "Coach Arjun", "L12");
        GroupClassMember group = new GroupClassMember("MEM6", 1500, "Zumba");
        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));

        System.out.println("\n--- Problem 2: getTotalSessionsAttended ---");
        PremiumMember pm = new PremiumMember("MEM7", 2000, "Coach Riya");
        pm.attendSession(); pm.attendSession(); pm.attendSession();
        elite.attendSession(); elite.attendSession();
        group.attendSession(); group.attendSession(); group.attendSession(); group.attendSession();
        System.out.println("Total sessions: " + getTotalSessionsAttended(new GymMember[]{pm, elite, group}));

        System.out.println("\n--- Problem 3: chargeLateFee with override ---");
        PremiumMember p5 = new PremiumMember("MEM5X", 2000, "Coach Riya");
        p5.chargeLateFee(200);
        System.out.println("Total late fees (premium, 200 halved): " + p5.getTotalLateFees());
        int[] history = p5.getLateFeeHistory();
        history[0] = 999;
        System.out.println("History after tampering (should still be [100]): " + Arrays.toString(p5.getLateFeeHistory()));

        System.out.println("\n--- Problem 4: batchPrint ---");
        System.out.println(batchPrint(new GymMember[]{new GymMember("MEM6X", 1000), new PremiumMember("MEM7X", 2000, "Coach Riya")}));

        System.out.println("\n--- Problem 5: membershipNumber ---");
        GymMember m1 = new GymMember(1000);
        System.out.println("Membership number: " + m1.getMembershipNumberStr());
        System.out.println("Members enrolled: " + GymMember.getMembersEnrolled());

        System.out.println("\n--- Problem 5: isValidReferralCode ---");
        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("G4B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));

        System.out.println("\n--- Problem 5: payFee overloads ---");
        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("Total fees paid: " + m1.getFeesPaid());

        System.out.println("\n--- Problem 5: processWeeklyCheckIn ---");
        GymMember[] checkIn = {new GroupClassMember(1500, "Zumba"), null, new GymMember(1000)};
        System.out.println(processWeeklyCheckIn(checkIn));
    }
}
