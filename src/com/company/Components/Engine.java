package com.company.Components;

public class Engine extends Component {
    public Engine(Double health) {
        this.priceIncrease = 1.0;
        this.health = health;
        this.damaged = health < 60.00;
        this.type = "Engine";
    }
}
