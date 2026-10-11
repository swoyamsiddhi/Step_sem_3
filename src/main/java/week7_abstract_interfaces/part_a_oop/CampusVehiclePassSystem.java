package week7_abstract_interfaces.part_a_oop;

import java.util.HashMap;
import java.util.Map;

// ─── Part A – Problem 2: Campus Vehicle Pass System ──────────────────────────

interface ChargeBay {
    String bookChargingBay();
}

abstract class CampusVehicle {
    private final String passNumber;
    private final String ownerName;

    public CampusVehicle(String passNumber, String ownerName) {
        this.passNumber = passNumber;
        this.ownerName = ownerName;
    }

    public String getPassNumber() { return passNumber; }
    public String getOwnerName() { return ownerName; }
    public abstract int passFee();
    public abstract String typeName();
}

class Bike extends CampusVehicle {
    public Bike(String pass, String owner) { super(pass, owner); }
    @Override public int passFee() { return 300; }
    @Override public String typeName() { return "Bike"; }
}

class Car extends CampusVehicle {
    public Car(String pass, String owner) { super(pass, owner); }
    @Override public int passFee() { return 1000; }
    @Override public String typeName() { return "Car"; }
}

class EBike extends CampusVehicle implements ChargeBay {
    public EBike(String pass, String owner) { super(pass, owner); }
    @Override public int passFee() { return 300; }
    @Override public String typeName() { return "EBike"; }
    @Override public String bookChargingBay() { return getPassNumber() + " charging bay allotted"; }
}

class ECar extends CampusVehicle implements ChargeBay {
    public ECar(String pass, String owner) { super(pass, owner); }
    @Override public int passFee() { return 1000; }
    @Override public String typeName() { return "ECar"; }
    @Override public String bookChargingBay() { return getPassNumber() + " charging bay allotted"; }
}

public class CampusVehiclePassSystem {

    public static void main(String[] args) {
        System.out.println("--- Part A Q2: Campus Vehicle Pass System ---");

        Map<String, CampusVehicle> registry = new HashMap<>();

        String[] input = {
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        };

        for (String line : input) {
            String[] p = line.trim().split("\\s+");
            if (p[0].equals("PASS")) {
                String type = p[1], pass = p[2], owner = p[3];
                CampusVehicle v;
                switch (type) {
                    case "Bike":  v = new Bike(pass, owner);  break;
                    case "Car":   v = new Car(pass, owner);   break;
                    case "EBike": v = new EBike(pass, owner); break;
                    case "ECar":  v = new ECar(pass, owner);  break;
                    default: System.out.println("Unknown vehicle type"); continue;
                }
                registry.put(pass, v);
                System.out.println(pass + " (" + v.typeName() + ") pass fee " + v.passFee());
            } else if (p[0].equals("CHARGE")) {
                String pass = p[1];
                CampusVehicle v = registry.get(pass);
                if (v == null) {
                    System.out.println("Vehicle not found");
                } else if (v instanceof ChargeBay) {
                    System.out.println(((ChargeBay) v).bookChargingBay());
                } else {
                    System.out.println(pass + " rejected: charging unsupported");
                }
            }
        }
    }
}
