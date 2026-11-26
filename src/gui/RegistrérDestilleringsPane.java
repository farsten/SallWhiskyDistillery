package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Destillering;
import model.MaltBatch;
import model.Medarbejder;
import storage.Storage;

import java.time.LocalDate;

public class RegistrérDestilleringsPane extends GridPane {
    TextField txfNMNummer;
    ComboBox<MaltBatch> cbBatch;
    ComboBox<Medarbejder> cbAnsvarlig;
    DatePicker dpStartDato;
    DatePicker dpSlutDato;
    TextField txfAlkoProcent;
    TextField txfMængde;
    ListView<Destillering> lvwDestilleringer;
    TextField txfRøgelse;
    TextField txfKommentar;

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

        //Labels for newMakeNummer, maltbatch, destilleringsansvarlig, startdato, slutdato, alkoholprocent og mængde samt røgelse og kommentar
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

        Label lblRøgelse = new Label("Rygemateriale");
        add(lblRøgelse, 0, 9);

        Label lblKommentar = new Label("Evt. kommentar");
        add(lblKommentar, 2, 9);
        /*Tekstfelter, ComboBoxes, DatePickers for
          newMakeNummer, maltbatch, destilleringsansvarlig, startdato, slutdato, alkoholprocent og mængde samt røgelse og kommentar*/

        txfNMNummer = new TextField();
        add(txfNMNummer, 0, 2, 3, 1);

        cbBatch = new ComboBox<>();
        add(cbBatch, 0, 4);

        cbAnsvarlig = new ComboBox<>();
        add(cbAnsvarlig, 2, 4);

        dpStartDato = new DatePicker();
        add(dpStartDato, 0, 6);

        dpSlutDato = new DatePicker();
        add(dpSlutDato, 2, 6);

        txfAlkoProcent = new TextField();
        add(txfAlkoProcent, 0, 8);

        txfMængde = new TextField();
        add(txfMængde, 2, 8);

        txfRøgelse = new TextField();
        add(txfRøgelse, 0, 10);

        txfKommentar = new TextField();
        add(txfKommentar, 2, 10);

        //Registrér button
        Button btnRegistrérDestillering = new Button("Registrér");

        add(btnRegistrérDestillering, 3, 11, 2, 1);
        btnRegistrérDestillering.setPrefWidth(100);
        btnRegistrérDestillering.setOnAction(event -> registrérDestilleringAction());

        //Label og ListView over destilleringer
        Label lblRegistreredeDestilleringer = new Label("Registrerede destilleringer");
        add(lblRegistreredeDestilleringer, 6, 0);
        lblRegistreredeDestilleringer.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        lvwDestilleringer = new ListView<>();
        add(lvwDestilleringer, 6, 1, 2, 8);
        lvwDestilleringer.setPrefHeight(200);
        updateRegistreredeDestilleringer();
        updateMaltBatchComboBox();
        updateDestilleringsAnsvarlig();
        //__________________________________________________________________________

    }

    private void updateRegistreredeDestilleringer() {
        lvwDestilleringer.getItems().setAll(Storage.getDestilleringer());
    }

    private void updateMaltBatchComboBox() {
        cbBatch.getItems().setAll(Storage.getMaltBatch());
    }

    private void updateDestilleringsAnsvarlig() {
        cbAnsvarlig.getItems().setAll(Storage.getMedarbejdere());
    }

    public void registrérDestilleringAction() {
        String newMake = txfNMNummer.getText().trim();
        MaltBatch batch = cbBatch.getValue();
        Medarbejder ansvarlig = cbAnsvarlig.getValue();
        LocalDate startDato = dpStartDato.getValue();
        LocalDate slutDato = dpSlutDato.getValue();
        String alkoholProcentTekst = txfAlkoProcent.getText().trim();
        String mængdeTekst = txfMængde.getText().trim();
        String rygemateriale = txfRøgelse.getText().trim();
        String kommentar = txfKommentar.getText().trim();
        int alkoholprocent;
        int mængde;

        if (newMake.isEmpty() || batch == null || ansvarlig == null
                || startDato == null || slutDato == null || alkoholProcentTekst.isEmpty() || mængdeTekst.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Manglende information");
            alert.setHeaderText("Husk at udfylde alle felter");
            alert.setContentText("Alle felter pånær 'Rygemateriale' og 'kommentar' skal udfyldes");

            alert.showAndWait();
            return;

        }
        try {
            alkoholprocent = Integer.parseInt(alkoholProcentTekst);

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Alkoholprocenten' skal være tal");

            alert.showAndWait();
            return;

        }
        try {
            mængde = Integer.parseInt(mængdeTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("'Mængde' skal være tal");
            alert.showAndWait();
            return;
        }

        Controller.registrerDestillering(startDato, slutDato, mængde, alkoholprocent, rygemateriale, kommentar, batch, ansvarlig);
        updateRegistreredeDestilleringer();

        dpStartDato.setValue(null);
        dpSlutDato.setValue(null);
        txfMængde.clear();
        txfAlkoProcent.clear();
        txfRøgelse.clear();
        txfKommentar.clear();
        cbBatch.setValue(null);
        cbAnsvarlig.setValue(null);
    }


}

