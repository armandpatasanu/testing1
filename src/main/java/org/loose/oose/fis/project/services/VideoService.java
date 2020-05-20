package org.loose.oose.fis.project.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.loose.oose.fis.project.controllers.EditProfileController;
import org.loose.oose.fis.project.controllers.UserProfileController;
import org.loose.oose.fis.project.controllers.WatchVideosController;
import org.loose.oose.fis.project.exceptions.CouldNotWriteUsersException;
import org.loose.oose.fis.project.model.Video;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class VideoService {

    private static WatchVideosController wvc;
    private static UserProfileController upc;
    private static List<Video> videos;
    private static final Path VIDEOS_PATH = FileSystemService.getPathToFile("config", "json/videos.json");

    public static void loadVideosFromFile() throws IOException {

        if (!Files.exists(VIDEOS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("json/videos.json"), VIDEOS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        videos = objectMapper.readValue(VIDEOS_PATH.toFile(), new TypeReference<List<Video>>() {
        });
    }
    public static Video getVideo(String vid_path)
    {
        for(Video v:videos)
            if (Objects.equals(vid_path, v.getVideo_path()))
                return v;
        return null;
    }
    private static void persistVideos() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(VIDEOS_PATH.toFile(), videos);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
    public static void addVideo(String uploader,String title, String category,String description, String path)
    {
        videos.add(new Video(uploader,title,category,description,path));
        persistVideos();
    }
    public static HBox setVideo(String title, String description)
    {
        HBox h = new HBox();
        h.setPrefSize(528,100);
        /*
        Media media = new Media(vpath);
        MediaPlayer mediaplayer = new MediaPlayer(media);
        MediaView mv= new MediaView(mediaplayer);
         */
        Button b1=new Button("edit");
        Text t= new Text(description);
        Text t1=new Text(title);
        h.getChildren().addAll(t1,t,b1);
        return h;
    }
    public static void putVideo(UserProfileController w) {
        upc=w;
    }


    public static void setVideos()
    {
        Button b1=new Button("Edit");
        for(Video v:videos)
            upc.getMainPane().getChildren().addAll(setVideo(v.getTitle(),v.getDescription()));

    }

}

