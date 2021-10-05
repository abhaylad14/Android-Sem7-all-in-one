package com.example.activitylifecycledemo;

public class PlayerModel {
    String Id, Name, Country, City, ImageURL;

    public PlayerModel(String Id, String Name, String Country, String City, String imgURL)
    {
        this.Id = Id;
        this.Name = Name;
        this.Country = Country;
        this.City = City;
        this.ImageURL = imgURL;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
