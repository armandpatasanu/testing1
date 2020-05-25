package org.loose.oose.fis.project.other;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    static boolean answer;
    public static boolean display() {
        Stage s = new Stage();
        Button yB,nB;

        nB=new Button("Nu");
        nB.setOnAction(e -> {
            answer=false;
            s.close();
        });
        yB=new Button("Da");
        yB.setOnAction(e -> {
            answer=true;
            s.close();
        });

        s.initModality(Modality.APPLICATION_MODAL);
        s.setMinWidth(500);

        Label l1=new Label();
        l1.setText("Sunteti sigur ca doriti sa stergeti acest video?");

        VBox layout =new VBox(10);
        layout.getChildren().addAll(l1,yB,nB);
        layout.setAlignment(Pos.CENTER);

        Scene sc = new Scene(layout);
        s.setScene(sc);
        s.showAndWait();

        return answer;

    }
}
