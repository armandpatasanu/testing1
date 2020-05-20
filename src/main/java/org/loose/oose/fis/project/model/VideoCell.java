package org.loose.oose.fis.project.model;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

public class VideoCell extends ListCell<Video> {

    public VideoCell(Video v)
    {
        super();
        Media media=new Media(v.getVideo_path());
        MediaPlayer vmp=new MediaPlayer(media);
        MediaView vmv= new MediaView(vmp);
        Text vdescription = new Text();
        vdescription.setText(v.getDescription());
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(vmv, vdescription, editButton, deleteButton);
    }

}
