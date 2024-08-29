package com.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.concurrent.CompletableFuture;

public class adminMaze extends Application {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {

        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();

        ImageView imageView = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setPickOnBounds(true);

        Button confirmChangeButton = new Button("确认修改");
        confirmChangeButton.setFont(new Font(20.0));
        confirmChangeButton.setId("ConfirmChangeButton");
        confirmChangeButton.setLayoutX(300.0);
        confirmChangeButton.setLayoutY(300.0);
        confirmChangeButton.setPrefHeight(86.0);
        confirmChangeButton.setPrefWidth(200.0);
        confirmChangeButton.setOnAction(e -> OnConfirmChange());

        Button backButton = new Button("返回");
        backButton.setFont(new Font(20.0));
        backButton.setLayoutX(300.0);
        backButton.setLayoutY(400.0);
        backButton.setPrefSize(200.0, 86.0);
        backButton.setOnAction(e -> {
            loginpage lgpage = new loginpage();
            try {
                lgpage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        textArea = new TextArea();
        textArea.setLayoutX(300.0);
        textArea.setLayoutY(150.0);
        textArea.setPrefHeight(118.0);
        textArea.setPrefWidth(200.0);

        Label label = new Label("修改参数");
        label.setLayoutX(150.0);
        label.setLayoutY(150.0);
        label.setPrefHeight(118.0);
        label.setPrefWidth(118.0);
        label.setStyle("-fx-font-size: 27px;");

        anchorPane.getChildren().addAll(imageView, confirmChangeButton, textArea, label, backButton);

        // Create VBox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(anchorPane);
        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);

        // Set Scene and Stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("迷宫管理界面");
        primaryStage.show();
    }

    private void OnConfirmChange() {
        String content = textArea.getText();

        if (content.isEmpty()) {
            showAlert("错误", "内容不能为空");
            return;
        }

        // Create JSON body
        String jsonBody = "{\"data\":\"" + content + "\"}";

        // Create the POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://119.45.239.3:8080/maze/1/update"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody))
                .build();

        // Send the request asynchronously
        CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Handle the response
        responseFuture.thenAccept(response -> {
            if (response.statusCode() == 200) {
                try {
                    JSONObject jsonResponse = new JSONObject(response.body());
                    int code = jsonResponse.getInt("code");
                    String message = jsonResponse.getString("message");

                    // 显示返回的 code 和 message
                    Platform.runLater(() -> showAlert("返回结果", "Code: " + code + "\nMessage: " + message));
                } catch (Exception e) {
                    Platform.runLater(() -> showAlert("解析错误", "无法解析服务器返回的响应"));
                }
            } else {
                Platform.runLater(() -> showAlert("失败", "修改失败，状态码：" + response.statusCode()));
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            Platform.runLater(() -> showAlert("错误", "请求失败"));
            return null;
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
