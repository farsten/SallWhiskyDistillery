package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RegistrérMaltPane extends GridPane {
    public RegistrérMaltPane() {
        initContent();
    }
    private void initContent(){
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        //Overskrift
        Label lblRegistrérMaltBatch = new Label("Registrér maltbatch");
        add(lblRegistrérMaltBatch, 0,0);
        lblRegistrérMaltBatch.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for batch-id, høstdato og "vælg mark"
        Label lblBatchID = new Label("Batch-ID");
        add(lblBatchID,0,1);

        Label lblHøstDato = new Label("Høstdato");
        add(lblHøstDato,3,1);

        Label lblVælgMark = new Label("Vælg mark");
        add(lblVælgMark,0,4);

        //BatchID texfield, Høstdato datepicker, VælgMark combobox
        TextField txfBatchID = new TextField();
        add(txfBatchID,0,2);

        DatePicker dpHøstDato = new DatePicker();
        add(dpHøstDato,3,2);

        ComboBox<String> cbVælgMark = new ComboBox<>();
        add(cbVælgMark,0,5);

        //RegistrérMalt button
        Button btnRegistrérMalt = new Button("Registrér");
        add(btnRegistrérMalt, 4,6);
        //____________________________________________________________

    }


}
