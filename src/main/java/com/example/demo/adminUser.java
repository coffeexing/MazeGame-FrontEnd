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

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.concurrent.CompletableFuture;

public class adminUser extends Application {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    private TextField textField;
    private ComboBox<String> comboBox;

    @Override
    public void start(Stage primaryStage) {

        // Create ImageView with background image
        ImageView imageView = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);

        // Create labels
        Label userLabel = new Label("目标用户");
        userLabel.setFont(new Font(18.0));
        userLabel.setLayoutX(250.0);
        userLabel.setLayoutY(168.0);

        Label actionLabel = new Label("操作");
        actionLabel.setFont(new Font(18.0));
        actionLabel.setLayoutX(250.0);
        actionLabel.setLayoutY(220.0);

        // Create text field and combo box
        textField = new TextField();
        textField.setLayoutX(350.0);
        textField.setLayoutY(169.0);
        textField.setPrefWidth(161.0);

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("封禁", "解禁");
        comboBox.setLayoutX(350.0);
        comboBox.setLayoutY(220.0);
        comboBox.setPrefWidth(161.0);

        // Create buttons
        Button confirmButton = new Button("确认");
        confirmButton.setFont(new Font(20.0));
        confirmButton.setLayoutX(300.0);
        confirmButton.setLayoutY(293.0);
        confirmButton.setPrefSize(69.0, 59.0);
        confirmButton.setOnAction(e -> OnConfirm());

        Button backButton = new Button("返回");
        backButton.setFont(new Font(20.0));
        backButton.setLayoutX(450.0);
        backButton.setLayoutY(293.0);
        backButton.setPrefSize(69.0, 59.0);
        backButton.setOnAction(e -> {
            loginpage lgpage = new loginpage();
            try {
                lgpage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create AnchorPane and add children
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView, userLabel, textField, comboBox, actionLabel, confirmButton, backButton);

        // Create VBox and add MenuBar and AnchorPane
        VBox vBox = new VBox();
        vBox.getChildren().addAll(anchorPane);
        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);

        // Set the Scene and Stage
        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("用户管理界面");
        primaryStage.show();
    }

    private void OnConfirm() {
        String action = comboBox.getValue();
        String userId = textField.getText().trim();

        if (action != null && !userId.isEmpty()) {
            String url = "";
            if (action.equals("封禁")) {
                url = "http://119.45.239.3:8080/user/" + userId + "/freeze";
            } else if (action.equals("解禁")) {
                url = "http://119.45.239.3:8080/user/" + userId + "/unfreeze";
            }

            sendPostRequest(url);
        } else {
            showAlert("错误", "请填写用户ID并选择操作");
        }
    }

    private void sendPostRequest(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(BodyPublishers.noBody()) // 无需请求体的 POST 请求
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            responseFuture.thenAccept(response -> {
                if (response.statusCode() == 200) {
                    Platform.runLater(() -> showAlert("成功", "操作成功"));
                } else {
                    Platform.runLater(() -> showAlert("失败", "操作失败，状态码：" + response.statusCode()));
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                Platform.runLater(() -> showAlert("错误", "请求失败"));
                return null;
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("错误", "无法发送请求");
        }
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
