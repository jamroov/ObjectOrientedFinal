package com.company.Customer;

public class Customer {
    public String name;
    public Double cash;
    public String wantedType;
    public Boolean willBuyDamaged;

    public Customer(String name, Double cash, String wantedType, Boolean willBuyDamaged) {
        this.name = name;
        this.cash = cash;
        this.wantedType = wantedType;
        this.willBuyDamaged = willBuyDamaged;
    }
}
