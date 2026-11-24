package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class VisHistorikPane extends GridPane {
    public VisHistorikPane(){
        initContent();
    }
    private void initContent(){
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift
        Label lblHistorik =  new Label("Vis whiskyhistorik");
        add(lblHistorik,0,0);
        // Overskriftens farve, tykkelse og størrelse
        lblHistorik.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for Vælg whiskuprodukt, indtast produkt-id, historik
        Label lblVælgWhisky = new Label("Vælg whiskyprodukt");
        Label lblProduktID = new Label("Eller indtast produkt-ID");
        add(lblVælgWhisky,0,2);
        add(lblProduktID,0,4);

        //ComboBox og TextField for Vælg whisky og indtast produkt-id
        ComboBox<String> cbVælgWhisky = new ComboBox<>();
        TextField txfProduktID = new TextField();
        add(cbVælgWhisky,0,3);
        add(txfProduktID,0,5);

        //Button for søgning af produkt-ID
        Button btnSøg = new Button("Søg");
        add(btnSøg,2,5);

        //TextArea for historik
        TextArea txaHistorik = new TextArea();
        add(txaHistorik,0,6);
        txaHistorik.setPrefWidth(200);
        txaHistorik.setEditable(false);


    }
}
