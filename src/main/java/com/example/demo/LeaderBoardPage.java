package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LeaderBoardPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPrefHeight(600);
        root.setPrefWidth(800);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMaxHeight(Double.MAX_VALUE);
        anchorPane.setMaxWidth(Double.MAX_VALUE);

        ImageView imageView = new ImageView(getClass().getResource("/com/example/demo/picture/backgroundpicture.png").toExternalForm());
        imageView.setFitHeight(600);
        imageView.setFitWidth(800);
        imageView.setLayoutY(3);

        Label label = new Label("排名");
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(125);
        label.setLayoutY(200);
        label.setPrefHeight(205);
        label.setPrefWidth(95);
        label.setFont(new Font(33));

        TableView<ScoreRecord> tableView = new TableView<>();
        tableView.setLayoutX(255);
        tableView.setLayoutY(200);
        tableView.setPrefHeight(150);
        tableView.setPrefWidth(350);

        TableColumn<ScoreRecord, Number> rankColumn = new TableColumn<>("排名");
        rankColumn.setPrefWidth(75);
        rankColumn.setCellValueFactory(cellData -> cellData.getValue().rankProperty());

        TableColumn<ScoreRecord, Number> idColumn = new TableColumn<>("id");
        idColumn.setPrefWidth(75);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<ScoreRecord, Number> scoreColumn = new TableColumn<>("成绩");
        scoreColumn.setPrefWidth(75);
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());

        TableColumn<ScoreRecord, String> timeColumn = new TableColumn<>("通关时间");
        timeColumn.setPrefWidth(100);
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        tableView.getColumns().addAll(rankColumn, idColumn, scoreColumn, timeColumn);

        ObservableList<ScoreRecord> data = FXCollections.observableArrayList(
                new ScoreRecord(1, 1, 95, "5:30"),
                new ScoreRecord(2, 2, 88, "6:15"),
                new ScoreRecord(3, 3, 76, "7:10"),
                new ScoreRecord(4, 4, 92, "5:45"),
                new ScoreRecord(5, 5, 85, "6:00")
        );
        tableView.setItems(data);

        Button backButton = new Button("返回");
        backButton.setId("back4Button");
        backButton.setLayoutX(300);
        backButton.setLayoutY(400);
        backButton.setPrefHeight(69);
        backButton.setPrefWidth(95);
        backButton.setFont(new Font(21));
        backButton.setOnAction(event -> onBack4());
        backButton.setOnAction(e -> {
            page1 mainPage = new page1();
            try {
                mainPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        anchorPane.getChildren().addAll(imageView, label, tableView, backButton);

        root.getChildren().addAll(anchorPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("排行榜");
        primaryStage.show();

        applyFadeTransition(label, 3);
        applyFadeTransition(tableView, 2);
    }

    private void applyFadeTransition(javafx.scene.Node node, int seconds) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(seconds), node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    private void onBack4() {
        // Implement the action for back4Button here
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ScoreRecord {
    private final IntegerProperty rank;
    private final IntegerProperty id;
    private final IntegerProperty score;
    private final StringProperty time;

    public ScoreRecord(int rank, int id, int score, String time) {
        this.rank = new SimpleIntegerProperty(rank);
        this.id = new SimpleIntegerProperty(id);
        this.score = new SimpleIntegerProperty(score);
        this.time = new SimpleStringProperty(time);
    }

    public int getRank() {
        return rank.get();
    }

    public IntegerProperty rankProperty() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank.set(rank);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}
