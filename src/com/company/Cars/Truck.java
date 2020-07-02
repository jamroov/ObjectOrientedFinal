package com.company.Cars;

public class Truck extends Vehicle {
    private static String segment;
    public Double load_capacity;

    public Truck(Double value, String make, Integer mileage, String colour, String segment, Double load_capacity) {
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        this.load_capacity = load_capacity;
    }

    private static void setSegment(String segment) {
        Truck.segment = segment;
    }
}
