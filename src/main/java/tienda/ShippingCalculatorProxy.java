package tienda;

public class ShippingCalculatorProxy implements ShippingCalculator {
    private final ShippingCalculator legacy;
    private final ShippingCalculator smart;
    private final FeatureFlags flags;

    public ShippingCalculatorProxy(ShippingCalculator legacy,
                                   ShippingCalculator smart,
                                   FeatureFlags flags) {
        this.legacy = legacy;
        this.smart = smart;
        this.flags = flags;
    }

        @Override
    public double calculate(Order order) {
        boolean usaSmart = flags.isEnabled("smart-shipping");
        long inicio = System.nanoTime();

        double resultado = usaSmart ? smart.calculate(order) : legacy.calculate(order);

        long ms = (System.nanoTime() - inicio) / 1_000_000;
        System.out.println("[Proxy] respondió " + (usaSmart ? "SMART" : "LEGACY")
                + " en " + ms + " ms");
        return resultado;
    }
}