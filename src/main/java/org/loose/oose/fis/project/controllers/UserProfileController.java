package org.loose.oose.fis.project.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.loose.oose.fis.project.Tools;
import org.loose.oose.fis.project.services.VideoService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.loose.oose.fis.project.controllers.LoginController.active_user;

public class UserProfileController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private static WatchVideosController wvc;
    @FXML
    private Label description;
    @FXML
    private TextArea area;
    @FXML
    private Button logOutButton;
    @FXML
    private AnchorPane tabPane;
    @FXML
    private Text userFullValue;
    @FXML
    private Text usernameValue;
    @FXML
    private Text userAdressValue;
    @FXML
    private Label userDescriptionValue;
    @FXML
    private Text userPhoneValue;
    @FXML
    private Circle profilePic;
    @FXML
    private Rectangle profileBackground;
    @FXML
    private Text userEmailValue;
    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image im=new Image(active_user.getPic_path());
        profilePic.setFill(new ImagePattern(im));
        Color c = null;
        profileBackground.setFill(c.valueOf(active_user.getBack_color()));
        userFullValue.setText(active_user.getFirstName() + " " + active_user.getLastName());
        usernameValue.setText("@" + active_user.getUsername());
        userAdressValue.setText(active_user.getCity() + "," + active_user.getCountry());
        userDescriptionValue.setText(active_user.getDescription());
        userPhoneValue.setText(active_user.getPhone());
        userEmailValue.setText(active_user.getEmail());
        System.out.println(active_user.getBack_color());
        AnchorPane content= new AnchorPane();
        try {
            content = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/WatchVideos.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().add(content);
    }
    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void logOutHandler(ActionEvent event) throws Exception {

        Stage prevStage = (Stage) logOutButton.getScene().getWindow();
        prevStage.close();
        Stage stage=new Stage();
        Stage loginStage = Tools.createLoginStage(stage);
        loginStage.show();

    }

    public void openEditProfileHandler(ActionEvent event) throws IOException {
        AnchorPane content =  FXMLLoader.load(getClass().getClassLoader().getResource("fxml/EditProfile.fxml"));
        mainPane.getChildren().add(content);
    }

    public void addVideoHandler(ActionEvent event) throws IOException {
        AnchorPane content =  FXMLLoader.load(getClass().getClassLoader().getResource("fxml/AddVideo.fxml"));
        mainPane.getChildren().removeAll();
        mainPane.getChildren().add(content);
    }

    public void openVideosHandler(ActionEvent event) throws Exception {
        AnchorPane content =  FXMLLoader.load(getClass().getClassLoader().getResource("fxml/WatchVideos.fxml"));
        mainPane.getChildren().removeAll();
        mainPane.getChildren().add(content);
        VideoService.setVideos();
    }

    @Override
    public void handle(ActionEvent event) {

    }

}
