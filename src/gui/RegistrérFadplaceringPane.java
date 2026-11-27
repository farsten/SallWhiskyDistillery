package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Fad;
import model.Lager;
import model.Lagerplacering;
import storage.Storage;

public class RegistrérFadplaceringPane extends GridPane {
    ComboBox<Fad> cbFad;
    ComboBox<Lager> cbLager;
    TextField txfReol;
    TextField txfHylde;
    TextField txfPlads;
    ListView<Lagerplacering> lvwLagerplaceringer;

    public RegistrérFadplaceringPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift
        Label lblRegistrérFadplacering = new Label("Registrér fadplacering");
        lblRegistrérFadplacering.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");
        add(lblRegistrérFadplacering, 0, 0, 2, 1);

        //Labels for fad, lager, reol, hylde og plads
        Label lblFad = new Label("Vælg fad");
        Label lblLager = new Label("Vælg lager");
        Label lblHylde = new Label("Hylde");
        Label lblReol = new Label("Reol");
        Label lblPlads = new Label("Plads");
        Label lblRegistreredePlaceringer = new Label("Registrerede lagerplaceringer");
        lblRegistreredePlaceringer.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");
        add(lblFad, 0, 1);
        add(lblLager, 0, 3);
        add(lblReol, 0, 5);
        add(lblHylde, 1, 5);
        add(lblPlads, 0, 7);
        add(lblRegistreredePlaceringer, 5, 0);
        //ComboBoxes og tekstfelter for fad, lager, reol, hylde og plads
        cbFad = new ComboBox<>();
        cbLager = new ComboBox<>();
        txfHylde = new TextField();
        txfPlads = new TextField();
        txfReol = new TextField();
        lvwLagerplaceringer = new ListView<>();
        add(cbFad, 0, 2, 2, 1);
        add(cbLager, 0, 4, 2, 1);
        add(txfReol, 0, 6, 1, 1);
        add(txfHylde, 1, 6);
        add(txfPlads, 0, 8);
        add(lvwLagerplaceringer, 5, 1, 3, 10);

        Button btnRegistrér = new Button("Registrér");
        add(btnRegistrér, 3, 9);
        btnRegistrér.setOnAction(event -> registrérFadplaceringAction());
        updateFadComboBox();
        updateLagerComboBox();
        updateFadplaceringListView();
    }

    private void updateFadComboBox() {
        cbFad.getItems().setAll(Storage.getFade());
    }

    private void updateLagerComboBox() {
        cbLager.getItems().setAll(Storage.getLagre());
    }
    private void updateFadplaceringListView(){
        //TODO
        //Mangler getPlacering i storage

    }

    public void registrérFadplaceringAction() {
        Fad fad = cbFad.getValue();
        Lager lager = cbLager.getValue();
        String reolTekst = txfReol.getText().trim();
        String hyldeTekst = txfHylde.getText().trim();
        String pladsTekst = txfPlads.getText().trim();
        int reol;
        int hylde;
        int plads;

        if (fad == null || lager == null || reolTekst.isEmpty() || hyldeTekst.isEmpty() || pladsTekst.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Manglende information");
            alert.setHeaderText("Husk at udfylde alle felter");
            alert.setContentText("Udfyld felterne vælg fad, vælg lager, reol, hylde og plads");
            alert.showAndWait();
            return;
        }
        try {
            reol = Integer.parseInt(reolTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Reol' skal være et tal");

            alert.showAndWait();
            return;
        }
        try {
            hylde = Integer.parseInt(hyldeTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Hylde' skal være et tal");

            alert.showAndWait();
            return;
        }
        try {
            plads = Integer.parseInt(pladsTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Plads' skal være et tal");

            alert.showAndWait();
            return;
        }
        Controller.placerFad(fad, lager, reol, hylde, plads);
        updateFadplaceringListView();

    }
}
