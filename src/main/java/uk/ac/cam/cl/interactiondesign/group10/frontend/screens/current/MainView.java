package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseView;

public class MainView extends BaseView {

    private MainController controller;
    private CurrentInfoController currentInfoController;

    public MainView(Location currentLocation) {
        controller = new MainController(this, currentLocation);
        currentInfoController = new CurrentInfoController(currentLocation);
        makeGrid();
        populateGrid();
    }

    private void makeGrid() {
        // one column
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setPercentWidth(100);
        getColumnConstraints().add(columnConstraints);

        // six rows, expand 2nd
        for (int i = 0; i < 6; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(i == 1 ? Priority.ALWAYS : Priority.NEVER);
            rowConstraints.setValignment(VPos.CENTER);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        Text title = new Text("Current weather in");
        add(title, 0, 0);

        CurrentInfoView civ = new CurrentInfoView(currentInfoController);
        add(civ, 0, 1);

        Button activity = new Button("Activity Suggestion");
        activity.setOnAction(controller::showActivity);
        add(activity, 0, 2);

        Button hourly = new Button("Hourly Forecast");
        hourly.setOnAction(controller::showHourly);
        add(hourly, 0, 3);

        Button daily = new Button("Daily Forecast");
        daily.setOnAction(controller::showDaily);
        add(daily, 0, 4);

        Button changeLocation = new Button("Change Location");
        changeLocation.setOnAction(controller::changeLocation);
        add(changeLocation, 0, 5);
    }
}