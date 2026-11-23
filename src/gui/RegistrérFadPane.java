package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RegistrérFadPane extends GridPane {
    public RegistrérFadPane(){
        initContent();
    }

    private void initContent(){
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift
        Label lblRegistrérFad = new Label("Registrér fad");
        add(lblRegistrérFad,0,0);

        //Overskriftens farve, tykkelse og størrelse
        lblRegistrérFad.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for fadnummer, fadtype, kapacitet, tidligere indhold og leverandør
        Label lblFadnummer = new Label("Fadnummer");
        Label lblFadtype = new Label("Fadtype");
        Label lblKapacitet = new Label("Kapacitet (liter)");
        Label lblTidligereIndhold = new Label("Tidligere indhold");
        Label lblLeverandør = new Label("Leverandør");
        add(lblFadnummer,0,1);
        add(lblFadtype,0,3);
        add(lblKapacitet,3,3);
        add(lblTidligereIndhold,0,5);
        add(lblLeverandør,3,5);

        //Textfield og ComboBox for fadnummer, fadtype, kapacitet, tidligere indhold og leverandør
        TextField txfFadnummer = new TextField();
        ComboBox<String> cbFadtype = new ComboBox<>();
        TextField txfKapacitet = new TextField();
        TextField txfTidligereIndhold = new TextField();
        TextField txfLeverandør = new TextField();
        add(txfFadnummer,0,2,4,1);
        add(cbFadtype,0,4);
        add(txfKapacitet,3,4);
        add(txfTidligereIndhold,0,6);
        add(txfLeverandør,3,6);

        //Button for registrering
        Button btnRegistrér = new Button("Registrér");
        add(btnRegistrér,4,7);
    }
}
