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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.loose.oose.fis.project.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.loose.oose.fis.project.controllers.loginController.active_user;

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
    private TextField newUsernameValue;

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
        String new_username= newUsernameValue.getText();
        if(new_username!=null || (!new_username.isEmpty()))
        {
            active_user.setUsername(new_username);
        }
        UserService.persistUsers();
    };
    public void searchImageHandler(ActionEvent event) throws IOException {
        final FileChooser filechooser = new FileChooser();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        File file= filechooser.showOpenDialog(stage);
        if(file!=null)
        {
            Image im = new Image(file.toURI().toString());
            picturePreview.setFill(new ImagePattern(im));
            active_user.setPic_path(file.toURI().toString());
        }
    }

}
