package com.company.Vehicles;

public class Truck extends Vehicle {
    public Double load_capacity;

    public Truck(Double value, String make, Integer mileage, String colour, String segment, Double load_capacity) {
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        this.load_capacity = load_capacity;
        this.segment = segment;
        this.load_capacity = load_capacity;
    }

    public String toString() {
        return "Truck, " + super.toString() + String.format(", load capacity: %.2f", this.load_capacity);
    }
}
