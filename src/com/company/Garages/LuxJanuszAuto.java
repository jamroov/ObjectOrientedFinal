package com.company.Garages;

public class LuxJanuszAuto extends Garage {
    public LuxJanuszAuto(String typeOfGarage, Integer riskOfFailure) {
        this.typeOfGarage = typeOfGarage;
        this.riskOfFailure = riskOfFailure;

        this.priceMap.put("Body", 13000.00);
        this.priceMap.put("Brakes", 5000.00);
        this.priceMap.put("Dampers", 6000.00);
        this.priceMap.put("Engine", 13000.00);
        this.priceMap.put("Gearbox", 9000.00);
    }
}
