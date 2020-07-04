package com.company.Garages;

public class MediumMarianCars extends Garage {
    public MediumMarianCars(String typeOfGarage, Integer riskOfFailure) {
        this.typeOfGarage = typeOfGarage;
        this.riskOfFailure = riskOfFailure;
        this.priceMap.put("Body", 10000.00);
        this.priceMap.put("Brakes", 2000.00);
        this.priceMap.put("Dampers", 3000.00);
        this.priceMap.put("Engine", 10000.00);
        this.priceMap.put("Gearbox", 6000.00);
    }
}
