package org.loose.oose.fis.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import org.loose.oose.fis.project.services.VideoService;

import java.net.URL;
import java.util.ResourceBundle;


public class WatchVideosController implements Initializable {


    @FXML
    private ListView listView;
    @FXML
    private ScrollPane videoScrollPane;

    @FXML
    public void initialize(URL location, ResourceBundle resources)
    {
        VideoService.putVideo(this);
        listView.setFocusTraversable( false );
    }

    public ListView getListView() {
        return listView;
    }
}

