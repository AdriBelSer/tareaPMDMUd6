package com.yinya.pmdm06;

import com.google.android.gms.maps.model.LatLng;

public class Station {
    int id;
    LatLng position;
    String name;
    String snippet;
    String title;
    String description;
    String password;
    int icon;
    int image;

    public Station(int id, LatLng position, String name, String snippet, String title, String description, String password, int icon, int image) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.snippet = snippet;
        this.title = title;
        this.description = description;
        this.password = password;
        this.icon = icon;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
