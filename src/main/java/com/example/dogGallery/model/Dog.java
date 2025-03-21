package com.example.dogGallery.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String breedType;
    private String color;
    private double age;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date activeDate = new Date();

    public Dog() {}

    public Dog(String name, String description, String breed, String color, double age, Date activeDate) {
        this.name = name;
        this.description = description;
        this.breedType = breed;
	this.color = color;
        this.age = age;
        this.activeDate = activeDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBreedType() { return breedType; }
    public void setBreedType(String breed) { this.breedType = breed; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getAge() { return age; }
    public void setAge(double age) { this.age = age; }

    public Date getActiveDate() { return activeDate; }
    public void setActiveDate(Date activeDate) { this.activeDate = activeDate; }
}