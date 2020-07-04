package com.company.Garages;

import com.company.Components.Component;
import com.company.Player.Player;
import com.company.Vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Garage {
    public String typeOfGarage; //Either cheap, medium or expensive
    public Integer riskOfFailure; //0, 10%, 20%

    Map<String, Double> priceMap = new HashMap<String, Double>();

    public void fixThis(Component component, Vehicle vehicle, Player player) {
        Boolean fixFail = false;
        Boolean carTotalled = false;
        if (!vehicle.components.contains(component)) {
            System.out.println("This is not this vehicle's component");
            return;
        }
        if (!component.damaged) {
            System.out.println(component.type + " is not damaged. No repairs needed.");
            return;
        }
        Double price = this.priceMap.get(component.type);
        if (player.getMoney() < price) {
            System.out.println(player.getName() + " not enough money for fix");
            return;
        }
        //Calculate risk of failure
        if (ThreadLocalRandom.current().nextInt(this.riskOfFailure) == 1) {
            fixFail = true;
            System.out.println("That was not supposed to happen...");
        }
        if (this.getClass() == CheapJanuszPol.class) {
            carTotalled = ThreadLocalRandom.current().nextInt(CheapJanuszPol.riskOfTotaling) == 1;
            System.out.println("Oh shit...");
        }
        if (carTotalled) {
            System.out.println("Sorry, your car is ruined.");
            Vehicle.totalThisVehicle(vehicle);
            return;
        }
        if (fixFail) {
            System.out.println("Sorry, we failed to fix it. But you still pay");
            player.subtractMoney(price);
            return;
        }
        System.out.println("We will fix that for you.");
        player.subtractMoney(price);
        component.damaged = false;
        component.health = 100.00;
        vehicle.value += vehicle.value * component.priceIncrease;
    }
}
