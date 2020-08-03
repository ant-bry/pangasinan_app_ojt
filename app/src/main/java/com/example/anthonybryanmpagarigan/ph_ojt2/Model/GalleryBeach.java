package com.example.anthonybryanmpagarigan.ph_ojt2.Model;

public class GalleryBeach {

    private String beach_name;
    private String beach_description;
    private String beach_image;

    public GalleryBeach() {
    }

    public GalleryBeach(String beach_name, String beach_description, String beach_image) {
        this.beach_name = beach_name;
        this.beach_description = beach_description;
        this.beach_image = beach_image;
    }

    public String getBeach_name() {
        return beach_name;
    }

    public void setBeach_name(String beach_name) {
        this.beach_name = beach_name;
    }

    public String getBeach_description() {
        return beach_description;
    }

    public void setBeach_description(String beach_description) {
        this.beach_description = beach_description;
    }

    public String getBeach_image() {
        return beach_image;
    }

    public void setBeach_image(String beach_image) {
        this.beach_image = beach_image;
    }
}
