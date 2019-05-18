package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WButton;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WText;
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
        for (int i = 0; i < 5; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(i == 1 ? Priority.ALWAYS : Priority.NEVER);
            rowConstraints.setValignment(VPos.CENTER);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        Text title = new WText("Activity Suggestion in", true);
        add(title, 0, 0);

        CurrentInfoView civ = new CurrentInfoView(currentInfoController);
        add(civ, 0, 1);


        Text activity = new WText();
        activity.setWrappingWidth(300);
        add(activity, 0, 2);
        controller.textActivitySuggestion = activity.textProperty();

        ImageView activityIcon = new ImageView();
        activityIcon.setFitHeight(100);
        activityIcon.setPreserveRatio(true);
        add(activityIcon, 0, 3);
        controller.imageActivityProperty = activityIcon.imageProperty();

        Button back = new WButton("Back");
        back.setOnAction(controller::gotoMainMenu);
        add(back, 0, 4);
    }
}
