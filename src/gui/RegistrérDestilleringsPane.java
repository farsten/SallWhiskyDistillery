package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RegistrérDestilleringsPane extends GridPane {
    public RegistrérDestilleringsPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

//overskrift
        Label lblRegistrérDestillering = new Label("Registrér destillering");
        add(lblRegistrérDestillering, 0, 0);
        lblRegistrérDestillering.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for newMakeNummer, maltbatch, destilleringsansvarlig, startdato, slutdato, alkoholprocent og mængde
        Label lblNMNummer = new Label("New Make-nummer");
        add(lblNMNummer, 0, 1);

        Label lblMaltBatch = new Label("Vælg maltbatch");
        add(lblMaltBatch, 0, 3);

        Label lblDestilleringsAnsvarlig = new Label("Destilleringsansvarlig");
        add(lblDestilleringsAnsvarlig, 2, 3);

        Label lblStartDato = new Label("Startdato");
        add(lblStartDato, 0, 5);

        Label lblSlutdato = new Label("Slutdato");
        add(lblSlutdato, 2, 5);

        Label lblAlkoProcent = new Label("Alkoholprocent (%)");
        add(lblAlkoProcent, 0, 7);

        Label lblMængde = new Label("Mængde (liter)");
        add(lblMængde, 2, 7);

        /*Tekstfelter, ComboBoxes, DatePickers for
          newMakeNummer, maltbatch, destilleringsansvarlig, startdato, slutdato, alkoholprocent og mængde*/

        TextField txfNMNummer = new TextField();
        add(txfNMNummer, 0, 2, 4, 1);

        ComboBox<String> cbMaltBatch = new ComboBox<>();
        add(cbMaltBatch, 0, 4);

        ComboBox<String> cbAnsvarlig = new ComboBox<>();
        add(cbAnsvarlig, 2, 4);

        DatePicker dpStartDato = new DatePicker();
        add(dpStartDato, 0, 6);

        DatePicker dpSlutdato = new DatePicker();
        add(dpSlutdato, 2, 6);

        TextField txfAlkoProcent = new TextField();
        add(txfAlkoProcent, 0, 8);

        TextField txfMængde = new TextField();
        add(txfMængde, 2, 8);

        //Registrér button
        Button btnRegistrérDestillering = new Button("Registrér");
        btnRegistrérDestillering.setStyle("-fx-font-size: 10px;");
        add(btnRegistrérDestillering, 4, 9);
        //__________________________________________________________________________

    }

}

