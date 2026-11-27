package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Fad;
import storage.Storage;

import javax.swing.text.LabelView;
import java.util.ArrayList;
import java.util.List;

public class RegistrérFadPane extends GridPane {
    TextField txfFadnummer;
    ComboBox<String> cbFadtype;
    TextField txfKapacitet;
    TextField txfTidligereIndhold;
    TextField txfLeverandør;
   // TextField txfSlutMængde;
    ListView<Fad> lvwFade;


    public RegistrérFadPane() {
        initContent();
    }

    private void initContent() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //overskrift
        Label lblRegistrérFad = new Label("Registrér fad");
        add(lblRegistrérFad, 0, 0);

        //Overskriftens farve, tykkelse og størrelse
        lblRegistrérFad.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for fadnummer, fadtype, kapacitet, tidligere indhold og leverandør samt registrerede fade
        Label lblFadnummer = new Label("Fadnummer");
        Label lblFadtype = new Label("Fadtype");
        Label lblKapacitet = new Label("Kapacitet (liter)");
        Label lblTidligereIndhold = new Label("Tidligere indhold");
        Label lblLeverandør = new Label("Leverandør");
        Label lblRegistreredeFade = new Label("Registrerede fade");
        lblRegistreredeFade.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");
        add(lblFadnummer, 0, 1);
        add(lblFadtype, 0, 3);
        add(lblKapacitet, 3, 3);
        add(lblTidligereIndhold, 0, 5);
        add(lblLeverandør, 3, 5);
        add(lblRegistreredeFade,6,0);

        //Textfield og ComboBox for fadnummer, fadtype, kapacitet, tidligere indhold og leverandør samt Listview over fade
        txfFadnummer = new TextField();
        cbFadtype = new ComboBox<>();
        txfKapacitet = new TextField();
        txfTidligereIndhold = new TextField();
        txfLeverandør = new TextField();
        lvwFade = new ListView<>();
        add(txfFadnummer, 0, 2, 4, 1);
        add(cbFadtype, 0, 4);
        add(txfKapacitet, 3, 4);
        add(txfTidligereIndhold, 0, 6);
        add(txfLeverandør, 3, 6);
        add(lvwFade,6,1,4,8);
        lvwFade.setPrefSize(500,500);

        //Button for registrering
        Button btnRegistrér = new Button("Registrér");
        add(btnRegistrér, 4, 7);
        btnRegistrér.setOnAction(event -> registrérFadAction());
        updateFadTypeComboBox();
        updateFadListView();
    }

    private void updateFadTypeComboBox() {
        cbFadtype.getItems().setAll(Storage.getFade().stream().map(fad -> fad.getType()).distinct().toList());
    }
    private void updateFadListView(){
        lvwFade.getItems().setAll(Storage.getFade());
    }

    public void registrérFadAction(){
        String fadnummerTekst = txfFadnummer.getText().trim();
        String fadtype = cbFadtype.getValue();
        String kapacitetTekst = txfKapacitet.getText().trim();
        String tidligereIndhold = txfTidligereIndhold.getText().trim();
        String leverandør = txfLeverandør.getText().trim();
        int fadnummer;
        int kapacitet;

        if ( fadnummerTekst.isEmpty() || fadtype == null || kapacitetTekst.isEmpty() || tidligereIndhold.isEmpty() ||leverandør.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Manglende information");
            alert.setHeaderText("Husk at udfylde alle felter");
            alert.setContentText("Udfyld felterne fadnummer, fadtype, kapacitet, tidligere indhold og leverandør");
            alert.showAndWait();
            return;
        }
        try{
            fadnummer = Integer.parseInt(fadnummerTekst);
        } catch (NumberFormatException e ){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Fadnummer' skal være et tal");

            alert.showAndWait();
            return;

        }
        try{
            kapacitet = Integer.parseInt(kapacitetTekst);
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("'Kapacitet' skal være et tal");

            alert.showAndWait();
            return;
        }
        Controller.opretFad(fadnummer,kapacitet,leverandør,tidligereIndhold,fadtype);
        updateFadListView();

        txfFadnummer.clear();
        txfKapacitet.clear();
        txfLeverandør.clear();

        txfTidligereIndhold.clear();
        cbFadtype.setValue(null);

    }
}

