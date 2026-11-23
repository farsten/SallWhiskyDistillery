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

        Scene scene = new Scene(pane, 900, 400);
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
        Label lblTitle = new Label("Sall Whisky                                               Destilleriets IT-System");
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
        GridPane markGridPane = new GridPane();
        markGridPane.setPadding(new Insets(20));
        markGridPane.setHgap(10);
        markGridPane.setVgap(10);
        registrérMarkTab.setContent(markGridPane);

        //overskrift
        Label lblRegistrérMark = new Label ("Registrér mark");
        markGridPane.add(lblRegistrérMark,0,0);
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
        markGridPane.add(txfMarkNavn,4,2);

        //Registrér button
        Button btnRegistrér = new Button("Registrér");
        markGridPane.add(btnRegistrér,5,3);


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
