package com.example.anthonybryanmpagarigan.ph_ojt2.Model;

public class User {
    private String id;
    private String fullname;
    private String password;
    private String email;
    private Boolean isAdmin;

    public User() {
    }

    public User(String id, String fullname, String password, String email, Boolean isAdmin) {
        this.id = id;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getisAdmin() {
        return isAdmin;
    }

    public void setisAdmin(Boolean isAdmin) {
        isAdmin = isAdmin;
    }
}
