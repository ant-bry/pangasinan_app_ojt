package com.example.anthonybryanmpagarigan.ph_ojt2.Model;

public class GalleryFalls {

    private String falls_name;
    private String falls_description;
    private String falls_image;

    public GalleryFalls() {
    }

    public GalleryFalls(String falls_name, String falls_description, String falls_image) {
        this.falls_name = falls_name;
        this.falls_description = falls_description;
        this.falls_image = falls_image;
    }

    public String getFalls_name() {
        return falls_name;
    }

    public void setFalls_name(String falls_name) {
        this.falls_name = falls_name;
    }

    public String getFalls_description() {
        return falls_description;
    }

    public void setFalls_description(String falls_description) {
        this.falls_description = falls_description;
    }

    public String getFalls_image() {
        return falls_image;
    }

    public void setFalls_image(String falls_image) {
        this.falls_image = falls_image;
    }
}
