package com.company.Cars;

public class Car extends Vehicle {
    private static String segment;

    public Car(Double value, String make, Integer mileage, String colour, String segment) {
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        setSegment(segment);
    }

    private static void setSegment(String segment) {
        Car.segment = segment;
    }
}
