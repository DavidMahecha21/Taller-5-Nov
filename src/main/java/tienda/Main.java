package tienda;

public class Main {
    public static void main(String[] args) {
        Checkout checkout = new Checkout(new SmartShippingCalculator());
        Order order = new Order("o1", "c1", "Medellín", 100, 4);
        System.out.println("Total: " + checkout.total(order));
    }
}