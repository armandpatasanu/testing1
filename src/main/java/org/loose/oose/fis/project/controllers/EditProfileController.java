package org.loose.oose.fis.project.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField pathValue;
    @FXML
    private Circle picturePreview;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private TextArea descriptionTextArea;
    private static final int LIMIT = 150;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> cities = FXCollections.observableArrayList();
        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1) {
            Locale obj = new Locale("", countrylist);
            String[] city = { obj.getDisplayCountry() };
            for (int x = 0; x < city.length; x++) {
                cities.add(obj.getDisplayCountry());
            }
        }
        countryComboBox.setItems(cities);

        descriptionTextArea.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                if (newValue.intValue() > oldValue.intValue())
                {
                    if (descriptionTextArea.getText().length() >= LIMIT)
                    {
                        descriptionTextArea.setText(descriptionTextArea.getText().substring(0, LIMIT));
                    }
                }
            }
        });
    }
    public void saveChangesHandler(ActionEvent event) {
        /*
    public void saveColorHandler(ActionEvent event) {
        profileBackground.setFill(pickerColor);
        active_user.setBack_color(pickerColor.toString());
        System.out.println(active_user.getBack_color());
        active_user.setCity("Timisoara");
        System.out.println(active_user.getCity());
    };
    */
    }

    public void searchImageHandler(ActionEvent event) {
        final FileChooser filechooser = new FileChooser();
        //final DirectoryChooser dirchooser = new DirectoryChooser();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        File file= filechooser.showOpenDialog(stage);
        if(file!=null)
        {
            Image im = new Image(file.toURI().toString());
            picturePreview.setFill(new ImagePattern(im));
        }
    }

}
