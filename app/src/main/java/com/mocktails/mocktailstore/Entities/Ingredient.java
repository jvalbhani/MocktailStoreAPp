package com.mocktails.mocktailstore.Entities;
import java.io.Serializable;
import java.util.List;


public class Ingredient implements Serializable {

    private String name;
    private List<Mocktail> mocktail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
