package week7_abstract_interfaces.assignment_problems;

public class WarehouseApp {

    static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 2: Warehouse Label Printer ---");
        PackageBox p = new PackageBox("TRK-88");
        Invoice i = new Invoice("INV-42");
        System.out.println(p.printLabel());
        System.out.println(i.printLabel());
        System.out.println("--- printAll ---");
        printAll(new Printable[]{p, i});
    }
}
