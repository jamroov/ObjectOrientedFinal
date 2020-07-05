package com.company.Transactions;

import com.company.Player.Player;
import com.company.Vehicles.Vehicle;

public abstract class Transaction {
    public String type;
    public Boolean successful;
    public Double value;
    public Player player;
    public Vehicle vehicle;
    public String msg;

    public void setTransaction(Boolean successful, Double value, String msg) {
        this.successful = successful;
        this.value = value;
        this.msg = msg;
    }

    public String toString() {
        return String.format("Transaction type: %d. Was successful: %b. Value: %.2f. On vehicle:\n%s",
                this.type, this.successful, this.vehicle.toStringShort());
    }
}
