package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class OpretWhiskyprodukt extends GridPane {
    public OpretWhiskyprodukt(){
        initContent();
    }

    private void initContent(){
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //Overskrift
        Label lblOpretWhisky = new Label("Opret whiskyprodukt");
        add(lblOpretWhisky,0,0,2,1);
        // Overskriftens farve, tykkelse og størrelse
        lblOpretWhisky.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Label for produkt-ID, whiskynavn, vælg fad til blanding, mængde fra fad, endelig alkoholprocent
        Label lblProduktID = new Label("Produkt-ID");
        Label lblWhiskyNavn = new Label("Whiskynavn");
        Label lblVælgFad = new Label ("Vælg fad(e) til blanding");
        Label lblFad1 = new Label("Fad 1 (skal udfyldes)");
        Label lblFad2 = new Label("Fad 2 (kan udfyldes)");
        Label lblFad3 = new Label("Fad 3 (kan udfyldes)");
        Label lblMængdeFad = new Label("Mængde fra fad (liter)");
        Label lblAlkoholProcent = new Label("Endelig alkoholprocent (%)");
        add(lblProduktID,0,2);
        add(lblWhiskyNavn,2,2);
        add(lblVælgFad,0,5);
        add(lblFad1,0,6);
        add(lblFad2,2,6);
        add(lblFad3,4,6);
        add(lblMængdeFad,0,8);
        add(lblAlkoholProcent,0,10);

        /*Textfields og comboboxes produkt-ID, whiskynavn, vælg fad til blanding,
        mængde fra fad, endelig alkoholprocent */
        TextField txfProduktID = new TextField();
        TextField txfWhiskyNavn = new TextField();
        ComboBox<String> cbFad1 = new ComboBox<>();
        ComboBox<String> cbFad2 = new ComboBox<>();
        ComboBox<String> cbFad3 = new ComboBox<>();
        TextField txfMængde = new TextField();
        TextField txfAlkoProcent = new TextField();
        add(txfProduktID,0,3);
        add(txfWhiskyNavn,2,3);
        add(cbFad1,0,7);
        add(cbFad2,2,7);
        add(cbFad3,4,7);
        add(txfMængde,0,9);
        add(txfAlkoProcent,0,11);

        //Button til oprettelse
        Button btnOpret = new Button("Opret");
        add(btnOpret,5,12);

    }
}
