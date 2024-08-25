package com.questionnaire.catalog.domain.entity;

public class Catalog {
    private int id;
    private String name;

    public Catalog(String name) {
        this.name = name;
    }

    
    public Catalog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
