package com.company.Garages;

import com.company.Components.Body;

public class CheapJanuszPol extends Garage {
    public static final Integer riskOfTotaling = 50;
    public CheapJanuszPol(String typeOfGarage, Integer riskOfFailure) {
        this.typeOfGarage = typeOfGarage;
        this.riskOfFailure = riskOfFailure;
        this.priceMap.put("Body", 8000.00);
        this.priceMap.put("Brakes", 1000.00);
        this.priceMap.put("Dampers", 2000.00);
        this.priceMap.put("Engine", 9000.00);
        this.priceMap.put("Gearbox", 5000.00);
    }
}
