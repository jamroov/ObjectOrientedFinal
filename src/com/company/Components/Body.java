package com.company.Components;

public class Body extends Component {

    public Body(Double health) {
        this.health = health;
        this.priceIncrease = 0.50;
        this.damaged = health < 60.00;
        this.type = "Body";
    }
}
