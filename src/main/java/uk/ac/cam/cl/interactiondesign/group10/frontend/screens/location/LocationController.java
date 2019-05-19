package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.location;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIException;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseController;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current.MainView;

class LocationController extends BaseController {

    StringProperty searchStringProperty;

    LocationController(LocationView view, Location previousLocation) {
        super(view, previousLocation);
    }

    /** Called after clicking on the search button or pressing enter in the text field
     *  Lookup the user provided place name, goto the main menu if valid, otherwise display an alert
     */
    void doSearch(ActionEvent event) {
        String searchString = searchStringProperty.get();
        // prevent searching an empty string
        if (searchString.isEmpty()) return;

        // try looking up the location name whilst displaying a loading dialog box
        Location location = null;
        Dialog loadingDialog = showLoadingDialog();
        try {
            location = Location.searchForLocation(searchString);
        } catch (APIException e) {
            e.printStackTrace();
        }
        loadingDialog.close();

        if (location == null) {
            // location unknown - show alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unknown Location");
            alert.setHeaderText("Unknown Location");
            alert.setContentText("Please try entering another location");
            alert.showAndWait();
        } else {
            // location found - goto main menu
            changeScreen(new MainView(location));
        }
    }

    /** Called after clicking on the geolocation button
     *  Try to locate the user (which is hard coded to Cambridge in this prototype), displaying a loading dialog box
     *  whilst doing so
     */
    void doLocate(ActionEvent event) {
        // try to use geolocation and display a location dialog
        Location location = null;
        Dialog loadingDialog = showLoadingDialog();
        try {
            location = Location.detectLocation();
        } catch (APIException e) {
            e.printStackTrace();
        }
        loadingDialog.close();

        if (location == null) {
            // unsuccessful - show alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed to find your location");
            alert.setHeaderText("Unknown Location");
            alert.setContentText("Please try again or enter your location manually");
            alert.showAndWait();
        } else {
            // successful - goto main menu
            changeScreen(new MainView(location));
        }
    }

    /** Helper method to make a loading dialog, used by the above 2 methods */
    private Dialog<Boolean> showLoadingDialog() {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Loading");
        dialog.setContentText("Loading data");
        dialog.setResult(true);
        dialog.show();
        return dialog;
    }

}
