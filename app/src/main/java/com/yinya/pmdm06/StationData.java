package com.yinya.pmdm06;

public class StationData {
    private int image;
    private String title;
    private String description;
    private String password;


    public StationData(int image, String title, String description, String password) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.password = password;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
