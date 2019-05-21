package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WButton;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WText;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseView;

/**
 * Base for hourly and daily forecasts
 */
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
        // top title text
        Text title = new WText(getTitleString(), true);
        add(title, 0, 0);

        // dynamic location text
        Text currentLocation = new WText();
        add(currentLocation, 0, 1);
        controller.locationText = currentLocation.textProperty();

        // container for each of the data point EntryView instances
        VBox scrollInner = new VBox();
        scrollInner.setSpacing(25);
        // inside a central scroll pane
        ScrollPane scrollPane = new ScrollPane(scrollInner);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        add(scrollPane, 0, 2);
        controller.scrollChildren = scrollInner.getChildren();

        // bottom back button
        Button back = new WButton("Back");
        back.setOnAction(controller::gotoMainMenu);
        add(back, 0, 3);
    }

    /** @return title text for this screen */
    abstract String getTitleString();
}
