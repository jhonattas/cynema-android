package br.com.patrocine.patrocine.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Snack implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("code")
    private int code;

    @SerializedName("preview")
    private String preview;

    @SerializedName("category")
    private String category;

    @SerializedName("title")
    private String title;

    @SerializedName("ingredients")
    private String ingredients;

    @SerializedName("price")
    private Double price;

    @SerializedName("status")
    private int status;

    @SerializedName("active")
    private boolean active;

    @SerializedName("available")
    private boolean available;

    public Snack(
            String id,
            int code,
            String preview,
            String category,
            String title,
            String ingredients,
            Double price,
            int status,
            boolean active,
            boolean available
    ){
        setId(id);
        setCode(code);
        setPreview(preview);
        setCategory(category);
        setTitle(title);
        setIngredients(ingredients);
        setPrice(price);
        setStatus(status);
        setActive(active);
        setAvailable(available);
    }

    public String getId(){ return id; }

    public void setId(String id) { this.id = id; }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview){
        this.preview = preview;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getIngredients(){
        return ingredients;
    }

    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
