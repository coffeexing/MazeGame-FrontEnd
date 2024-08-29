package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class page3 extends Application {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void start(Stage primaryStage) {
        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create Buttons
        Button level1Button = new Button("关卡1");
        level1Button.setFont(new Font(24));
        level1Button.setPrefSize(131, 98);
        level1Button.setLayoutX(50);
        level1Button.setLayoutY(292);
        level1Button.setOnAction(e -> OnLevel1());

        Button level2Button = new Button("关卡2");
        level2Button.setFont(new Font(24));
        level2Button.setPrefSize(131, 98);
        level2Button.setLayoutX(300);
        level2Button.setLayoutY(297);
        level2Button.setOnAction(e -> OnLevel2());

        Button backButton2 = new Button("返回");
        backButton2.setFont(new Font(24));
        backButton2.setPrefSize(131, 80);
        backButton2.setLayoutX(300);
        backButton2.setLayoutY(450);
        backButton2.setOnAction(e -> OnBack2());
        backButton2.setOnAction(e -> {
            page1 mainPage = new page1();
            try {
                mainPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button level3Button = new Button("关卡3");
        level3Button.setFont(new Font(24));
        level3Button.setPrefSize(131, 98);
        level3Button.setLayoutX(550);
        level3Button.setLayoutY(297);
        level3Button.setOnAction(e -> OnLevel3());

        // Create Images
        ImageView imageView1 = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        imageView1.setFitWidth(800);
        imageView1.setFitHeight(600);
        imageView1.setPickOnBounds(true);

        ImageView imageView2 = new ImageView(getClass().getResource("/com/example/demo/picture/titlepicture.png").toExternalForm());
        imageView2.setFitWidth(404);
        imageView2.setFitHeight(168);
        imageView2.setLayoutX(180);
        imageView2.setLayoutY(46);
        imageView2.setPickOnBounds(true);

        // Create AnchorPane and add children
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView1, level1Button, level2Button, backButton2, level3Button, imageView2);

        // Create VBox
        VBox vbox = new VBox(menuBar, anchorPane);
        vbox.setPrefSize(640, 400);

        // Create Scene
        Scene scene = new Scene(vbox, 800, 600);

        // Set Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("迷宫游戏");
        primaryStage.show();
    }

    // Event handlers
    private void OnLevel1() {
        sendPostRequest("http://119.45.239.3:8080/api/level1");
    }

    private void OnLevel2() {
        sendPostRequest("http://119.45.239.3:8080/api/level1");
    }

    private void OnBack2() {
        // Handle Back button action
    }

    private void OnLevel3() {
        sendPostRequest("http://119.45.239.3:8080/api/level1");
    }

    private void sendPostRequest(String url) {




    }

    public static void main(String[] args) {
        launch(args);
    }
}
