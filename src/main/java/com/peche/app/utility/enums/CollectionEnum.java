package com.peche.app.utility.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CollectionEnum {

    TOTTUS("TOTTUS"),
    METRO("METRO"),
    PLAZA_VEA("PLAZAVEA");

    private final String market;

    CollectionEnum(String market) {
        this.market = market;
    }

    public static boolean exists(String type) {
        return Arrays.stream(CollectionEnum.values())
                .anyMatch(market -> market.getMarket().equalsIgnoreCase(type));
    }
}
