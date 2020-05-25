package org.loose.oose.fis.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static org.loose.oose.fis.project.services.VideoService.active_video;

public class EditVideoController implements Initializable {

    @FXML
    private ImageView newThumbnailImage;

    public void saveNewChangesHandler(ActionEvent event) {
    }

    public void searchNewBackgroundHandler(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Image im=new Image(active_video.getThumbnail_path());
        newThumbnailImage.setImage(im);
    }
}
