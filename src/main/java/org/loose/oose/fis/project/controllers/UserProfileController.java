package org.loose.oose.fis.project.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import org.loose.oose.fis.project.Main;
import org.loose.oose.fis.project.Tools;
import org.loose.oose.fis.project.model.User;
import org.loose.oose.fis.project.services.UserService;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static org.loose.oose.fis.project.controllers.loginController.active_user;

public class UserProfileController implements Initializable, EventHandler<ActionEvent> {

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
    private Text userDescriptionValue;
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
        /*
        Color c = null;
        profileBackground.setFill(c.valueOf(active_user.getBack_color()));
        userFullValue.setText(active_user.getFirstName() + " " + active_user.getLastName());
        usernameValue.setText("@" + active_user.getUsername());
        userAdressValue.setText(active_user.getCity() + "," + active_user.getCountry());
        userDescriptionValue.setText(active_user.getDescription());
        userPhoneValue.setText(active_user.getPhone());
        userEmailValue.setText(active_user.getEmail());
        backgroundColorPicker.setOnAction(e -> pickerColor = backgroundColorPicker.getValue());
        System.out.println(active_user.getBack_color());
        */
    }

    public void logOutHandler(ActionEvent event) throws Exception {

        Stage prevStage = (Stage) logOutButton.getScene().getWindow();
        prevStage.close();
        Stage stage=new Stage();
        Stage loginStage = Tools.createLoginStage(stage);
        loginStage.show();
    }

    public void seeVideosList(ActionEvent event) {
    }

    @FXML
    public void openEditProfileHandler(ActionEvent event) throws IOException {
        AnchorPane content =  FXMLLoader.load(getClass().getClassLoader().getResource("editProfile.fxml"));
        mainPane.getChildren().add(content);
    }

    public void addVideoHandler(ActionEvent event) {
    }
    /*
    public void saveColorHandler(ActionEvent event) {
        profileBackground.setFill(pickerColor);
        active_user.setBack_color(pickerColor.toString());
        System.out.println(active_user.getBack_color());
        active_user.setCity("Timisoara");
        System.out.println(active_user.getCity());
    };
    */
    @Override
    public void handle(ActionEvent event) {

    }

}
