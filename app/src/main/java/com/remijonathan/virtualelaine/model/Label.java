package com.remijonathan.virtualelaine.model;

import com.remijonathan.virtualelaine.data.DatabaseHelper;

import java.util.List;

public class Label {
    private int id;
    private String title;
    private int color;
    private String description;

    public Label(int id, String title, int color, String description) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
