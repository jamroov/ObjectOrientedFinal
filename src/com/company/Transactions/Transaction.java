package com.company.Transactions;

import com.company.Advert.Advert;
import com.company.Player.Player;
import com.company.Vehicles.Vehicle;

public abstract class Transaction {
    public String type;
    public Boolean successful;
    public Double value;
    public Player player;
    public Vehicle vehicle;
    public String msg;
    public Advert ad;

    public void setTransaction(Boolean successful, Double value, String msg) {
        this.successful = successful;
        this.value = value;
        this.msg = msg;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Transaction type: %s. Was successful: %b. Value: %.2f. For:\n", this.type, this.successful, this.value));
        if (this.vehicle != null) {
            str.append(this.vehicle.toStringShort());
        }
        if (this.ad != null) {
            str.append(this.ad.toString());
        }
        return str.toString();
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setAd(Advert ad) {
        this.ad = ad;
    }
}
