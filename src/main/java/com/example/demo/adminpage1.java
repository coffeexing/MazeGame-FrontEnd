package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class adminpage1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPrefSize(800, 600);


        AnchorPane anchorPane = new AnchorPane();
        ImageView imageView = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        imageView.setFitHeight(600);
        imageView.setFitWidth(800);
        AnchorPane.setTopAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);

        Button userStructButton = new Button("用户操作");
        userStructButton.setFont(new Font(22));
        userStructButton.setLayoutX(300);
        userStructButton.setLayoutY(200);
        userStructButton.setOnAction(e -> onUserStruct());
        userStructButton.setOnAction(e -> {
            adminUser admu = new adminUser();
            try {
                admu.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button mazeButton = new Button("迷宫操作");
        mazeButton.setFont(new Font(22));
        mazeButton.setLayoutX(300);
        mazeButton.setLayoutY(300);
        mazeButton.setOnAction(e -> onMaze());
        mazeButton.setOnAction(e -> {
            adminMaze admm = new adminMaze();
            try {
                admm.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button Back5Button = new Button("返回");
        Back5Button.setFont(new Font(22));
        Back5Button.setLayoutX(325);
        Back5Button.setLayoutY(400);
        Back5Button.setOnAction(e -> onBack5());
        Back5Button.setOnAction(e -> {
            loginpage lgpage = new loginpage();
            try {
                lgpage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        anchorPane.getChildren().addAll(imageView, userStructButton, mazeButton,Back5Button);
        root.getChildren().add(anchorPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("管理员");
        primaryStage.show();
    }

    private void onUserStruct() {
        // Handle UserStruct button action
    }

    private void onMaze() {
        // Handle Maze button action
    }
    private void onBack5() {
        // Handle Maze button action
    }

    public static void main(String[] args) {
        launch(args);
    }
}

