package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WText;

/**
 * Individual entry corresponding to a weather data point (each hour or day) inside the scroll pane on the forecast screen
 */
class EntryView extends GridPane {

    private EntryController controller;

    EntryView(EntryController controller) {
        this.controller = controller;
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    private void makeGrid() {
        // three columns
        ColumnConstraints column1 = new ColumnConstraints();
        getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.RIGHT);
        column2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHalignment(HPos.LEFT);
        column3.setHgrow(Priority.ALWAYS);
        getColumnConstraints().add(column3);

        // three rows
        // 2nd row expands to add padding between condition text and values
        for (int i = 0; i < 4; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(i == 2 ? Priority.ALWAYS : Priority.NEVER);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        // top left day / hour text
        Text time = new WText();
        add(time, 0, 0);
        setValignment(time, VPos.TOP);
        controller.textTime = time.textProperty();

        // top right weather conditions text
        Text conditions = new WText();
        conditions.setWrappingWidth(200);
        conditions.setTextAlignment(TextAlignment.CENTER);
        add(conditions, 1, 0, 2, 1);
        setHalignment(conditions, HPos.CENTER);
        controller.textConditions = conditions.textProperty();

        // central value labels
        add(new WText("Temperature: "), 1, 2);
        add(new WText("Precipitation: "), 1, 3);

        // actual temperature value
        Text temperature = new WText();
        add(temperature, 2, 2);
        controller.textTemperature = temperature.textProperty();

        // actual precipitation value
        Text precipitation = new WText();
        add(precipitation, 2, 3);
        controller.textPrecipitation = precipitation.textProperty();

        // weather icon for the data point
        ImageView imageView = new ImageView();
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);
        add(imageView, 0, 1, 1, 3);
        controller.imageProperty = imageView.imageProperty();
    }
}
