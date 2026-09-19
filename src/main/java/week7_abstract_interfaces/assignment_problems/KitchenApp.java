package week7_abstract_interfaces.assignment_problems;

public class KitchenApp {

    public static void main(String[] args) {
        System.out.println("--- Problem 4: Smart Kitchen Assistant ---");
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println("Speed level: " + b.getSpeedLevel());

        b.setSpeedLevel(9);
        System.out.println("After invalid setSpeedLevel(9), speed is still: " + b.getSpeedLevel());

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}
