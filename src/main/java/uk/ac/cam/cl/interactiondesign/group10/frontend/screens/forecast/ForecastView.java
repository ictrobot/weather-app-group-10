package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WButton;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WText;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseView;

abstract class ForecastView extends BaseView {

    protected ForecastController controller;

    void makeGrid() {
        // one column
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setPercentWidth(100);
        getColumnConstraints().add(columnConstraints);

        // four rows, 3rd expand to fill
        for (int i = 0; i < 4; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(i == 2 ? Priority.ALWAYS : Priority.NEVER);
            getRowConstraints().add(rowConstraints);
        }
    }

    void populateGrid() {
        Text title = new WText(getTitleString(), true);
        add(title, 0, 0);

        Text currentLocation = new WText();
        add(currentLocation, 0, 1);
        controller.locationText = currentLocation.textProperty();

        VBox scrollInner = new VBox();
        scrollInner.setSpacing(25);
        ScrollPane scrollPane = new ScrollPane(scrollInner);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        add(scrollPane, 0, 2);
        controller.scrollChildren = scrollInner.getChildren();

        Button back = new WButton("Back");
        back.setOnAction(controller::gotoMainMenu);
        add(back, 0, 3);
    }

    abstract String getTitleString();
}
