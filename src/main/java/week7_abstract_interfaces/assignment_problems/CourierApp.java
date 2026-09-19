package week7_abstract_interfaces.assignment_problems;

public class CourierApp {

    public static void main(String[] args) {
        System.out.println("--- Problem 5: Package Drop-Off Log ---");
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;
        System.out.println("\n--- logAll with upcasting ---");
        DeliveryNote.logAll(new DeliveryNote[]{ref, new LetterNote("TRK-2")});
    }
}
