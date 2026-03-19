package org.example.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void shouldCreateCarWithAllFields() {
        Car car = new Car.Builder()
                .setEngine("V12")
                .setWheels(4)
                .setColor("Black")
                .setAirConditioning(true)
                .build();

        assertNotNull(car);
        assertEquals("V12", car.getEngine());
        assertEquals(4, car.getWheels());
        assertEquals("Black", car.getColor());
        assertTrue(car.isAirConditioning());
    }

    @Test
    void shouldCreateCarWithPartialFields() {
        // Arrange & Act (Only engine and color)
        Car basicCar = new Car.Builder()
                .setEngine("1.0")
                .setColor("White")
                .build();

        // Assert
        assertEquals("1.0", basicCar.getEngine());
        assertEquals("White", basicCar.getColor());
        assertEquals(0, basicCar.getWheels()); // Default int value
        assertFalse(basicCar.isAirConditioning()); // Default boolean value
    }

    @Test
    void shouldReturnSameBuilderInstanceOnSetters() {
        // Testing the Fluent Interface (Method Chaining)
        Car.Builder builder = new Car.Builder();
        Car.Builder sameBuilder = builder.setEngine("V8");

        assertSame(builder, sameBuilder, "The builder should return 'this' to allow chaining");
    }
}
