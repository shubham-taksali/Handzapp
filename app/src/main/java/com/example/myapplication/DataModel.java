package com.example.myapplication;

public class DataModel {

    private int drawable;
    private String personName;
    private String personRating;
    private String personTask;
    private int ribbonColor;


    public DataModel(int drawable, String personName, String personRating, String personTask, int ribbonColor) {
        this.drawable = drawable;
        this.personName = personName;
        this.personRating = personRating;
        this.personTask = personTask;
        this.ribbonColor = ribbonColor;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonRating() {
        return personRating;
    }

    public void setPersonRating(String personRating) {
        this.personRating = personRating;
    }

    public String getPersonTask() {
        return personTask;
    }

    public void setPersonTask(String personTask) {
        this.personTask = personTask;
    }

    public int getRibbonColor() {
        return ribbonColor;
    }

    public void setRibbonColor(int ribbonColor) {
        this.ribbonColor = ribbonColor;
    }
}