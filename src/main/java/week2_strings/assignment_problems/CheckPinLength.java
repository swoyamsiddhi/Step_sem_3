package week2_strings.assignment_problems;

public class CheckPinLength {

    void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        CheckPinLength validator = new CheckPinLength();
        validator.checkPinLength("482");
        validator.checkPinLength("4820");
    }
}
