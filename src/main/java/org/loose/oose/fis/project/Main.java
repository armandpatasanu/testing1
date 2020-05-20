package org.loose.oose.fis.project;

import javafx.application.Application;
import javafx.stage.Stage;
import org.loose.oose.fis.project.services.UserService;
import org.loose.oose.fis.project.services.VideoService;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{

        UserService.loadUsersFromFile();
        VideoService.loadVideosFromFile();


        Stage loginStage = Tools.createLoginStage(primaryStage);
        loginStage.show();
    }
}