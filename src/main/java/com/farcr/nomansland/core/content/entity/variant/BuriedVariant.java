package com.farcr.nomansland.core.content.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BuriedVariant {
    ZERO(0),
    ONE(1);

    private static final BuriedVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(BuriedVariant::getId)).toArray(BuriedVariant[]::new);
    private final int id;

    BuriedVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BuriedVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

