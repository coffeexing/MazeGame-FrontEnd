package com.example.demo;

import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;


public class loginpage extends Application {


    private Stage primaryStage;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    TextField usernameField;
    PasswordField passwordField;


    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;


        // Create Labels
        Label usernameLabel = new Label("用户名");
        usernameLabel.setFont(new Font(18));

        Label passwordLabel = new Label("密码");
        passwordLabel.setFont(new Font(18));

        // Create TextFields and PasswordField
        usernameField = new TextField();
        passwordField = new PasswordField();

        // Create TranslateTransitions for TextFields
        TranslateTransition usernameTransition = new TranslateTransition(Duration.seconds(2), usernameField);
        usernameTransition.setFromX(-300);
        usernameTransition.setToX(0);
        usernameTransition.setCycleCount(1);
        usernameTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition passwordTransition = new TranslateTransition(Duration.seconds(2), passwordField);
        passwordTransition.setFromX(-300);
        passwordTransition.setToX(0);
        passwordTransition.setCycleCount(1);
        passwordTransition.setInterpolator(Interpolator.EASE_BOTH);

// Start the animations
        usernameTransition.play();
        passwordTransition.play();


        // Create Buttons
        Button loginButton = new Button("登录");
        loginButton.setFont(new Font(20));
        loginButton.setOnAction(event -> OnLogin());
        loginButton.setBorder(null);

        // Create FadeTransition for LoginButton
        FadeTransition loginFadeTransition = new FadeTransition(Duration.seconds(3), loginButton);
        loginFadeTransition.setFromValue(0);
        loginFadeTransition.setToValue(1);
        loginFadeTransition.setCycleCount(1);
        loginFadeTransition.setInterpolator(Interpolator.EASE_BOTH);

// Start the animation
        loginFadeTransition.play();


        // Create RadioButtons
        RadioButton gamerButton = new RadioButton("玩家");
        gamerButton.setFont(new Font(14));
        gamerButton.setOnAction(event -> OnGamer());

        RadioButton adminButton = new RadioButton("管理员");
        adminButton.setFont(new Font(14));
        adminButton.setOnAction(event -> OnAdmin());

        ToggleGroup toggleGroup = new ToggleGroup();
        gamerButton.setToggleGroup(toggleGroup);
        adminButton.setToggleGroup(toggleGroup);

        // Create ImageViews
        ImageView backgroundImageView = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        backgroundImageView.setFitHeight(600.0);
        backgroundImageView.setFitWidth(800.0);

        ImageView titleImageView = new ImageView(getClass().getResource("/com/example/demo/picture/titlepicture.png").toExternalForm());
        titleImageView.setFitHeight(161.0);
        titleImageView.setFitWidth(424.0);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), titleImageView);
        translateTransition.setFromY(-50);
        translateTransition.setToY(100); // 控制弹跳的高度
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true); // 使动画反向播放以实现弹跳效果

        // 创建 RotateTransition 动画
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1500), titleImageView);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360); // 控制旋转的角度
        rotateTransition.setCycleCount(1); // 旋转动画只播放一次

        // 开始动画
        translateTransition.setOnFinished(event -> rotateTransition.play());
        translateTransition.play();


        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(backgroundImageView, titleImageView,
                usernameLabel, usernameField, passwordLabel, passwordField,
                loginButton, gamerButton, adminButton);

        // Position elements
        AnchorPane.setTopAnchor(titleImageView, 62.0);
        AnchorPane.setLeftAnchor(titleImageView, 160.0);

        AnchorPane.setTopAnchor(usernameLabel, 250.0);
        AnchorPane.setLeftAnchor(usernameLabel, 200.0);

        AnchorPane.setTopAnchor(usernameField, 250.0);
        AnchorPane.setLeftAnchor(usernameField, 300.0);

        AnchorPane.setTopAnchor(passwordLabel, 300.0);
        AnchorPane.setLeftAnchor(passwordLabel, 200.0);

        AnchorPane.setTopAnchor(passwordField, 300.0);
        AnchorPane.setLeftAnchor(passwordField, 300.0);

        AnchorPane.setTopAnchor(loginButton, 400.0);
        AnchorPane.setLeftAnchor(loginButton, 330.0);

        AnchorPane.setTopAnchor(gamerButton, 350.0);
        AnchorPane.setLeftAnchor(gamerButton, 300.0);

        AnchorPane.setTopAnchor(adminButton, 350.0);
        AnchorPane.setLeftAnchor(adminButton, 400.0);

        // Create VBox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(anchorPane);
        VBox.setVgrow(anchorPane, Priority.ALWAYS);

        // Create Scene
        Scene scene = new Scene(vbox, 800, 600);



        // Set up Stage
        primaryStage.setTitle("迷宫游戏");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    int a;

    // Event handler methods
    private void OnLogin() {
        System.out.println("Login button clicked");

        String username = usernameField.getText();

        String password = passwordField.getText();

        int role = a;

        boolean b = true;

        String encodedParams = URLEncoder.encode("username", StandardCharsets.UTF_8) + "=" +
                URLEncoder.encode(username, StandardCharsets.UTF_8) + "&" +
                URLEncoder.encode("password", StandardCharsets.UTF_8) + "=" +
                URLEncoder.encode(password, StandardCharsets.UTF_8) + "&" +
                URLEncoder.encode("role", StandardCharsets.UTF_8) + "=" +
                URLEncoder.encode(String.valueOf(role), StandardCharsets.UTF_8);

        // Create the POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://119.45.239.3:8080/login"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(encodedParams))
                .build();

        // Send the request asynchronously
        CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Handle the response
        responseFuture.thenAccept(response -> {
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                int code = jsonResponse.getInt("code");
                String message = jsonResponse.getString("message"); // Note: the key seems to be misspelled in the example

                System.out.println(code);

                Platform.runLater(() -> {
                    if (code != 200) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("登录提示");
                        alert.setHeaderText(null);
                        alert.setContentText(message);

                        alert.showAndWait().ifPresent(response2 -> {
                            if (response2 == ButtonType.OK) {
                                System.out.println("登录失败");
                            }
                        });
                    } else {
                        try {
                            if (a == 2) {
                                page1 mainPage = new page1();
                                mainPage.start(primaryStage);
                            } else if (a == 1) {
                                adminpage1 mainPage1 = new adminpage1();
                                mainPage1.start(primaryStage);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                System.out.println("1");
            } else {
                System.out.println("Request failed: " + response.statusCode());
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }

    private void OnGamer() {
        a = 2;
    }

    private void OnAdmin() {
        a = 1;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

