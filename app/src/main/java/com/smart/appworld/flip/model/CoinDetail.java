package com.smart.appworld.flip.model;

import com.google.gson.annotations.SerializedName;

public class CoinDetail {
    @SerializedName("id")
    private String id;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("name")
    private String name;

    @SerializedName("market_cap_rank")
    private String market_cap_rank ;

    @SerializedName("coingecko_rank")
    private String coingecko_rank ;

    @SerializedName("coingecko_score")
    private String coingecko_score ;

    @SerializedName("developer_score")
    private String developer_score ;

    @SerializedName("community_score")
    private String community_score ;

    @SerializedName("liquidity_score")
    private String liquidity_score ;

    public CoinDetail(String id, String symbol, String name, String market_cap_rank, String coingecko_rank, String coingecko_score, String developer_score, String community_score, String liquidity_score, String public_interest_score, String last_updated, CoinDetailImage image) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.market_cap_rank = market_cap_rank;
        this.coingecko_rank = coingecko_rank;
        this.coingecko_score = coingecko_score;
        this.developer_score = developer_score;
        this.community_score = community_score;
        this.liquidity_score = liquidity_score;
        this.public_interest_score = public_interest_score;
        this.last_updated = last_updated;
        this.image = image;
    }

    @SerializedName("public_interest_score")
    private String public_interest_score ;

    @SerializedName("last_updated")
    private String last_updated;

    @SerializedName("image")
    private  CoinDetailImage image;



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

    public String getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(String market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public String getCoingecko_rank() {
        return coingecko_rank;
    }

    public void setCoingecko_rank(String coingecko_rank) {
        this.coingecko_rank = coingecko_rank;
    }

    public String getCoingecko_score() {
        return coingecko_score;
    }

    public void setCoingecko_score(String coingecko_score) {
        this.coingecko_score = coingecko_score;
    }

    public String getDeveloper_score() {
        return developer_score;
    }

    public void setDeveloper_score(String developer_score) {
        this.developer_score = developer_score;
    }

    public String getCommunity_score() {
        return community_score;
    }

    public void setCommunity_score(String community_score) {
        this.community_score = community_score;
    }

    public String getLiquidity_score() {
        return liquidity_score;
    }

    public void setLiquidity_score(String liquidity_score) {
        this.liquidity_score = liquidity_score;
    }

    public String getPublic_interest_score() {
        return public_interest_score;
    }

    public void setPublic_interest_score(String public_interest_score) {
        this.public_interest_score = public_interest_score;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public CoinDetailImage getImage() {
        return image;
    }

    public void setImage(CoinDetailImage image) {
        this.image = image;
    }




    @Override
    public String toString()
    {
        return "ClassPojo [ symbol = "+symbol+", image = "+image+", last_updated = "+last_updated+", public_interest_score = "+public_interest_score+", market_cap_rank = "+market_cap_rank+", community_score = "+community_score+", developer_score = "+developer_score+", liquidity_score = "+liquidity_score+", coingecko_score = "+coingecko_score+", name = "+name+", coingecko_rank = "+coingecko_rank+",  id = "+id+"]";
    }
}

