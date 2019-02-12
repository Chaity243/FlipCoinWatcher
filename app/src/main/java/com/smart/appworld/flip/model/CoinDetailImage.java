package com.smart.appworld.flip.model;

import com.google.gson.annotations.SerializedName;

public class CoinDetailImage {
    @SerializedName("thumb")
    private String thumb ;

    @SerializedName("small")
    private String small ;

    public CoinDetailImage(String thumb, String small, String large) {
        this.thumb = thumb;
        this.small = small;
        this.large = large;
    }

    @SerializedName("large")
    private String large ;


    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


}
