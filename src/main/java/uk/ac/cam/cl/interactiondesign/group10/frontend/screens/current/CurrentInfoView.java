package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.PrecAnimation;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.ThermoAnimation;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WText;

class CurrentInfoView extends GridPane {

    private CurrentInfoController controller;

    CurrentInfoView(CurrentInfoController currentInfoController) {
        this.controller = currentInfoController;
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    private void makeGrid() {
        // three columns
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.NEVER);
        column1.setHalignment(HPos.CENTER);
        getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        column2.setHalignment(HPos.CENTER);
        getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHgrow(Priority.NEVER);
        column3.setHalignment(HPos.CENTER);
        getColumnConstraints().add(column3);

        // five rows
        for (int i = 0; i < 5; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setValignment(VPos.CENTER);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        Text location = new WText();
        add(location, 0, 0, 3, 1);
        controller.textLocation = location.textProperty();

        ImageView weatherIcon = new ImageView();
        weatherIcon.setFitHeight(160);
        weatherIcon.setPreserveRatio(true);
        add(weatherIcon, 1, 1);
        controller.imageProperty = weatherIcon.imageProperty();

        Text conditions = new WText();
        add(conditions, 1, 2);
        controller.textConditions = conditions.textProperty();

        // temperature
        ThermoAnimation thermometer = new ThermoAnimation();
        thermometer.setFitWidth(50);
        add(thermometer, 0, 1, 1, 3);
        setValignment(thermometer, VPos.BOTTOM);
        controller.thermoAnimationSetup = thermometer::setupAnimation;

        // precipitation
        PrecAnimation raindrop = new PrecAnimation();
        raindrop.setFitWidth(50);
        add(raindrop, 2, 1, 1, 3);
        setValignment(raindrop, VPos.BOTTOM);
        controller.precAnimationSetup = raindrop::setupAnimation;

        GridPane textGrid = makeTextGrid();
        add(textGrid, 0, 4, 3, 1);
    }

    private GridPane makeTextGrid() {
        // make inner grid for bottom text with just 2 columns
        GridPane textGrid = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.ALWAYS);
        column1.setHalignment(HPos.LEFT);
        textGrid.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        column2.setHalignment(HPos.RIGHT);
        textGrid.getColumnConstraints().add(column2);

        textGrid.add(new WText("Temperature:"), 0, 0);

        Text temperature = new WText();
        textGrid.add(temperature, 0, 1);
        controller.textTemperature = temperature.textProperty();

        textGrid.add(new WText("Precipitation:"), 1, 0);

        Text precipitation = new WText();
        textGrid.add(precipitation, 1, 1);
        controller.textPrecipitation = precipitation.textProperty();

        return textGrid;
    }
}
