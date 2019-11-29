package com.remijonathan.virtualelaine.model;

import androidx.annotation.Nullable;

public class Task {
    int id;
    String title;
    String label;
    String description;
    boolean isActive;

    public Task(int id, String title, @Nullable String label, @Nullable String description, boolean isActive) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.description = description;
        this.isActive = isActive;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
