package org.loose.oose.fis.project.other;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Core extends Application {


    public static SlideShow ss;

    public int width = 300;
    public int height = 150;


    Scene rootScene;

    Pane rootPane;

    Pane innerPane1;
    Pane innerPane2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ss = new SlideShow();
        ss.loadImages(width);

        primaryStage.setTitle("Slide Shows");
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        rootPane = new Pane();

        innerPane1 = new Pane();
        innerPane2 = new Pane();

        for (int i = 0; i < ss.getIVs().size() - 1; i++) {
            innerPane1.getChildren().add(ss.getIVs().get(i));
        }
        innerPane2.getChildren().add(ss.getIVs().get(ss.getIVs().size() - 1));

        rootPane.getChildren().addAll(innerPane1, innerPane2);

        rootScene = new Scene(rootPane, width, height);

        primaryStage.setScene(rootScene);

        ss.startAnimation(width, innerPane1, innerPane2, 4);

        primaryStage.show();
    }
}