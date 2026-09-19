package week6_access_modifiers.assignment_problems;

public class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String storedSecurityHash;

    public LibraryMemberBean() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        int hash = 0;
        for (int i = 0; i < answer.length(); i++) {
            hash += answer.charAt(i);
        }
        storedSecurityHash = String.valueOf(hash);
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 4: LibraryMemberBean ---");
        LibraryMemberBean m = new LibraryMemberBean();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println("Membership ID: " + m.getMembershipId());

        m.setMembershipId("FAKE-0000");
        System.out.println("After second setMembershipId (should still be LIB-8841): " + m.getMembershipId());

        System.out.println("Is premium: " + m.isPremiumMember());

        m.setSecurityAnswer("BlueMountain");
        System.out.println("Security answer set (no getter exists).");
    }
}
