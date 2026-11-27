package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Whisky;
import storage.Storage;

public class VisWhiskyproduktPane extends GridPane {
    TableView<Whisky> tbvwIDNavn;
    public VisWhiskyproduktPane(){
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

        //TableView for produkt-id og navn
        tbvwIDNavn = new TableView<>();
        TableColumn<Whisky, Integer> colID = new TableColumn<>("ID");
        TableColumn<Whisky, String> colNavn = new TableColumn<>("Navn");

        colID.setCellValueFactory(new PropertyValueFactory<>("produktId"));
        colNavn.setCellValueFactory(new PropertyValueFactory<>("navn"));

        tbvwIDNavn.getColumns().add(colID);
        tbvwIDNavn.getColumns().add(colNavn);
        add(tbvwIDNavn,0,3,5,4);
        updateWhiskyTableView();

    }

    private void updateWhiskyTableView(){
        tbvwIDNavn.getItems().setAll(Storage.getWhiskyer());
    }
}
