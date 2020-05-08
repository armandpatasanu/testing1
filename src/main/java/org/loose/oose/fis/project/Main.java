package org.loose.oose.fis.project;

import javafx.application.Application;
import javafx.stage.Stage;
import org.loose.oose.fis.project.services.UserService;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{

        UserService.loadUsersFromFile();

        Stage loginStage = Tools.createLoginStage(primaryStage);
        loginStage.show();
    }
}