package com.questionnaire.survey.domain.entity;

public class Survey {
    private int id;
    private String description;
    private String name;

    public Survey(int id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Survey{" +
               "id=" + id +
               ", description='" + description + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}
