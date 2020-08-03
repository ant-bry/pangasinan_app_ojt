package com.example.anthonybryanmpagarigan.ph_ojt2.Model;

public class GalleryChurch {

    private String church_name;
    private String church_description;
    private String church_image;

    public GalleryChurch() {
    }

    public GalleryChurch(String church_name, String church_description, String church_image) {
        this.church_name = church_name;
        this.church_description = church_description;
        this.church_image = church_image;
    }

    public String getChurch_name() {
        return church_name;
    }

    public void setChurch_name(String church_name) {
        this.church_name = church_name;
    }

    public String getChurch_description() {
        return church_description;
    }

    public void setChurch_description(String church_description) {
        this.church_description = church_description;
    }

    public String getChurch_image() {
        return church_image;
    }

    public void setChurch_image(String church_image) {
        this.church_image = church_image;
    }
}
