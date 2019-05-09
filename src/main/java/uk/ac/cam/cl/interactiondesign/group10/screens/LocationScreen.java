package uk.ac.cam.cl.interactiondesign.group10.screens;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIException;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;

import java.net.URL;

public class LocationScreen extends ScreenBase {

    public static final URL FXML_URL = MainMenu.class.getResource("LocationScreen.fxml");

    @FXML
    private TextField locationInput;

    public void doSearch() {
        String searchString = locationInput.getCharacters().toString();
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
            MainMenu.showMainMenu(this, location);
        }
    }

    public void doLocate() {
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
            MainMenu.showMainMenu(this, location);
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
