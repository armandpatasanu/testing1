package org.loose.oose.fis.project.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import org.loose.oose.fis.project.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class mainTabController implements Initializable {

    private static double xOffset = 0;
    private static double yOffset = 0;
    @FXML
    private Label description;
    @FXML
    private TextArea area;
    @FXML
    private Button logOutButton;
    @FXML
    private AnchorPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveChanges(ActionEvent event) {
        description.setText(area.getText());}


}
