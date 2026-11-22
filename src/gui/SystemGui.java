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
        Label lblTitle = new Label("Sall Whisky                                          Destilleriets IT-System");
        HBox topBox = new HBox(lblTitle);
        pane.add(topBox, 0,0);
        topBox.setStyle("-fx-background-color: #B34D1E;");
        lblTitle.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        // Tabs
        TabPane mainTabPane = new TabPane();
        String produktionsTabString = "Produktion";
        Tab produktionTab = new Tab(produktionsTabString);
        Tab fadeTab = new Tab("Fade og lager");
        Tab whiskyTab = new Tab("Whiskyprodukter");
        Tab historikTab = new Tab("Historik");
        mainTabPane.getTabs().addAll(produktionTab, fadeTab, whiskyTab, historikTab);
        pane.add(mainTabPane,0,1);

        // Vælg Produktion som standard
        mainTabPane.getSelectionModel().select(produktionTab);

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
        Tab registrérFadplaceringTab = new Tab("Registrér påfyldning");

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
