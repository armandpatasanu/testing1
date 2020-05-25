package org.loose.oose.fis.project.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.loose.oose.fis.project.Tools;
import org.loose.oose.fis.project.controllers.EditProfileController;
import org.loose.oose.fis.project.controllers.UserProfileController;
import org.loose.oose.fis.project.controllers.WatchVideosController;
import org.loose.oose.fis.project.exceptions.CouldNotWriteUsersException;
import org.loose.oose.fis.project.model.Video;
import org.loose.oose.fis.project.other.ConfirmBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class VideoService {

    public static Video active_video;
    private static WatchVideosController wvc;
    private static UserProfileController upc;
    private static List<Video> videos;
    private static final Path VIDEOS_PATH = FileSystemService.getPathToFile("config", "json/videos.json");

    public static Video getVideo(String title)
    {
        for(Video vd:videos)
            if (Objects.equals(title, vd.getTitle()))
                return vd;
        return null;
    }
    public static void loadVideosFromFile() throws IOException {

        if (!Files.exists(VIDEOS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("json/videos.json"), VIDEOS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        videos = objectMapper.readValue(VIDEOS_PATH.toFile(), new TypeReference<List<Video>>() {
        });
    }
    private static void persistVideos() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(VIDEOS_PATH.toFile(), videos);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
    public static void addVideo(String uploader,String title, String category,String description, String path, String thumbnail)
    {
        videos.add(new Video(uploader,title,category,description,path,thumbnail));
        persistVideos();
    }
    public static HBox setVideo(String title, String description, String vthumb)
    {
        active_video=getVideo(title);
        HBox video_slot=new HBox(20);
        VBox v=new VBox(3);
        HBox h=new HBox(10);
        video_slot.setStyle("-fx-border-color:#b22222;-fx-border-weight:thin;");
        Button button = new Button();
        Region rg=new Region();
        HBox.setHgrow(rg,Priority.ALWAYS);
        Button editButton=new Button("Edit");
        Button deleteButton=new Button("Delete");
        Button seeReviewsButton=new Button("See Reviews");
        Image im=new Image(vthumb,175,100,false,true);
        BackgroundImage iM = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bg = new Background(iM);
        button.setBackground(bg);
        button.setStyle("-fx-border-color:#80bfff; -fx-border-width: 1pt 1pt 1pt 1pt;");
        button.setPrefSize(175,100);
        button.setCursor(Cursor.HAND);
        button.setOnAction(e -> {
            System.out.println("Ati apasat pe videoclipul cu titlul: " + title);
        });
        deleteButton.setOnAction(e-> {
            if(ConfirmBox.display()){
                videos.remove(active_video);
                persistVideos();
                System.out.println("Ati sters videoclipul!");
            }
            else
                System.out.println("Nu ati sters nimic!");
        });
        editButton.setOnAction(e-> {
            active_video=getVideo(title);
            Stage stage=new Stage();
            Stage editVideoStage = null;
            try {
                editVideoStage = Tools.createEditVideoStage(stage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            editVideoStage.show();
        });
        Label vdescription= new Label(description);
        vdescription.setPrefSize(150,100);
        vdescription.setWrapText(true);
        Text vtitle=new Text(title);
        v.getChildren().addAll(vtitle,vdescription);
        h.getChildren().addAll(seeReviewsButton,editButton,deleteButton);
        video_slot.getChildren().addAll(button,v,rg,h);
        video_slot.setAlignment(Pos.CENTER_LEFT);
        return video_slot;
    }
    public static void putVideo(WatchVideosController w) {
        wvc=w;
    }


    public static void setVideos() throws Exception {
        for(Video v:videos)
            wvc.getListView().getItems().add(setVideo(v.getTitle(),v.getDescription(),v.getThumbnail_path()));

    }

}

