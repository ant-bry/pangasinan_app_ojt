package com.example.anthonybryanmpagarigan.ph_ojt2.Model;

public class GalleryFoods {
    private String foods_name;
    private String foods_description;
    private String foods_image;

    public GalleryFoods() {
    }

    public GalleryFoods(String foods_name, String foods_description, String foods_image) {
        this.foods_name = foods_name;
        this.foods_description = foods_description;
        this.foods_image = foods_image;
    }

    public String getFoods_name() {
        return foods_name;
    }

    public void setFoods_name(String foods_name) {
        this.foods_name = foods_name;
    }

    public String getFoods_description() {
        return foods_description;
    }

    public void setFoods_description(String foods_description) {
        this.foods_description = foods_description;
    }

    public String getFoods_image() {
        return foods_image;
    }

    public void setFoods_image(String foods_image) {
        this.foods_image = foods_image;
    }
}
