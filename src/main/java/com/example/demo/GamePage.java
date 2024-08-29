package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class GamePage extends Application {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox();
        vbox.setPrefSize(800, 600);

        // AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(800, 600);

        ImageView imageView = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        imageView.setFitHeight(600);
        imageView.setFitWidth(800);

        Button randomButton = new Button("随机关卡");
        randomButton.setFont(new Font(24));
        randomButton.setPrefSize(131, 98);
        randomButton.setLayoutX(50);
        randomButton.setLayoutY(292);
        randomButton.setOnAction(e -> {
            page3 p3 = new page3();
            System.out.println("Choice Button Pressed");
            sendRandomLevelRequest();
        });

        Button choiceButton = new Button("选择关卡");
        choiceButton.setFont(new Font(24));
        choiceButton.setPrefSize(131, 98);
        choiceButton.setLayoutX(300);
        choiceButton.setLayoutY(297);
        choiceButton.setOnAction(e -> {
            page3 p3 = new page3();
            try {
                p3.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        Button costumeButton = new Button("自定义");
        costumeButton.setFont(new Font(24));
        costumeButton.setPrefSize(131, 98);
        costumeButton.setLayoutX(550);
        costumeButton.setLayoutY(297);
        costumeButton.setOnAction(e -> System.out.println("Costume Button Pressed"));

        Button backButton = new Button("返回");
        backButton.setFont(new Font(24));
        backButton.setPrefSize(131, 80);
        backButton.setLayoutX(300);
        backButton.setLayoutY(450);
        backButton.setOnAction(e -> {
            page1 mainPage = new page1();
            try {
                mainPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ImageView logoImageView = new ImageView(getClass().getResource("/com/example/demo/picture/titlepicture.png").toExternalForm());
        logoImageView.setFitHeight(168);
        logoImageView.setFitWidth(404);
        logoImageView.setLayoutX(180);
        logoImageView.setLayoutY(43);

        anchorPane.getChildren().addAll(imageView, randomButton, choiceButton, costumeButton, backButton, logoImageView);

        vbox.getChildren().addAll(anchorPane);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("迷宫游戏");
        primaryStage.show();
    }

    private void sendRandomLevelRequest() {
        // Define the request body (if necessary)
        String jsonBody = "{\"level\":\"random\"}";

        // Create the POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://119.45.239.3:8080/api/level1"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody))
                .build();

        // Send the request asynchronously
        CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, BodyHandlers.ofString());

        // Handle the response
        responseFuture.thenAccept(response -> {
            if (response.statusCode() == 200) {
                System.out.println("Request successful: " + response.body());
            } else {
                System.out.println("Request failed: " + response.statusCode());
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
