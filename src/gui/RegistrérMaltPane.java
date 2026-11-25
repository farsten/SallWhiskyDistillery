package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.MaltBatch;
import model.Mark;
import storage.Storage;

import java.time.LocalDate;

public class RegistrérMaltPane extends GridPane {
    TextField txfBatchID;
    DatePicker dpHøstDato;
    TextField txfKornsort;
    ComboBox<Mark> cbMarker;
    ListView<MaltBatch> lvwBatches = new ListView();

    public RegistrérMaltPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //Overskrift
        Label lblRegistrérMaltBatch = new Label("Registrér maltbatch");
        add(lblRegistrérMaltBatch, 0, 0, 2, 1);
        lblRegistrérMaltBatch.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for batch-id, høstdato, kornsort og "vælg mark"
        Label lblBatchID = new Label("Batch-ID");
        add(lblBatchID, 0, 1);

        Label lblHøstDato = new Label("Høstdato");
        add(lblHøstDato, 3, 1);

        Label lblKortsort = new Label("Kornsort");
        add(lblKortsort, 3, 4);

        Label lblVælgMark = new Label("Vælg mark");
        add(lblVælgMark, 0, 4);

        //BatchID texfield, Høstdato datepicker, Textfield for kornsort og VælgMark combobox
        txfBatchID = new TextField();
        add(txfBatchID, 0, 2);

        dpHøstDato = new DatePicker();
        add(dpHøstDato, 3, 2);

        txfKornsort = new TextField();
        add(txfKornsort, 3, 5);

        cbMarker = new ComboBox<>();
        add(cbMarker, 0, 5);

        //RegistrérMalt button
        Button btnRegistrérMalt = new Button("Registrér");
        add(btnRegistrérMalt, 4, 6);

        Label lblRegistreredeBatches = new Label("Registrerede maltbatches");
        add(lblRegistreredeBatches, 6, 0);
        add(lvwBatches, 6, 1, 2, 6);
        lvwBatches.setPrefHeight(200);

        btnRegistrérMalt.setOnAction(event -> registrérBatchAction());
        updateRegistreredeBatches();
        updateVælgMarkComboBox();
        //____________________________________________________________

    }

    private void updateRegistreredeBatches() {
        lvwBatches.getItems().setAll(Storage.getMaltBatch());
    }

    private void updateVælgMarkComboBox() {
        cbMarker.getItems().setAll(Storage.getMarker());
    }

    public void registrérBatchAction() {
        String batchIDTekst = txfBatchID.getText().trim();
        LocalDate høstDato = dpHøstDato.getValue();
        Mark mark = cbMarker.getValue();
        String kornsort = txfKornsort.getText().trim();
        int batchID;
        if (batchIDTekst.isEmpty() || høstDato == null || mark == null || kornsort.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Manglende information");
            alert.setHeaderText("Husk at udfylde alle felter");
            alert.setContentText("Udfyld batch-ID, høstdato, kornsort og vælg mark");

            alert.showAndWait();
            return;
        }
        try {
            batchID = Integer.parseInt(batchIDTekst);
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Batch-ID' skal være tal");

            alert.showAndWait();
            return;

        }
        Controller.opretMaltBatch(batchID, kornsort, høstDato, mark);
        updateRegistreredeBatches();

    }

}
