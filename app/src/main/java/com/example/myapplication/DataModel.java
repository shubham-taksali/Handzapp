package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("tasks")
    @Expose
    private String tasks;
    @SerializedName("image")
    @Expose
    private String image;

    private boolean isSelected;

    /**
     * No args constructor for use in serialization
     */
    public DataModel() {
    }

    /**
     * @param name
     * @param image
     * @param number
     * @param tasks
     * @param rating
     */
    public DataModel(Integer number, String name, String rating, String tasks, String image) {
        super();
        this.number = number;
        this.name = name;
        this.rating = rating;
        this.tasks = tasks;
        this.image = image;
        this.isSelected = false;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}