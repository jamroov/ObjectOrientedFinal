package com.company.Vehicles;

public class Motorcycle extends Vehicle {
    public Motorcycle(Double value, String make, Integer mileage, String colour, String segment) {
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        this.segment = segment;
    }

    public String toString() {
        return "Motorcycle, " + super.toString();
    }
}
