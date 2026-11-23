package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegistrérMarkPane extends GridPane {
    public RegistrérMarkPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift
        Label lblRegistrérMark = new Label("Registrér mark");
        add(lblRegistrérMark, 0, 0);

        // Overskriftens farve, tykkelse og størrelse
        lblRegistrérMark.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //labels for mark-ID og mark-navn
        Label lblMarkID = new Label("Mark-ID");
        Label lblMarkNavn = new Label("Markens navn");
        add(lblMarkID, 0, 1);
        add(lblMarkNavn, 3, 1);

        //Textfields for mark-ID og mark-navn
        TextField txfMarkID = new TextField();
        TextField txfMarkNavn = new TextField();
        add(txfMarkID, 0, 2);
        add(txfMarkNavn, 3, 2);

        //Registrér button
        Button btnRegistrérMark = new Button("Registrér");
        add(btnRegistrérMark, 4, 5);
        //________________________________________________________________________

    }
}

