package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javax.naming.InitialContext;

public class HistorikPane extends TabPane {
    public HistorikPane(){
        initContent();
    }

    private void initContent(){
       //Underfane
        Tab historikTab = new Tab("Historik");

        getTabs().add(historikTab);
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        //Indhold i underfanen
        historikTab.setContent(new VisHistorikPane());
        //styling
        farverPåTabs(historikTab);

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
