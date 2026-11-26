package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SystemGui extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sall Whisky");

        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #F8F1EB;");

        initContent(pane);

        Scene scene = new Scene(pane, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(BorderPane pane) {

        // OVERSKRIFTSBJÆLKE
        Label lblTitle = new Label("Sall Whisky");
        Label lblSystem = new Label("Destilleriets IT-System");

        HBox topBox = new HBox();

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBox.getChildren().addAll(lblTitle, spacer, lblSystem);

        topBox.setPadding(new Insets(10));

        topBox.setStyle("-fx-background-color: #B34D1E;");

        lblTitle.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        lblSystem.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 18px;");

        pane.setTop(topBox);


        // HOVEDFANER
        TabPane mainTabPane = new TabPane();

        Tab produktionTab = new Tab("Produktion");
        Tab fadeTab = new Tab("Fade og lager");
        Tab whiskyTab = new Tab("Whiskyprodukter");
        Tab historikTab = new Tab("Historik");

        mainTabPane.getTabs().addAll(produktionTab, fadeTab, whiskyTab, historikTab);

        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        mainTabPane.getSelectionModel().select(produktionTab);


        // STYLING AF FANER
        farverPåTabs(produktionTab);
        farverPåTabs(fadeTab);
        farverPåTabs(whiskyTab);
        farverPåTabs(historikTab);


        // INDHOLD FRA SEPARATE KLASSER
        produktionTab.setContent(new ProduktionsPane());
        fadeTab.setContent(new FadeLagerPane());
        whiskyTab.setContent(new WhiskyProduktionerPane());
        historikTab.setContent(new HistorikPane());


        // BAGGRUNDSFARVE
        mainTabPane.setStyle("-fx-background-color: #F8F1EB;");
        pane.setCenter(mainTabPane);
    }


    // FARVER PÅ TABS
    private void farverPåTabs(Tab tab) {
        Label label = new Label(tab.getText());
        tab.setText(null);
        tab.setGraphic(label);

        // Styling ved opstart
        if (tab.isSelected()) {
            label.setStyle("-fx-font-weight: bold;" + "-fx-text-fill: #B96F49;");
        } else {
            label.setStyle("-fx-font-weight: normal;" + "-fx-text-fill: black;");
        }

        // Styling ved skift af fane
        tab.selectedProperty().addListener(
                (obs, oldValue, isSelected) -> {
                    if (isSelected) {
                        label.setStyle("-fx-font-weight: bold;" + "-fx-text-fill: #B96F49;");
                    } else {
                        label.setStyle("-fx-font-weight: normal;" + "-fx-text-fill: black;");
                    }
                }
        );
    }
}