package com.farcr.nomansland.common.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BuriedVariant {

    ZERO(0),
    ONE(1),
    TWO(2);

    private static final BuriedVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BuriedVariant::getId)).toArray(BuriedVariant[]::new);
    private final int id;

    BuriedVariant(int id) {
        this.id = id;
    }

    public static BuriedVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public int getId() {
        return this.id;
    }
}