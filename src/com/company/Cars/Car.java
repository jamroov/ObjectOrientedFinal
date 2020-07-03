package com.company.Cars;

public class Car extends Vehicle {
    private static String segment;

    public Car(Double value, String make, Integer mileage, String colour, String segment) {
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        if (this.value > 100000.00) {
            setSegment("premium");
        }
        else if (this.value > 35000.00 && this.value <= 100000.00) {
            setSegment("standard");
        }
        else {
            setSegment("budget");
        }
    }

    private static void setSegment(String segment) {
        Car.segment = segment;
    }
}
