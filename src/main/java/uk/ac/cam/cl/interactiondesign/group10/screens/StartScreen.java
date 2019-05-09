package uk.ac.cam.cl.interactiondesign.group10.screens;

import com.google.maps.model.LatLng;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import uk.ac.cam.cl.interactiondesign.group10.backend.GetLocation;

import java.net.URL;

public class StartScreen extends ScreenBase {

  public static final URL FXML_URL = MainMenu.class.getResource("StartScreen.fxml");

  @FXML
  private TextField locationInput;

  public void searchPressed() {
    String searchString = locationInput.getCharacters().toString();
    System.out.println("Searching for \"" + searchString + "\"");
    LatLng location = GetLocation.search(searchString);
    if (location == null) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Unknown Location");
      alert.setHeaderText("Unknown Location");
      alert.setContentText("Please try entering another location");
      alert.showAndWait();
    } else {
      showMainScreen(location);
    }
  }

  public void locatePressed() {
    System.out.println("Trying to automatically locate");
    LatLng location = GetLocation.detect();
    if (location == null) {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Failed to find your location");
      alert.setHeaderText("Unknown Location");
      alert.setContentText("Please enter your location manually");
      alert.showAndWait();
    } else {
      showMainScreen(location);
    }
  }

  private void showMainScreen(LatLng location) {
    System.out.println("Location: " + location);
    MainMenu.showMainMenu(this, location);
  }
}
