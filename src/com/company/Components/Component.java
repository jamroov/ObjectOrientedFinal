package com.company.Components;

import com.company.Vehicles.Vehicle;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Component {
    public String type;
    public Boolean damaged;
    public Double priceIncrease;
    public Double health;

    public static void addComponents(Vehicle vehicle) {
        vehicle.components.add(new Body(ThreadLocalRandom.current().nextDouble(50.00, 100.00)));
        vehicle.components.add(new Brakes(ThreadLocalRandom.current().nextDouble(50.00, 100.00)));
        vehicle.components.add(new Dampers(ThreadLocalRandom.current().nextDouble(50.00, 100.00)));
        vehicle.components.add(new Engine(ThreadLocalRandom.current().nextDouble(50.00, 100.00)));
        vehicle.components.add(new Gearbox(ThreadLocalRandom.current().nextDouble(50.00, 100.00)));
    }

    public String toString() {
        return String.format("Component type: %s, damaged: %b, car value increased if fixed: %.2f/%%", this.type, this.damaged, this.priceIncrease * 100.00);
    }
}
