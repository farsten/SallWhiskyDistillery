package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class SystemGui extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Sall Whisky                                                                                           Destilleriets IT-system");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    private void initContent(GridPane pane) {
        //show or hide grid lines
        pane.setGridLinesVisible(true);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        //overskrift
        Label lblTitle = new Label("Sall Whisky                                        Destilleriets IT-System");
        HBox topBox = new HBox(lblTitle);
        pane.add(topBox, 0,0);
        topBox.setStyle("-fx-background-color: #B34D1E;");
        lblTitle.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        // Tabs
        TabPane mainTabPane = new TabPane();
        Tab produktionTab = new Tab("Produktion");
        Tab fadeTab = new Tab("Fade og lager");
        Tab whiskyTab = new Tab("Whiskyprodukter");
        Tab historikTab = new Tab("Historik");
        mainTabPane.getTabs().addAll(produktionTab, fadeTab, whiskyTab, historikTab);
        pane.add(mainTabPane,0,1);

        //Baggrundsfarve
        mainTabPane.setStyle("-fx-background-color: #F8F1EB;");

        // Vælg Produktion som standard
        mainTabPane.getSelectionModel().select(produktionTab);

        //Sørg for at tabs ikke kan lukkes ned/krydses
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Anvend farver
        farverPåTabs(produktionTab);
        farverPåTabs(fadeTab);
        farverPåTabs(whiskyTab);
        farverPåTabs(historikTab);

        // Tabs inde i produktionstab'en
        TabPane produktionTabPane = new TabPane();

        Tab registrérMarkTab = new Tab("Registrér mark");
        Tab registrérMaltTab = new Tab("Registrér maltbatch");
        Tab registrérDestilleringTab = new Tab("Registrér destillering");

        produktionTabPane.getTabs().addAll(registrérMarkTab, registrérMaltTab, registrérDestilleringTab);
        produktionTab.setContent(produktionTabPane);
        produktionTabPane.getSelectionModel().select(registrérMarkTab);
        produktionTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        farverPåTabs(registrérMarkTab);
        farverPåTabs(registrérMaltTab);
        farverPåTabs(registrérDestilleringTab);

        //tabs inde i "Fade og lager"-tab'en
        TabPane fadeTabPane = new TabPane();
        Tab registrérFadTab = new Tab("Registrér fad");
        Tab registrérPåfyldningTab = new Tab("Registrér påfyldning");
        Tab registrérFadplaceringTab = new Tab("Registrér fadplacering");

        fadeTabPane.getTabs().addAll(registrérFadTab, registrérPåfyldningTab, registrérFadplaceringTab);
        fadeTab.setContent(fadeTabPane);
        fadeTabPane.getSelectionModel().select(registrérFadTab);
        fadeTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        farverPåTabs(registrérFadTab);
        farverPåTabs(registrérPåfyldningTab);
        farverPåTabs(registrérFadplaceringTab);

        //tabs inde i "Whiskyprodukter"-tab'en
        TabPane whiskyTabPane = new TabPane();
        Tab opretWhiskyprodukt = new Tab("Opret whiskyprodukt");
        Tab visWhiskyprodukter = new Tab("Vis whiskyprodukter");
        whiskyTabPane.getTabs().addAll(opretWhiskyprodukt, visWhiskyprodukter);
        whiskyTab.setContent(whiskyTabPane);
        whiskyTabPane.getSelectionModel().select(opretWhiskyprodukt);
        whiskyTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        farverPåTabs(opretWhiskyprodukt);
        farverPåTabs(visWhiskyprodukter);

        //Registrér mark tab
        //________________________________________________________________
        GridPane markGridPane = new GridPane();
        markGridPane.setPadding(new Insets(20));
        markGridPane.setHgap(10);
        markGridPane.setVgap(10);
        markGridPane.setPrefHeight(200);
        registrérMarkTab.setContent(markGridPane);

        //overskrift
        Label lblRegistrérMark = new Label ("Registrér mark");
        markGridPane.add(lblRegistrérMark,0,0);

        // Overskriftens farve, tykkelse og størrelse
        lblRegistrérMark.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //labels for mark-ID og mark-navn
        Label lblMarkID = new Label("Mark-ID");
        Label lblMarkNavn = new Label("Markens navn");
        markGridPane.add(lblMarkID,0,1);
        markGridPane.add(lblMarkNavn,3,1);

        //Textfields for mark-ID og mark-navn
        TextField txfMarkID = new TextField();
        TextField txfMarkNavn= new TextField();
        markGridPane.add(txfMarkID,0,2);
        markGridPane.add(txfMarkNavn,3,2);

        //Registrér button
        Button btnRegistrérMark = new Button("Registrér");
        markGridPane.add(btnRegistrérMark,4,5);
        //________________________________________________________________________


        //Registrér malt tab
        //________________________________________________________________________

        GridPane maltGridPane = new GridPane();
        maltGridPane.setPadding(new Insets(20));
        maltGridPane.setHgap(10);
        maltGridPane.setVgap(10);
        maltGridPane.setPrefHeight(250);
        registrérMaltTab.setContent(maltGridPane);

        //Overskrift
        Label lblRegistrérMaltBatch = new Label("Registrér maltbatch");
        maltGridPane.add(lblRegistrérMaltBatch, 0,0);
        lblRegistrérMaltBatch.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for batch-id, høstdato og "vælg mark"
        Label lblBatchID = new Label("Batch-ID");
        maltGridPane.add(lblBatchID,0,1);

        Label lblHøstDato = new Label("Høstdato");
        maltGridPane.add(lblHøstDato,3,1);

        Label lblVælgMark = new Label("Vælg mark");
        maltGridPane.add(lblVælgMark,0,4);

        //BatchID texfield, Høstdato datepicker, VælgMark combobox
        TextField txfBatchID = new TextField();
        maltGridPane.add(txfBatchID,0,2);

        DatePicker dpHøstDato = new DatePicker();
        maltGridPane.add(dpHøstDato,3,2);

        ComboBox<String> cbVælgMark = new ComboBox<>();
        maltGridPane.add(cbVælgMark,0,5);

        //RegistrérMalt button
        Button btnRegistrérMalt = new Button("Registrér");
        maltGridPane.add(btnRegistrérMalt, 4,5);
        //____________________________________________________________

        //Registrér destillering
        //____________________________________________________________
        GridPane destilleringGridPane = new GridPane();
        destilleringGridPane.setPadding(new Insets(20));
        destilleringGridPane.setHgap(10);
        destilleringGridPane.setVgap(10);
        destilleringGridPane.setPrefHeight(250);
        registrérDestilleringTab.setContent(destilleringGridPane);

        //overskrift
        Label lblRegistrérDestillering = new Label("Registrér destillering");
        destilleringGridPane.add(lblRegistrérDestillering,0,0);
        lblRegistrérDestillering.setStyle("-fx-text-fill: black;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        //Labels for newMakeNummer, maltbatch, destilleringsansvarlig, startdato, slutdato, alkoholprocent og mængde
        Label lblNMNummer = new Label("New Make-nummer");
        destilleringGridPane.add(lblNMNummer,0,1);

        Label lblMaltBatch = new Label("Vælg maltbatch");
        destilleringGridPane.add(lblMaltBatch,0,3);

        Label lblDestilleringsAnsvarlig = new Label("Destilleringsansvarlig");
        destilleringGridPane.add(lblDestilleringsAnsvarlig, 2,3);

        Label lblStartDato = new Label("Startdato");
        destilleringGridPane.add(lblStartDato,0,5);

        Label lblSlutdato = new Label("Slutdato");
        destilleringGridPane.add(lblSlutdato,2,5);

        Label lblAlkoProcent = new Label("Alkoholprocent (%)");
        destilleringGridPane.add(lblAlkoProcent,0,7);

        Label lblMængde = new Label("Mængde (liter)");
        destilleringGridPane.add(lblMængde,2,7);

        /*Tekstfelter, ComboBoxes, DatePickers for
          newMakeNummer, maltbatch, destilleringsansvarlig, startdato, slutdato, alkoholprocent og mængde*/

        TextField txfNMNummer = new TextField();
        destilleringGridPane.add(txfNMNummer,0,2,4,1);

        ComboBox<String> cbMaltBatch = new ComboBox<>();
        destilleringGridPane.add(cbMaltBatch, 0,4);

        ComboBox<String> cbAnsvarlig = new ComboBox<>();
        destilleringGridPane.add(cbAnsvarlig,2,4);

        DatePicker dpStartDato = new DatePicker();
        destilleringGridPane.add(dpStartDato, 0,6);

        DatePicker dpSlutdato = new DatePicker();
        destilleringGridPane.add(dpSlutdato, 2,6);

        TextField txfAlkoProcent = new TextField();
        destilleringGridPane.add(txfAlkoProcent,0,8);

        TextField txfMængde = new TextField();
        destilleringGridPane.add(txfMængde,2,8);

        //Registrér button
        Button btnRegistrérDestillering = new Button("Registrér");
        btnRegistrérDestillering.setStyle("-fx-font-size: 10px;");
        destilleringGridPane.add(btnRegistrérDestillering, 4,9);


    }

    private void farverPåTabs(Tab tab) {
        Label label = new Label(tab.getText());
        tab.setText(null);
        tab.setGraphic(label);

        // produktionstab har farve, når programmet starter
        if (tab.isSelected()) {
            label.setStyle("-fx-font-weight: bold;" + "-fx-text-fill: #B96F49;");
        } else {
            label.setStyle("-fx-font-weight: normal;" + "-fx-text-fill: black;");
        }

        // Farven skifter når brugeren skifter fane
        tab.selectedProperty().addListener((obs, oldValue, isSelected) -> {
            if (isSelected) {
                label.setStyle("-fx-font-weight: bold;" + "-fx-text-fill: #B96F49;");
            } else {
                label.setStyle("-fx-font-weight: normal;" + "-fx-text-fill: black;");
            }
        });
    }

}
