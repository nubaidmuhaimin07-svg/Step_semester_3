import java.util.*;

class Product {
    private final String name;
    private final double price;
    public Product(String name, double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.name = name;
        this.price = price;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class Customer {
    private final String name;
    public Customer(String name) { this.name = name; }
    public String getName() { return name; }
}

class OrderItem {
    private final Product product;
    private final int quantity;
    public OrderItem(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        this.product = product;
        this.quantity = quantity;
    }
    public double total() { return product.getPrice() * quantity; }
}

class Order {
    private final String id;
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private String status = "Pending";

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        System.out.println("Order created for " + customer.getName() + ".");
    }
    public String getId() { return id; }
    public String getStatus() { return status; }
    public void addItem(Product product, int quantity) { items.add(new OrderItem(product, quantity)); }
    public boolean isEmpty() { return items.isEmpty(); }
    public double total() {
        double total = 0;
        for (OrderItem item : items) total += item.total();
        return total;
    }
    public void markPaid() { status = "Paid"; }
}

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) { return true; }
    public String getName() { return "Credit Card"; }
}

class PayPalPayment implements PaymentMethod {
    private final boolean succeeds;
    public PayPalPayment(boolean succeeds) { this.succeeds = succeeds; }
    public boolean processPayment(double amount) { return succeeds; }
    public String getName() { return "PayPal"; }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) { return true; }
    public String getName() { return "Bank Transfer"; }
}

class PaymentService {
    public void pay(Order order, PaymentMethod method) {
        if (order.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }
        System.out.println("Payment initiated via " + method.getName() + " for Order " + order.getId() + ".");
        if (method.processPayment(order.total())) {
            order.markPaid();
            System.out.println("Payment for Order " + order.getId() + " successful.");
        } else {
            System.out.println("Payment for Order " + order.getId() + " failed.");
        }
        System.out.println("Order status: " + order.getStatus());
    }
}

public class Q5ShoppingPaymentSystem {
    public static void main(String[] args) {
        Customer x = new Customer("Customer X");
        Order orderX = new Order("X", x);
        orderX.addItem(new Product("Product A", 20), 2);
        orderX.addItem(new Product("Product B", 15), 1);
        new PaymentService().pay(orderX, new CreditCardPayment());

        Order empty = new Order("Y", new Customer("Customer Y"));
        new PaymentService().pay(empty, new CreditCardPayment());

        Order orderZ = new Order("Z", new Customer("Customer Z"));
        orderZ.addItem(new Product("Product C", 30), 1);
        new PaymentService().pay(orderZ, new PayPalPayment(false));
    }
}
