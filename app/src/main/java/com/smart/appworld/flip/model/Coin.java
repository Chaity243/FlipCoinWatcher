package com.smart.appworld.flip.model;

import com.google.gson.annotations.SerializedName;

public class Coin {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coin(String id) {
        this.id = id;
    }

    public Coin(String id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }

    @SerializedName("id")
    private String id;
    @SerializedName("symbol")
    private String symbol;
    @SerializedName("name")
    private String name;





}