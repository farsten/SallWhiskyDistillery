package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Mark;
import storage.Storage;

public class RegistrérMarkPane extends GridPane {
    TextField txfMarkID;
    TextField txfMarkNavn;
    ListView<Mark> lvwMarker = new ListView();

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
        txfMarkID = new TextField();
        txfMarkNavn = new TextField();
        add(txfMarkID, 0, 2);
        add(txfMarkNavn, 3, 2);

        //Registrér button
        Button btnRegistrérMark = new Button("Registrér");
        add(btnRegistrérMark, 4, 5);
        btnRegistrérMark.setOnAction(event -> opretSommerhusAction());

        Label lblMarker = new Label("Registrerede marker");
        lvwMarker.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");
        add(lblMarker,6,0);
        add(lvwMarker,6,1,2,6);
        lvwMarker.setPrefHeight(200);
        updateMarkListView();
    }
    private void updateMarkListView(){
        lvwMarker.getItems().setAll(Storage.getMarker());
    }
    public void opretSommerhusAction() {
        String markIDTekst = txfMarkID.getText().trim();
        String markNavn = txfMarkNavn.getText().trim();
        int markID;


        if (markIDTekst.isEmpty() || markNavn.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Manglende information");
            alert.setHeaderText("Husk at udfylde alle felter");
            alert.setContentText("Udfyld mark-ID og markens navn");

            alert.showAndWait();
            return;
        }
        try {
            markID = Integer.parseInt(markIDTekst);
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'mark-ID' skal være tal");

            alert.showAndWait();
            return;

        }
        Controller.opretMark(markID, markNavn);
        updateMarkListView();

    }
}

