package com.company.Advert;

import java.util.concurrent.ThreadLocalRandom;

public class LocalPaperAd extends Advert {
    public LocalPaperAd() {
        this.CustomerIncrease = ThreadLocalRandom.current().nextInt(1,6);
        this.price = 5000.00;
        this.type = "Local Paper Ad";
    }
}
