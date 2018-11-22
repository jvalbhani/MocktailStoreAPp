package com.mocktails.mocktailstore.Entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jayesh
 */
public class Mocktail implements Serializable {

    private long id;
    private String name;
    private String tagLine;
    private String description;
    private Boolean visible;
    private List<Rating> ratings;
    private String imageUrl;
    private List<Ingredient> ingredients;
    private String recipe;
    private int price;

    public Mocktail() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getVisible() {
        return visible;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Mocktail [name=").append(name).append(", tagLine=")
                .append(tagLine).append(", description=").append(description).append(", visible=").append(visible)
                .append(", ratings=").append(ratings)
                .append(", imageUrl=").append(imageUrl).append(", ingredients=").append(ingredients).append(", recipe=")
                .append(recipe).append(", price=").append(price).append("]");
        return builder.toString();
    }

}
