package org.loose.oose.fis.project.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.loose.oose.fis.project.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.loose.oose.fis.project.controllers.LoginController.active_user;

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
    @FXML
    private ColorPicker backgroundColorPicker;
    @FXML
    private TextField usersCityValue;
    @FXML
    private TextField usersNumberValue;
    @FXML
    private Text editProfileError;

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
        String new_city= usersCityValue.getText();
        String new_phone= usersNumberValue.getText();
        String new_description= descriptionTextArea.getText();
        String new_country=  countryComboBox.getValue();
        Color c= backgroundColorPicker.getValue();

        if(new_city==null || (new_city.isEmpty()))
        {
            usersCityValue.setText("enter a city");
            usersCityValue.setStyle("-fx-font-color:#c31c1c");
            return;
        }
        if(new_phone==null || (new_phone.isEmpty()))
        {
            usersNumberValue.setText("enter a valid phone number");
            usersNumberValue.setStyle("-fx-font-color:#c31c1c");
            return;
        }
        if(new_description==null || (new_description.isEmpty()))
        {
            descriptionTextArea.setText("enter a description");
            descriptionTextArea.setStyle("-fx-font-color:#c31c1c");
            return;
        }
        if(new_country==null)
        {
            editProfileError.setText("Alegeti o tara valida!");
            return;
        }
        if(c.equals(active_user.getBack_color()))
        {
            editProfileError.setText("Alegeti o culoare de background!");
            return;
        }
        active_user.setCity(new_city);
        active_user.setPhone(new_phone);
        active_user.setBack_color(c.toString());
        active_user.setDescription(new_description);
        active_user.setCountry(new_country);
        UserService.persistUsers();
        usersCityValue.clear();
        usersNumberValue.clear();
        descriptionTextArea.clear();
        System.out.println("Profil editat cu succes!");
    };
    public void searchNewImageHandler(ActionEvent event) throws IOException {
        final FileChooser filechooser = new FileChooser();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        File file= filechooser.showOpenDialog(stage);
        if(file!=null)
        {
            Image im = new Image(file.toURI().toString(),150,120,true,false);
            picturePreview.setFill(new ImagePattern(im));
            active_user.setPic_path(file.toURI().toString());
        }
    }

}
