package week2_strings.assignment_problems;

public class NormalizeAndValidate {

    String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        String prefix = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return prefix + rest;
    }

    String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must be 10 digits";
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("]");
        sb.append(" YEAR: ").append(year);
        sb.append(" | CATALOG: ").append(catalog);

        return sb.toString();
    }

    public static void main(String[] args) {
        NormalizeAndValidate nav = new NormalizeAndValidate();

        String code1 = nav.normalizeCode(" pen2026004251 ");
        System.out.println(nav.validateAndFormat(code1));

        String code2 = nav.normalizeCode("12N2026004251");
        System.out.println(nav.validateAndFormat(code2));
    }
}
