package com.company.Vehicles;

import com.company.Components.Component;

public class Truck extends Vehicle {
    public Double load_capacity;

    public Truck(Integer id, Double value, String make, Integer mileage, String colour, String segment, Double load_capacity) {
        this.type = "Truck";
        this.id = id;
        this.value = value;
        this.make = make;
        this.mileage = mileage;
        this.colour = colour;
        this.load_capacity = load_capacity;
        this.segment = segment;
        this.load_capacity = load_capacity;
    }

    public String toString() {
        StringBuilder msg = new StringBuilder();
        msg.append(String.format("%s, ID: %d, Value: %.2f, brand: %s, mileage: %d, color: %s, segment: %s, load capacity: %.2f\n",
                this.type, this.id, this.value, this.make, this.mileage, this.colour, this.segment, this.load_capacity));
        for (Component comp : this.components) {
            msg.append(comp.toString()).append("\n");
        }
        return msg.toString();
    }
}
