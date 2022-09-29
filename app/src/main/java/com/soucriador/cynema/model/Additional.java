package com.soucriador.cynema.model;

import com.google.gson.annotations.SerializedName;

public class Additional {

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    @SerializedName("category")
    private String category;

    @SerializedName("status")
    private int status;

    @SerializedName("active")
    private boolean active;

    public Additional(
            String name,
            double price,
            String category,
            int status,
            boolean active
    ){
        setName(name);
        setPrice(price);
        setCategory(category);
        setStatus(status);
        setActive(active);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
