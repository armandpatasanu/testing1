package org.loose.oose.fis.project.other;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldLimit extends Application {
    private static final int LIMIT = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {

        final TextField textField = new TextField();
        textField.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (textField.getText().length() >= LIMIT) {

                        // if it's 11th character then just setText to previous
                        // one
                        textField.setText(textField.getText().substring(0, LIMIT));
                    }
                }
            }
        });

        VBox vbox = new VBox(20);
        vbox.getChildren().add(textField);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
