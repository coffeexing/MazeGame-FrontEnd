package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class page1 extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create ImageView
        ImageView imageView1 = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        imageView1.setFitHeight(600.0);
        imageView1.setFitWidth(800.0);
        imageView1.setLayoutY(0.0);

        ImageView imageView2 = new ImageView(getClass().getResource("/com/example/demo/picture/titlepicture.png").toExternalForm());
        imageView2.setFitHeight(161.0);
        imageView2.setFitWidth(424.0);
        imageView2.setLayoutX(160.0);
        imageView2.setLayoutY(62.0);

        // Create Buttons
        Button startButton = new Button("开始游戏");
        startButton.setId("StartButton");
        startButton.setLayoutX(150.0);
        startButton.setLayoutY(329.0);
        startButton.setPrefHeight(83.0);
        startButton.setPrefWidth(137.0);
        startButton.setFont(Font.font(25));

        Button leaderBoardButton = new Button("排行榜");
        leaderBoardButton.setId("LeaderBoardButton");
        leaderBoardButton.setLayoutX(450.0);
        leaderBoardButton.setLayoutY(329.0);
        leaderBoardButton.setPrefHeight(83.0);
        leaderBoardButton.setPrefWidth(137.0);
        leaderBoardButton.setFont(Font.font(24));

        Button ExitButton = new Button("退出登录");
        ExitButton.setId("ExitButton");
        ExitButton.setLayoutX(300.0);
        ExitButton.setLayoutY(450.0);
        ExitButton.setPrefHeight(83.0);
        ExitButton.setPrefWidth(137.0);
        ExitButton.setFont(Font.font(24));
        ExitButton.setOnAction(e -> {
            loginpage lgpage = new loginpage();
            try {
                lgpage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create AnchorPane and add children
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView1, imageView2, startButton, leaderBoardButton, ExitButton);

        // Add action to startButton to switch to the game scene
        startButton.setOnAction(e -> {
            GamePage gamePage = new GamePage();
            try {
                gamePage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Add action to leaderBoardButton to switch to the leaderboard scene
        leaderBoardButton.setOnAction(e -> {
            LeaderBoardPage leaderBoardPage = new LeaderBoardPage();
            try {
                leaderBoardPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create VBox and add AnchorPane
        VBox vbox = new VBox();
        vbox.getChildren().addAll(anchorPane);
        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);

        // Create Scene and set Stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setTitle("迷宫游戏");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Apply fade transition to buttons
        applyFadeTransition(startButton);
        applyFadeTransition(leaderBoardButton);
        applyFadeTransition(ExitButton);
    }

    private void applyFadeTransition(Button button) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), button);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
