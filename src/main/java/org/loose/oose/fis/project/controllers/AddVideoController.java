package org.loose.oose.fis.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.loose.oose.fis.project.services.VideoService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static org.loose.oose.fis.project.controllers.LoginController.active_user;


public class AddVideoController implements Initializable {

    @FXML
    private TextField videoCategoryValue;
    @FXML
    private TextField videoTitleValue;
    @FXML
    private TextArea videoDescriptionValue;
    @FXML
    private Text addVideoErrorText;
    @FXML
    private AnchorPane videoPane;
    @FXML
    private MediaView addVideoMedia;
    @FXML
    private TextField videoPathValue;
    @FXML
    private TextField thumbnailPathValue;
    @FXML
    private ImageView videoThumbnail;
    private File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void searchVideoHandler(ActionEvent event) {
        final FileChooser filechooser = new FileChooser();
        //final DirectoryChooser dirchooser = new DirectoryChooser();
        Stage stage = (Stage) videoPane.getScene().getWindow();
        file= filechooser.showOpenDialog(stage);
        if(file!=null)
        {
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaplayer = new MediaPlayer(media);
            videoPathValue.setText(file.toURI().toString());
            addVideoMedia.setMediaPlayer(mediaplayer);
        }
    }

    public void searchThumbnailHandler(ActionEvent event)
    {
        final FileChooser filechooser = new FileChooser();
        Stage stage = (Stage) videoPane.getScene().getWindow();
        File file= filechooser.showOpenDialog(stage);
        if(file!=null)
        {
            Image im = new Image(file.toURI().toString());
            videoThumbnail.setImage(im);
            thumbnailPathValue.setText(file.toURI().toString());
        }

    }
    public void addVideoHandler(ActionEvent event) {
        String vcategory = videoCategoryValue.getText();
        String vtitle = videoTitleValue.getText();
        String vdescription = videoDescriptionValue.getText();
        String vpath = videoPathValue.getText();
        String vuploader = active_user.getUsername();
        String vthumbnail =  thumbnailPathValue.getText();

        if (vcategory == null || vcategory.isEmpty()) {
            addVideoErrorText.setText("Please choose a catergory for the video!");
            return;
        }
        if (vtitle == null || vtitle.isEmpty()) {
            addVideoErrorText.setText("Please type in a title for the video!");
            return;
        }
        if (vpath == null || vpath.isEmpty()) {
            addVideoErrorText.setText("Please add a video!");
            return;
        }
        if (vthumbnail == null || vthumbnail.isEmpty()) {
            addVideoErrorText.setText("Please add a thumbnail!");
            return;
        }
        else
        {
            VideoService.addVideo(vuploader,vtitle,vcategory,vdescription,vpath,vthumbnail);
            thumbnailPathValue.clear();
            videoPathValue.clear();
            videoDescriptionValue.clear();
            videoTitleValue.clear();
            videoCategoryValue.clear();
            System.out.println("Video adaugat cu succes!");
        }
    }
}
