package com.example.lab4.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shoes")
public class Shoe {
    @Id
    private String id;

    private String name;

    private int price;

    private String brand;

    private String description;

    private String imageUrl;

    public Shoe(String name, int price, String brand, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
