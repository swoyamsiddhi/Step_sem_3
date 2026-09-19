package week6_access_modifiers.assignment_problems;

public class LibraryMember {

    private String membershipPin;

    String branchCode;

    protected double finesOwed;

    public String displayName;

    public LibraryMember(String displayName, String branchCode, String membershipPin) {
        this.displayName = displayName;
        this.branchCode = branchCode;
        this.membershipPin = membershipPin;
    }
}
