package com.company.Components;

public class Brakes extends Component {

    public Brakes(Double health) {
        this.priceIncrease = 0.10;
        this.health = health;
        this.damaged = health < 60.00;
        this.type = "Brakes";
    }
}
