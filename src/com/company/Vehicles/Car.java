package com.company.Vehicles;

public class Car extends Vehicle {
    public Car(Double value, String make, Integer mileage, String colour, String segment) {
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        this.segment = segment;
    }

    public String toString() {
        return "Car, " + super.toString();
    }
}
