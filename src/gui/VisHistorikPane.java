package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Whisky;
import storage.Storage;

public class VisHistorikPane extends GridPane {
    ComboBox<Integer> cbVælgWhisky;
    TextField txfProduktID;

    public VisHistorikPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift
        Label lblHistorik = new Label("Vis whiskyhistorik");
        add(lblHistorik, 0, 0);
        // Overskriftens farve, tykkelse og størrelse
        lblHistorik.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for Vælg whiskuprodukt, indtast produkt-id, historik
        Label lblVælgWhisky = new Label("Vælg whiskyprodukt ud fra produkt-ID");
        add(lblVælgWhisky, 0, 2);

        Label lblHistorikTxa = new Label("Produktets historik");
        add(lblHistorikTxa, 0,4);
        //ComboBox og TextField for Vælg whisky og indtast produkt-id
        cbVælgWhisky = new ComboBox<>();
        txfProduktID = new TextField();
        add(cbVælgWhisky, 0, 3, 1, 1);
        cbVælgWhisky.setPrefWidth(250);

        //TextArea for historik
        TextArea txaHistorik = new TextArea();
        add(txaHistorik, 0, 5);
        txaHistorik.setPrefWidth(200);
        txaHistorik.setEditable(false);

        updateWhiskyComboBox();

    }

    private void updateWhiskyComboBox() {
        cbVælgWhisky.getItems().setAll(Storage.getWhiskyer().stream().map(whisky -> whisky.getProduktId()).toList()
        );

    }
}
