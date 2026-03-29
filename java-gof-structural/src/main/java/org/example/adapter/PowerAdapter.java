package org.example.adapter;

public class PowerAdapter implements EuropeanSocket {
    private final AmericanDevice americanDevice;

    public PowerAdapter(AmericanDevice device) {
        this.americanDevice = device;
    }

    @Override
    public void provide220V() {
        System.out.println("Adapter converting 220V to 110V...");
        // Delegate the actual work to the adaptee
        americanDevice.consume110V();
    }
}