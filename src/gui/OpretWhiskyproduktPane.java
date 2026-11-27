package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Fad;
import storage.Storage;

public class OpretWhiskyproduktPane extends GridPane {
    TextField txfProduktID;
    TextField txfWhiskyNavn;
    ComboBox<Fad> cbFad1;
    ComboBox<Fad> cbFad2;
    ComboBox<Fad> cbFad3;
    TextField txfMængde1;
    TextField txfMængde2;
    TextField txfMængde3;
    TextField txfAlkoProcent;

    public OpretWhiskyproduktPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //Overskrift
        Label lblOpretWhisky = new Label("Opret whiskyprodukt");
        add(lblOpretWhisky, 0, 0, 2, 1);
        // Overskriftens farve, tykkelse og størrelse
        lblOpretWhisky.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Label for produkt-ID, whiskynavn, vælg fad til blanding, mængde fra fad, endelig alkoholprocent
        Label lblProduktID = new Label("Produkt-ID");
        Label lblWhiskyNavn = new Label("Whiskynavn");
        Label lblVælgFad = new Label("Vælg fad(e) til blanding");
        Label lblFad1 = new Label("Fad 1 (skal udfyldes)");
        Label lblFad2 = new Label("Fad 2 (kan udfyldes)");
        Label lblFad3 = new Label("Fad 3 (kan udfyldes)");
        Label lblMængdeFad1 = new Label("Mængde fra fad 1 (liter)");
        Label lblMængdeFad2 = new Label("Mængde fra fad 2 (liter)");
        Label lblMængdeFad3 = new Label("Mængde fra fad 3 (liter)");
        Label lblAlkoholProcent = new Label("Endelig alkoholprocent (%)");
        add(lblProduktID, 0, 2);
        add(lblWhiskyNavn, 2, 2);
        add(lblVælgFad, 0, 5);
        add(lblFad1, 0, 6);
        add(lblFad2, 2, 6);
        add(lblFad3, 4, 6);
        add(lblMængdeFad1, 0, 8);
        add(lblMængdeFad2, 2, 8);
        add(lblMængdeFad3, 4, 8);
        add(lblAlkoholProcent, 0, 10);

        /*Textfields og comboboxes produkt-ID, whiskynavn, vælg fad til blanding,
        mængde fra fad, endelig alkoholprocent */
        txfProduktID = new TextField();
        txfWhiskyNavn = new TextField();
        cbFad1 = new ComboBox<>();
        cbFad2 = new ComboBox<>();
        cbFad3 = new ComboBox<>();
        txfMængde1 = new TextField();
        txfMængde2 = new TextField();
        txfMængde3 = new TextField();
        txfAlkoProcent = new TextField();
        add(txfProduktID, 0, 3);
        add(txfWhiskyNavn, 2, 3);
        add(cbFad1, 0, 7, 1, 1);
        cbFad1.setPrefWidth(250);
        cbFad2.setPrefWidth(250);
        cbFad3.setPrefWidth(250);
        add(cbFad2, 2, 7, 1, 1);
        add(cbFad3, 4, 7, 1, 1);
        add(txfMængde1, 0, 9);
        add(txfMængde2, 2, 9);
        add(txfMængde3, 4, 9);
        add(txfAlkoProcent, 0, 11);

        //Button til oprettelse
        Button btnOpret = new Button("Opret");
        add(btnOpret, 5, 12);
        updateFadComboBox();
    }

    private void updateFadComboBox() {
        cbFad1.getItems().setAll(Storage.getFade());
        cbFad2.getItems().setAll(Storage.getFade());
        cbFad3.getItems().setAll(Storage.getFade());
    }

    public void opretWhiskyAction() {
        String produktIDTekst = txfProduktID.getText().trim();
        String navn = txfWhiskyNavn.getText().trim();
        Fad fad1 = cbFad1.getValue();
        Fad fad2 = cbFad2.getValue();
        Fad fad3 = cbFad3.getValue();
        String mængde1Tekst = txfMængde1.getText().trim();
        String mængde2Tekst = txfMængde2.getText().trim();
        String mængde3Tekst = txfMængde3.getText().trim();
        String alkoProcentTekst = txfAlkoProcent.getText().trim();
        int produktID;
        int mængde1;
        int mængde2;
        int mængde3;
        int alkoProcent;

        if (produktIDTekst.isEmpty() || navn.isEmpty() || fad1 == null || mængde1Tekst.isEmpty() || alkoProcentTekst.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Manglende information");
            alert.setHeaderText("Husk at udfylde de nødvendige felter");
            alert.setContentText("Alle felter pånær 'Rygemateriale' og 'kommentar' skal udfyldes");

            alert.showAndWait();
            return;
        }
        try {
            produktID = Integer.parseInt(produktIDTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Produkt-ID' skal være et tal");

            alert.showAndWait();
            return;
        }
        try {
            mængde1 = Integer.parseInt(mængde1Tekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Mængde 1' skal være et tal");

            alert.showAndWait();
            return;
        }
        try {
            mængde2 = Integer.parseInt(mængde2Tekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Mængde 2' skal være et tal");

            alert.showAndWait();
            return;
        }
        try {
            mængde3 = Integer.parseInt(mængde3Tekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Mængde 3' skal være et tal");

            alert.showAndWait();
            return;
        }
        try {
            alkoProcent = Integer.parseInt(alkoProcentTekst);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Alkoholprocenten' skal være et tal");

            alert.showAndWait();
            return;
        }
        //TODO
        //Snak om felter/attributter
       // Controller.opretWhisky(produktID, navn, alkoProcent, )
    }
}
