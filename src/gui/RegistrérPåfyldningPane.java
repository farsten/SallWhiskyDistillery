package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;

public class RegistrérPåfyldningPane extends GridPane {
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
        add(lblInfo,0,1);

        // Overskriftens farve, tykkelse og størrelse
        lblRegistrérPåfyldning.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for destillering, fad, påfyldningsdato, antal liter og alkoholprocent
        Label lblDestillering = new Label("Destillering (New Make)");
        Label lblFad = new Label ("Fad");
        Label lblPåfyldningsdato = new Label("Påfyldningsdato");
        Label lblAntalLiter = new Label("Antal liter");
        Label lblAlkoProcent = new Label("Alkoholprocent (%)");
        add(lblDestillering,0,3);
        add(lblFad,0,5);
        add(lblPåfyldningsdato,2,7);
        add(lblAntalLiter, 0,7);
        add(lblAlkoProcent,0,9);

        //Textfelter, ComboBoxes og DatePicker for destillering, fad, påfyldningsdato, antal liter og alkoholprocent
        ComboBox<String> cbDestillering = new ComboBox<>();
        ComboBox<String> cbFad= new ComboBox<>();
        DatePicker dpPåfyldningsdato = new DatePicker();
        TextField txfAntalLiter = new TextField();
        TextField txfAlkoProcent = new TextField();
        add(cbDestillering,0,4);
        add(cbFad,0,6);
        add(dpPåfyldningsdato,2,8);
        add(txfAntalLiter,0,8);
        add(txfAlkoProcent,0,10);

        //Button for regristrering
        Button btnRegistrér = new Button("Registrér");
        add(btnRegistrér,3,11);
    }
}
