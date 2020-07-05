package com.company.Vehicles;

public class Car extends Vehicle {
    public Car(Integer id, Double value, String make, Integer mileage, String colour, String segment) {
        this.id = id;
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        this.segment = segment;
        this.type = "Car";
    }

    public String toString() {
        return super.toString();
    }
}
