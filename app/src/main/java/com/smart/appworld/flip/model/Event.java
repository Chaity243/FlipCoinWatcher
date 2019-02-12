package com.smart.appworld.flip.model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("end_date")
    private String end_date;
    @SerializedName("venue")
    private String venue;
    @SerializedName("country")
    private String country;
    @SerializedName("website")
    private String website;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("description")
    private String description;
    @SerializedName("screenshot")
    private String screenshot;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("organizer")
    private String organizer;
    @SerializedName("email")
    private String email;
    @SerializedName("start_date")
    private String start_date;

    public Event(String end_date, String venue, String country, String website, String address, String city, String description, String screenshot, String type, String title, String organizer, String email, String start_date) {
        this.end_date = end_date;
        this.venue = venue;
        this.country = country;
        this.website = website;
        this.address = address;
        this.city = city;
        this.description = description;
        this.screenshot = screenshot;
        this.type = type;
        this.title = title;
        this.organizer = organizer;
        this.email = email;
        this.start_date = start_date;
    }

    public String getEnd_date ()
    {
        return end_date;
    }

    public void setEnd_date (String end_date)
    {
        this.end_date = end_date;
    }

    public String getVenue ()
    {
        return venue;
    }

    public void setVenue (String venue)
    {
        this.venue = venue;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getWebsite ()
    {
        return website;
    }

    public void setWebsite (String website)
    {
        this.website = website;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getScreenshot ()
    {
        return screenshot;
    }

    public void setScreenshot (String screenshot)
    {
        this.screenshot = screenshot;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getOrganizer ()
    {
        return organizer;
    }

    public void setOrganizer (String organizer)
    {
        this.organizer = organizer;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getStart_date ()
    {
        return start_date;
    }

    public void setStart_date (String start_date)
    {
        this.start_date = start_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [end_date = "+end_date+", venue = "+venue+", country = "+country+", website = "+website+", address = "+address+", city = "+city+", description = "+description+", screenshot = "+screenshot+", type = "+type+", title = "+title+", organizer = "+organizer+", email = "+email+", start_date = "+start_date+"]";
    }
}

