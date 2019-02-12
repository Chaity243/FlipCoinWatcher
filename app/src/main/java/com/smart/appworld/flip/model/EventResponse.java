package com.smart.appworld.flip.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {
    @SerializedName("count")
    private String count;
    @SerializedName("page")
    private String page;
    @SerializedName("data")
    private List<Event> data;

    public List<Event> getData() {
        return data;
    }

    public void setData(List<Event> data) {
        this.data = data;
    }



    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }


    public EventResponse(String count, String page, List<Event> events) {
        this.count = count;
        this.page = page;
        this.data = events;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +", count = "+count+", page = "+page+"]";
    }
}

