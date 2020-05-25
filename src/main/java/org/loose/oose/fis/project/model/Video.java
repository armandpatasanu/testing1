package org.loose.oose.fis.project.model;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class Video {
    private String thumbnail_path;
    private String uploader;
    private String video_path;
    private String title;
    private String description;
    private String category;
    private int likes;
    private int dislikes;

    public Video() {
    }

    public Video(String user, String title, String category, String description, String vpath, String vthumbnail) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.video_path = vpath;
        this.likes = 0;
        this.dislikes = 0;
        this.uploader = user;
        this.thumbnail_path = vthumbnail;
    }

    public String getThumbnail_path() {
        return thumbnail_path;
    }

    public void setThumbnail_path(String thumbnail_path) {
        this.thumbnail_path = thumbnail_path;
    }

    public String getVideo_path() {
        return video_path;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getCategory() {
        return category;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;
        Video video = (Video) o;
        return getTitle().equals(video.getTitle());
    }

}
