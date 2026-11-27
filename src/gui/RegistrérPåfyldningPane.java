package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Destillering;
import model.Fad;
import model.Påfyldning;
import org.w3c.dom.Text;
import storage.Storage;

import java.time.LocalDate;

public class RegistrérPåfyldningPane extends GridPane {
    ComboBox<Destillering> cbDestillering;
    TextField txfPåfyldningsID;
    ComboBox<Fad> cbFad;
    DatePicker dpPåfyldningsdato;
    TextField txfAntalLiter;
    TextField txfAlkoProcent;
    ListView<Påfyldning> lvwRegisteredePåfyldninger;

    public RegistrérPåfyldningPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift og infobesked
        Label lblRegistrérPåfyldning = new Label("Registrér påfyldning");
        add(lblRegistrérPåfyldning, 0, 0);
        Label lblInfo = new Label("Vælg en eksisterende destillering og det fad, der skal hældes på.");
        add(lblInfo, 0, 1, 2, 1);

        // Overskriftens farve, tykkelse og størrelse
        lblRegistrérPåfyldning.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for destillering, påfyldningsID, fad, påfyldningsdato, antal liter og alkoholprocent samt registrerede påfyldninger
        Label lblDestillering = new Label("Destillering (New Make)");
        Label lblPåfyldningsID = new Label ("PåfyldningsID");
        Label lblFad = new Label("Fad");
        Label lblPåfyldningsdato = new Label("Påfyldningsdato");
        Label lblAntalLiter = new Label("Antal liter");
        Label lblAlkoProcent = new Label("Alkoholprocent (%)");
        Label lblRegistreredePåfyldnigner = new Label("Registrerede påfyldninger");
        lblRegistreredePåfyldnigner.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");
        add(lblDestillering, 0, 3);
        add(lblPåfyldningsID,1,3);
        add(lblFad, 0, 5);
        add(lblPåfyldningsdato, 1, 7);
        add(lblAntalLiter, 0, 7);
        add(lblAlkoProcent, 0, 9);
        add(lblRegistreredePåfyldnigner, 5, 0);

        //Textfelter, ComboBoxes og DatePicker for destillering, påfyldningsID, fad, påfyldningsdato, antal liter og alkoholprocent samt listview og registrerede påfyldninger
        cbDestillering = new ComboBox<>();
        txfPåfyldningsID = new TextField();
        cbFad = new ComboBox<>();
        dpPåfyldningsdato = new DatePicker();
        txfAntalLiter = new TextField();
        txfAlkoProcent = new TextField();
        lvwRegisteredePåfyldninger = new ListView<>();
        add(cbDestillering, 0, 4);
        add(txfPåfyldningsID,1,4);
        add(cbFad, 0, 6,3,1);
        add(dpPåfyldningsdato, 1, 8);
        add(txfAntalLiter, 0, 8);
        add(txfAlkoProcent, 0, 10);
        add(lvwRegisteredePåfyldninger, 4, 1, 2, 12);
        lvwRegisteredePåfyldninger.setPrefHeight(400);


        //Button for registrering
        Button btnRegistrér = new Button("Registrér");
        add(btnRegistrér, 3, 12);
        btnRegistrér.setOnAction(event -> registrérPåfyldningAction());
        updateDestilleringComboBox();
        updatePåfyldningListView();
        updateFadComboBox();
    }

    private void updatePåfyldningListView() {
        //der er ikke .getPåfyldninger i storage, så kan ikke lave et listview over disse
        lvwRegisteredePåfyldninger.getItems().setAll(Storage.getPåfyldninger());
    }

    private void updateDestilleringComboBox() {
        cbDestillering.getItems().setAll(Storage.getDestilleringer());
    }

    private void updateFadComboBox() {
        cbFad.getItems().setAll(Storage.getFade());
    }

    public void registrérPåfyldningAction() {
        Destillering destillering = cbDestillering.getValue();
        Fad fad = cbFad.getValue();
        String antalLiterTekst = txfAntalLiter.getText().trim();
        LocalDate påfyldningsdato = dpPåfyldningsdato.getValue();
        String alkoProcentTekst = txfAlkoProcent.getText().trim();
        String påfyldningsIDTekst = txfPåfyldningsID.getText().trim();

        int antalLiter;
        int alkoProcent;
        int påfyldningsID;

        if (destillering == null || fad == null || antalLiterTekst.isEmpty() || påfyldningsdato == null || alkoProcentTekst.isEmpty() || påfyldningsIDTekst.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Manglende information");
            alert.setHeaderText("Husk at udfylde alle felter");
            alert.setContentText("Udfyld felterne destillering, påfyldningsID, fad, antal liter, påfyldningsdato og alkoholprocent");
            alert.showAndWait();
            return;
        }

        try {
            antalLiter = Integer.parseInt(antalLiterTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Antal liter' skal være et tal");

            alert.showAndWait();
            return;
        }
        try {
            alkoProcent = Integer.parseInt(alkoProcentTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Alkoholprocent' skal være et tal");

            alert.showAndWait();
            return;
        }
        try{
            påfyldningsID = Integer.parseInt(påfyldningsIDTekst);
        }catch(NumberFormatException e ){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'PåfyldningsID' skal være et tal");

            alert.showAndWait();
            return;
        }
        Controller.registrerPåfyldning(påfyldningsID, påfyldningsdato, antalLiter, alkoProcent,  fad, destillering);
        updatePåfyldningListView();

        txfPåfyldningsID.clear();
        txfAlkoProcent.clear();
        txfAntalLiter.clear();
        cbFad.setValue(null);
        cbDestillering.setValue(null);
        dpPåfyldningsdato.setValue(null);
    }
}
