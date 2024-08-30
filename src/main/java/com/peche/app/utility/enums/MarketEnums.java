package com.peche.app.utility.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MarketEnums {

    TOTTUS("Tottus"),
    METRO("Metro"),
    PLAZA_VEA("Plaza Vea");

    private final String market;

    MarketEnums(String market) {
        this.market = market;
    }

    public static boolean exists(String type) {
        return Arrays.stream(MarketEnums.values())
                .anyMatch(market -> market.getMarket().equalsIgnoreCase(type));
    }
}
