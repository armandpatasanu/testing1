package org.loose.oose.fis.project.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.loose.oose.fis.project.exceptions.EmailAlreadyUsedException;
import org.loose.oose.fis.project.exceptions.UsernameAlreadyExistsException;
import org.loose.oose.fis.project.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class registerController implements Initializable {
    @FXML
    private Button createAccButton;
    @FXML
    private Text registerMessage;
    @FXML
    private TextField fnamefield;
    @FXML
    private TextField lnamefield;
    @FXML
    private TextField userfield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField passwordfield;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label lblRegister;

    public void closingLogin(MouseEvent event) {
        System.exit(0);
    }

    public void createAccountHandler(ActionEvent event) {
        String firstname = fnamefield.getText();
        String lastname = lnamefield.getText();
        String user = userfield.getText();
        String pass = passwordfield.getText();
        String email = emailfield.getText();

        if (firstname == null || firstname.isEmpty()) {
            registerMessage.setText("Please type in your first name!");
            return;
        }
        if (lastname == null || lastname.isEmpty()) {
            registerMessage.setText("Please type in your last name!");
            return;
        }
        if (user == null || user.isEmpty()) {
            registerMessage.setText("Please type in a username!");
            return;
        }

        if (pass == null || pass.isEmpty()) {
            registerMessage.setText("Password cannot be empty");
            return;
        }
        if (email == null || email.isEmpty())
        {
            registerMessage.setText("A valid email must be entered!");
            return;
        } else
            try {
                UserService.addUser(fnamefield.getText(), lnamefield.getText(), userfield.getText(), emailfield.getText(), passwordfield.getText());
                Parent viewCreateAccountRoot = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
                Scene registerScene = lblRegister.getScene();
                viewCreateAccountRoot.translateYProperty().set(registerScene.getHeight());
                AnchorPane rootPane = (AnchorPane) registerScene.getRoot();
                rootPane.getChildren().add(viewCreateAccountRoot);
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(viewCreateAccountRoot.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
                timeline.setOnFinished((ActionEvent event2) -> {
                    rootPane.getChildren().remove(anchorpane);
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UsernameAlreadyExistsException e) {
                registerMessage.setText(e.getMessage());
            } catch (EmailAlreadyUsedException e) {
                registerMessage.setText(e.getMessage());
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToLoginHandler(ActionEvent event) throws IOException
    {
        try {
            Parent viewCreateAccountRoot = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
            Scene registerScene = lblRegister.getScene();
            viewCreateAccountRoot.translateYProperty().set(registerScene.getHeight());
            AnchorPane rootPane = (AnchorPane) registerScene.getRoot();
            rootPane.getChildren().add(viewCreateAccountRoot);
            Timeline timeline = new Timeline();
            KeyValue keyValue = new KeyValue(viewCreateAccountRoot.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            timeline.setOnFinished((ActionEvent event2) -> {
                rootPane.getChildren().remove(anchorpane);
            });
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
