package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class VisWhiskyprodukt extends GridPane {
    public VisWhiskyprodukt(){
        initContent();
    }
    private void initContent(){
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift
        Label lblRegistreredeWhiskyProdukter = new Label("Registrerede whiskyprodukter");
        add(lblRegistreredeWhiskyProdukter, 0, 0,2,1);
        // Overskriftens farve, tykkelse og størrelse
        lblRegistreredeWhiskyProdukter.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for produkt-ID og navn
        Label lblProduktID = new Label("Produkt-ID");
        Label lblNavn = new Label("Navn");
        add(lblProduktID,0,2);
        add(lblNavn,2,2);

        //TableView for produkt-id og navn
        TableView<String> tvwIDNavn = new TableView<>();
        add(tvwIDNavn,0,3,5,4);

    }
}
