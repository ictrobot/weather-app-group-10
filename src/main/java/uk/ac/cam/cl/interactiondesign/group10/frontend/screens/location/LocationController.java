package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.location;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIException;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseController;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.MainMenu;

class LocationController extends BaseController {

    StringProperty searchStringProperty;

    LocationController(LocationView view, Location previousLocation) {
        super(view, previousLocation);
    }

    void doSearch(ActionEvent event) {
        String searchString = searchStringProperty.get();
        // prevent searching an empty string
        if (searchString.isEmpty()) return;

        Location location = null;
        Dialog loadingDialog = showLoadingDialog();
        try {
            location = Location.searchForLocation(searchString);
        } catch (APIException e) {
            e.printStackTrace();
        }
        loadingDialog.close();

        if (location == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unknown Location");
            alert.setHeaderText("Unknown Location");
            alert.setContentText("Please try entering another location");
            alert.showAndWait();
        } else {
            MainMenu.show(getStage(), location);
        }
    }

    void doLocate(ActionEvent event) {
        Location location = null;
        Dialog loadingDialog = showLoadingDialog();
        try {
            location = Location.detectLocation();
        } catch (APIException e) {
            e.printStackTrace();
        }
        loadingDialog.close();

        if (location == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed to find your location");
            alert.setHeaderText("Unknown Location");
            alert.setContentText("Please try again or enter your location manually");
            alert.showAndWait();
        } else {
            MainMenu.show(getStage(), location);
        }
    }

    private Dialog<Boolean> showLoadingDialog() {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Loading");
        dialog.setContentText("Loading data");
        dialog.setResult(true);
        dialog.show();
        return dialog;
    }

}
