package com.company.Components;

public class Dampers extends Component{
    public Dampers(Double health) {
        this.priceIncrease = 0.20;
        this.health = health;
        this.damaged = health < 60.00;
        this.type = "Dampers";
    }
}
