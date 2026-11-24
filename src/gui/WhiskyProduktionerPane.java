package gui;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class WhiskyProduktionerPane extends TabPane {
    public WhiskyProduktionerPane(){
        initContent();
    }
    private void initContent(){
        //underfaner
        Tab opretTab = new Tab("Opret whiskyprodukt");
        Tab visTab = new Tab("Vis whiskyprodukt");

        getTabs().addAll(opretTab, visTab);
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        getSelectionModel().select(opretTab);

        //Indhold i underfanerne
        opretTab.setContent(new OpretWhiskyprodukt());
        visTab.setContent(new VisWhiskyprodukt());
        //Styling
        farverPåTabs(opretTab);
        farverPåTabs(visTab);

    }
    private void farverPåTabs(Tab tab) {

        Label label = new Label(tab.getText());
        tab.setText(null);
        tab.setGraphic(label);

        if (tab.isSelected()) {
            label.setStyle("-fx-font-weight: bold;" + "-fx-text-fill: #B96F49;"
            );
        } else {
            label.setStyle("-fx-font-weight: normal;" + "-fx-text-fill: black;");
        }

        tab.selectedProperty().addListener((obs, oldValue, isSelected) -> {
                    if (isSelected) {
                        label.setStyle("-fx-font-weight: bold;" + "-fx-text-fill: #B96F49;");
                    } else {
                        label.setStyle("-fx-font-weight: normal;" + "-fx-text-fill: black;");
                    }
                }
        );
    }
}
