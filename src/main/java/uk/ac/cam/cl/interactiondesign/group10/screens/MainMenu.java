package uk.ac.cam.cl.interactiondesign.group10.screens;

import com.google.maps.model.LatLng;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.GetWeather;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;

import java.io.IOException;
import java.net.URL;

public class MainMenu extends ScreenBase {

  public static final URL FXML_URL = MainMenu.class.getResource("MainMenu.fxml");

  public static void showMainMenu(ScreenBase current, LatLng location) {
    try {
      FXMLLoader loader = new FXMLLoader(FXML_URL);
      Parent root = loader.load();
      MainMenu mainMenu = loader.getController();
      mainMenu.initialize(location);

      current.getStage().setScene(new Scene(root));
    } catch (IOException e) {
      throw new RuntimeException("Failed to load mainmenu", e);
    }
  }

  WeatherData data;

  @FXML
  private Text currentTempText;

  private void initialize(LatLng location) {
    data = GetWeather.getWeather(location);

    currentTempText.setText(data.temperature + "°C");
  }

}
