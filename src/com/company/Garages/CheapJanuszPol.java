package com.company.Garages;

public class CheapJanuszPol extends Garage {
    public final Double riskOfTotaling = 0.02;
    public CheapJanuszPol(String typeOfGarage, Double riskOfFailure) {
        this.typeOfGarage = typeOfGarage;
        this.riskOfFailure = riskOfFailure;
    }
}
