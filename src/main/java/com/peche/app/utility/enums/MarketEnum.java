package com.peche.app.utility.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MarketEnum {

    TOTTUS("TOTTUS"),
    METRO("METRO"),
    PLAZA_VEA("PLAZAVEA");

    private final String market;

    MarketEnum(String market) {
        this.market = market;
    }

    public static boolean exists(String type) {
        return Arrays.stream(MarketEnum.values())
                .anyMatch(market -> market.getMarket().equalsIgnoreCase(type));
    }
}
