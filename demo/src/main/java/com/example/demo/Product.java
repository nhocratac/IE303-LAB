package com.example.demo;

public class Product {
    private String name;
    private double price;
    private String imagePath;
    private String description;
    private String brand;

    public Product(String name, double price, String brand, String description ,String imagePath) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.imagePath = imagePath;
    }


    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }
    public String getDescription() { return description; }
    public String getBrand() { return brand; }

    // Setter
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setDescription(String description) { this.description = description; }
    public void setBrand(String brand) { this.brand = brand; }
}
