package week7_abstract_interfaces.coding_questions;

import java.util.ArrayList;
import java.util.List;

// ─── Q5: Payment Processing for a Shopping System ───────────────────────────

class Product {
    private final String name;
    private final double price;
    public Product(String name, double price) { this.name = name; this.price = price; }
    public double getPrice() { return price; }
    public String getName() { return name; }
}

interface PaymentMethod {
    /** Returns true if payment was processed successfully. */
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    private final boolean willSucceed;
    public CreditCardPayment(boolean willSucceed) { this.willSucceed = willSucceed; }
    @Override
    public boolean processPayment(double amount) {
        System.out.printf("Payment initiated via Credit Card (amount: %.2f).%n", amount);
        return willSucceed;
    }
}

class PayPalPayment implements PaymentMethod {
    private final boolean willSucceed;
    public PayPalPayment(boolean willSucceed) { this.willSucceed = willSucceed; }
    @Override
    public boolean processPayment(double amount) {
        System.out.printf("Payment initiated via PayPal (amount: %.2f).%n", amount);
        return willSucceed;
    }
}

class BankTransferPayment implements PaymentMethod {
    private final boolean willSucceed;
    public BankTransferPayment(boolean willSucceed) { this.willSucceed = willSucceed; }
    @Override
    public boolean processPayment(double amount) {
        System.out.printf("Payment initiated via Bank Transfer (amount: %.2f).%n", amount);
        return willSucceed;
    }
}

enum OrderStatus { PENDING, PAID }

class ShoppingOrder {
    private final String orderId;
    private final List<Product> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.PENDING;

    public ShoppingOrder(String orderId) {
        this.orderId = orderId;
        System.out.println("Order created: " + orderId);
    }

    public void addProduct(Product p, int qty) {
        for (int i = 0; i < qty; i++) items.add(p);
    }

    public double total() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public void pay(PaymentMethod method) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }
        boolean success = method.processPayment(total());
        if (success) {
            status = OrderStatus.PAID;
            System.out.println("Payment for " + orderId + " successful. Order status: PAID.");
        } else {
            System.out.println("Payment for " + orderId + " failed. Order status: PENDING.");
        }
    }
}

public class PaymentProcessingSystem {

    public static void main(String[] args) {
        System.out.println("--- Q5: Payment Processing for a Shopping System ---");

        // Customer X – credit card, succeeds
        ShoppingOrder orderX = new ShoppingOrder("Order X");
        orderX.addProduct(new Product("Product A", 50.0), 2);
        orderX.addProduct(new Product("Product B", 30.0), 1);
        orderX.pay(new CreditCardPayment(true));

        // Customer Y – empty order
        ShoppingOrder orderY = new ShoppingOrder("Order Y");
        orderY.pay(new CreditCardPayment(true));

        // Customer Z – PayPal, fails
        ShoppingOrder orderZ = new ShoppingOrder("Order Z");
        orderZ.addProduct(new Product("Product C", 75.0), 1);
        orderZ.pay(new PayPalPayment(false));
    }
}
