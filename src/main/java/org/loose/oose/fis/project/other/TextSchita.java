package org.loose.oose.fis.project.other;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TextSchita extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        VBox layout = new VBox(
                10,
                createButtonBasedEditor("foo"),
                createInlineEditor("bar")
        );

        stage.setScene(new Scene(layout));
        stage.show();
    }

    private Pane createButtonBasedEditor(String initialText) {
        Label label = new Label(initialText);
        TextField textField = new TextField(label.getText());
        Button save = new Button("Save");
        save.setDefaultButton(true);
        save.setDisable(true);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            save.setDisable(false);
        });

        textField.setOnAction(event -> save.fire());

        save.setOnAction(event -> {
            label.setText(textField.getText());
            save.setDisable(true);
        });

        VBox layout = new VBox(
                10,
                label,
                textField,
                save
        );
        layout.setPadding(new Insets(10));
        layout.setStyle(
                "-fx-background-color: palegreen;"
        );

        return layout;
    }

    private Pane createInlineEditor(String initialText) {
        TextField textField = new TextField(initialText);

        textField.styleProperty().bind(
                Bindings.when(
                        textField.editableProperty())
                        .then((String) null)
                        .otherwise("-fx-background-color: transparent;")
        );

        textField.setEditable(false);

        textField.setOnAction(event -> textField.setEditable(false));

        ToggleButton edit = new ToggleButton(null, new ImageView(EDIT_ICON_LOC));
        textField.editableProperty().bindBidirectional(edit.selectedProperty());

        edit.setOnAction(event -> {
            if (edit.isSelected()) {
                textField.requestFocus();
            }
        });

        HBox layout = new HBox(
                5,
                edit,
                textField
        );
        layout.setPadding(new Insets(10));
        layout.setStyle(
                "-fx-background-color: paleturquoise;"
        );

        return layout;
    }

    // icon license: free for non-commercial use.
    // commercial license available at: http://www.customicondesign.com/free-icons/mono-icon-set/mono-general-2/
    public static final String EDIT_ICON_LOC =
            "http://icons.iconarchive.com/icons/custom-icon-design/mono-general-2/16/edit-icon.png";
}
