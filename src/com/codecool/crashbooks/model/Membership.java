package com.codecool.crashbooks.model;

public enum  Membership {
    ADMIN(0),
    FREE(1),
    BRONZE(5),
    SILVER(10),
    GOLD(15);

    Membership(int limit) {
        this.limit = limit;
    }

    private final int limit;

    public int getLimit() {
        return limit;
    }
}
