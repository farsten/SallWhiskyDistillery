package gui;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ProduktionsPane extends TabPane {

    public ProduktionsPane() {
        initContent();
    }

    private void initContent() {

        // UNDERFANER
        Tab markTab = new Tab("Registrér mark");
        Tab maltTab = new Tab("Registrér maltbatch");
        Tab destilleringTab = new Tab("Registrér destillering");

        getTabs().addAll(markTab, maltTab, destilleringTab);

        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        getSelectionModel().select(markTab);

        // INDHOLD I UNDERFANERNE
        markTab.setContent(new RegistrérMarkPane());
        maltTab.setContent(new RegistrérMaltPane());
        destilleringTab.setContent(new RegistrérDestilleringsPane());

        // STYLING
        farverPåTabs(markTab);
        farverPåTabs(maltTab);
        farverPåTabs(destilleringTab);
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

