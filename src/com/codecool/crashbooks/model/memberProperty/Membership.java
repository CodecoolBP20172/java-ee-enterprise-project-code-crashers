package com.codecool.crashbooks.model.memberProperty;


public enum Membership {
    ADMIN(0, "Not message", 0, "korona.png"),
    FREE(1, "Basic membership", 0, "korona.png"),
    BRONZE(5, "Bronze membership", 2000, "korona.png"),
    SILVER(10, "Silver membership", 5000, "korona.png"),
    GOLD(15, "Gold membership", 9000, "korona.png");

    private final int limit;
    private final String message;
    private final int price;
    private final String imgURl;

    Membership(int limit, String message, int price, String imgURL) {
        this.limit = limit;
        this.message = message;
        this.price = price;
        this.imgURl = imgURL;
    }

    public String getImgURL() {
        return imgURl;
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
