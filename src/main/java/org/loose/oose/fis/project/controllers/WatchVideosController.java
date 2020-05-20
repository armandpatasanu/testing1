package org.loose.oose.fis.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.loose.oose.fis.project.services.VideoService;

import java.net.URL;
import java.util.ResourceBundle;


public class WatchVideosController implements Initializable {


    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane videoScrollPane;

    @FXML
    public void initialize(URL location, ResourceBundle resources)
    {
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
}

