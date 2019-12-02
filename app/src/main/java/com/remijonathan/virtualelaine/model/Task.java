package com.remijonathan.virtualelaine.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Task {
    private int id;
    private String title;
    private String label;
    private Calendar dueDate = Calendar.getInstance();
    private String description;
    private boolean isActive;

    public Task(int id, String title, @Nullable String dueDate, @Nullable String label,  @Nullable String description, boolean isActive){
        this.id = id;
        this.title = title;
        this.label = label;
        this.description = description;
        this.isActive = isActive;


        if (dueDate!=null) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
                this.dueDate.setTime(simpleDateFormat.parse(dueDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
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

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }
}
