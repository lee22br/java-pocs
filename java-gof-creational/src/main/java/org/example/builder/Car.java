package org.example.builder;

public class Car {
    // Final fields for immutability
    private final String engine;
    private final int wheels;
    private final String color;
    private final boolean airConditioning;

    // Private constructor
    private Car(Builder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
        this.airConditioning = builder.airConditioning;
    }

    @Override
    public String toString() {
        return "Car [Engine=" + engine + ", Wheels=" + wheels +
                ", Color=" + color + ", AC=" + airConditioning + "]";
    }

    public String getEngine() {
        return engine;
    }

    public int getWheels() {
        return wheels;
    }

    public String getColor() {
        return color;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    // --- The Static Inner Builder ---
    public static class Builder {
        private String engine;
        private int wheels;
        private String color;
        private boolean airConditioning;

        public Builder setEngine(String engine) {
            this.engine = engine;
            return this; // Fluent API: returns itself
        }

        public Builder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setAirConditioning(boolean airConditioning) {
            this.airConditioning = airConditioning;
            return this;
        }

        // The final assembly method
        public Car build() {
            return new Car(this);
        }
    }
}
