package com.example.anthonybryanmpagarigan.ph_ojt2.Model;

public class DirectoryGovernmentOfficials {

    private String image;
    private String position;
    private String name;
    private String description;
    private String place;

    public DirectoryGovernmentOfficials(String image, String position, String name, String description, String place) {
        this.image = image;
        this.position = position;
        this.name = name;
        this.description = description;
        this.place = place;
    }

    public DirectoryGovernmentOfficials() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
