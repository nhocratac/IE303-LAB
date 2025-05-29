package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class MainProductDisplayController {

    public VBox root;
    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label brandLabel, descriptionLabel;

    public void setProduct(Product product) {
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(product.getImagePath())));
        imageView.setImage(img);

        nameLabel.setText(product.getName());
        priceLabel.setText("$" + product.getPrice());
        brandLabel.setText(product.getBrand()); // Nếu Product có brand
        descriptionLabel.setText(product.getDescription());

    }
}
