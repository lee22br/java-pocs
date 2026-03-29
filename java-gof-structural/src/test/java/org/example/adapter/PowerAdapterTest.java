package org.example.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PowerAdapterTest {
    @Test
    void testAdapterBridgesCall() {
        AmericanDevice device = new AmericanDevice();

        EuropeanSocket adapter = new PowerAdapter(device);

        assertDoesNotThrow(() -> adapter.provide220V());
    }

    @Test
    void testAdapterTypeCompatibility() {
        AmericanDevice device = new AmericanDevice();
        EuropeanSocket adapter = new PowerAdapter(device);

        assertTrue(adapter instanceof EuropeanSocket, "Adapter must implement the target interface");
    }
}
