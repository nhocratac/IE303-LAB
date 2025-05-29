package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class ProductCardController {
    public AnchorPane cardRoot;
    @FXML
    private Label nameLabel, priceLabel,brandLabel,descriptionLabel;

    @FXML private ImageView imageView;
    private Product product;
    private MainController mainController;

    public void setData(Product product, MainController mainController) {
        this.product = product;
        this.mainController = mainController;
        nameLabel.setText(product.getName());

        descriptionLabel.setText(product.getDescription());
        priceLabel.setText("$" + product.getPrice());
        brandLabel.setText(product.getBrand());
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(product.getImagePath()))));
    }

    @FXML
    private void handleClick() throws IOException {
        mainController.setMainProduct(product);
    }
}
