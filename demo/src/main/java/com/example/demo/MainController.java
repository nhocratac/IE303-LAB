package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private VBox mainProductDisplay;
    @FXML private FlowPane productList;

    public void initialize() throws IOException {
        List<Product> products = List.of(
                new Product("ULTRABOOST 22", 180.0, "Adidas", "Responsive running shoes with a snug fit for comfort.", "/images/img1.png"),
                new Product("NMD_R1 V2", 140.0, "Adidas", "Street-style sneakers with bold design and all-day comfort.", "/images/img2.png"),
                new Product("FORUM LOW", 110.0, "Adidas", "Retro basketball-inspired shoes reimagined for everyday wear.", "/images/img3.png"),
                new Product("OZWEEGO", 130.0, "Adidas", "Chunky sneakers with a futuristic vibe and soft cushioning.", "/images/img4.png"),
                new Product("GAZELLE", 95.0, "Adidas", "Classic low-profile suede shoes with iconic 3-Stripes design.", "/images/img5.png"),
                new Product("TERREX FREE HIKER", 200.0, "Adidas", "Durable hiking shoes made for performance on rough terrain.", "/images/img6.png"),
                new Product("ULTRABOOST 22", 180.0, "Adidas", "Responsive running shoes with a snug fit for comfort.", "/images/img1.png"),
                new Product("NMD_R1 V2", 140.0, "Adidas", "Street-style sneakers with bold design and all-day comfort.", "/images/img2.png")
        );


        for (Product product : products) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("product-card.fxml"));
                AnchorPane card = loader.load();
                ProductCardController controller = loader.getController();
                controller.setData(product, this);
                productList.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Hiển thị mặc định sản phẩm đầu tiên
        setMainProduct(products.getFirst());
    }

    public void setMainProduct(Product product) throws IOException {
        mainProductDisplay.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainProductDisplay.fxml"));
        VBox mainProductNode = loader.load();
        MainProductDisplayController mainProductDisplayController = loader.getController();

        mainProductDisplayController.setProduct(product);

        mainProductDisplay.getChildren().add(mainProductNode);
        FadeTransition ft = new FadeTransition(Duration.millis(800), mainProductNode );
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
