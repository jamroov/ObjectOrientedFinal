package com.company.Advert;

import com.company.Player.Player;
import com.company.Transactions.Buy;
import com.company.Transactions.Transaction;

public abstract class Advert {
    public String type;
    public Double price;
    public Integer CustomerIncrease;

    public Boolean buyAnAd(Player player) {
        Transaction thisTransaction = new Buy(player);
        thisTransaction.setAd(this);
        if (this.price > player.getMoney()) {
            System.out.println(player.getName() + " not enough money.");
            return false;
        }
        player.subtractMoney(this.price);
        player.addTransaction(thisTransaction);
        return true;
    }
    public String toString() {
        return String.format("%s, price: %.2f, affected customers: %d", this.type, this.price, this.CustomerIncrease);
    }
}
