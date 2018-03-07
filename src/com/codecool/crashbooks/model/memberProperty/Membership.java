package com.codecool.crashbooks.model.memberProperty;


public enum Membership {
    ADMIN(0, "Not message", 0),
    FREE(1, "Basic membership", 0),
    BRONZE(5, "Bronze membership", 2000),
    SILVER(10, "Silver membership", 5000),
    GOLD(15, "Gold membership", 9000);

    private final int limit;
    private final String message;
    private final int price;

    Membership(int limit, String message, int price) {
        this.limit = limit;
        this.message = message;
        this.price = price;
    }

    public int getLimit() {
        return limit;
    }

    public String getMessage() {
        return message;
    }

    public int getPrice() {
        return price;
    }
}
