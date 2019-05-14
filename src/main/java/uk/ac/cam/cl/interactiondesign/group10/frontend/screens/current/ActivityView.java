package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseView;

public class ActivityView extends BaseView {

    private ActivityController controller;
    private CurrentInfoController currentInfoController;

    public ActivityView(Location currentLocation) {
        controller = new ActivityController(this, currentLocation);
        currentInfoController = new CurrentInfoController(currentLocation);
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    private void makeGrid() {
        // one column
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setPercentWidth(100);
        getColumnConstraints().add(columnConstraints);

        // four rows, expand 2nd
        for (int i = 0; i < 4; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(i == 1 ? Priority.ALWAYS : Priority.NEVER);
            rowConstraints.setValignment(VPos.CENTER);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        Text title = new Text("Activity Suggestion in");
        add(title, 0, 0);

        CurrentInfoView civ = new CurrentInfoView(currentInfoController);
        add(civ, 0, 1);

        Text activity = new Text();
        add(activity, 0, 2);
        controller.textActivitySuggestion = activity.textProperty();

        Button back = new Button("Back");
        back.setOnAction(controller::gotoMainMenu);
        add(back, 0, 3);
    }
}
