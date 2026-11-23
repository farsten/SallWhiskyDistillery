package gui;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class FadeLagerPane extends TabPane {
    public FadeLagerPane() {
        initContent();
    }

    private void initContent() {
        //Underfaner
        Tab fadTab = new Tab("Registrér fad");
        Tab påfyldningTab = new Tab("Registrér påfyldning");
        Tab fadplaceringTab = new Tab("Registrér fadplacering");

        getTabs().addAll(fadTab, påfyldningTab, fadplaceringTab);
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        getSelectionModel().select(fadTab);

        //Indhold i underfanerne
        fadTab.setContent(new RegistrérFadPane());
        påfyldningTab.setContent(new RegistrérPåfyldningPane());
        fadplaceringTab.setContent(new RegistrérFadplaceringPane());
        //Styling
        farverPåTabs(fadTab);
        farverPåTabs(påfyldningTab);
        farverPåTabs(fadplaceringTab);
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

