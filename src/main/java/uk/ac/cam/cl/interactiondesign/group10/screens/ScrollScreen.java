package uk.ac.cam.cl.interactiondesign.group10.screens;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

public class ScrollScreen extends ScreenBase {

    private static final URL FXML_URL = ScrollScreen.class.getResource("ScrollScreen.fxml");

    static void show(Stage stage, Location location, ScrollScreenType type) {
        try {
            FXMLLoader loader = new FXMLLoader(FXML_URL);
            Parent root = loader.load();
            ScrollScreen scrollScreen = loader.getController();
            scrollScreen.initialize(location, type);

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load ScrollScreen", e);
        }
    }

    private Location location;

    @FXML
    private Text textTitle;

    @FXML
    private Text textCurrentLocation;

    @FXML
    private VBox scrollContents;

    private void initialize(Location location, ScrollScreenType type) {
        this.location = location;

        textTitle.setText(type.title);
        textCurrentLocation.setText(location.getName());

        ObservableList<Node> children = scrollContents.getChildren();
        WeatherData weatherData = location.getWeatherData();
        List<WeatherData.WeatherDataPoint> dataPoints = type.getter.apply(weatherData);
        for (WeatherData.WeatherDataPoint data : dataPoints) {
            children.add(ScrollScreenEntry.load(data));
        }
    }

    public void goBack() {
        MainMenu.show(getStage(), location);
    }

    public enum ScrollScreenType {
        HOURLY("Hourly weather forecast for", wd -> wd.hourly),
        DAILY("Daily weather forecast for", wd -> wd.daily);

        private final String title;
        private final Function<WeatherData, List<WeatherData.WeatherDataPoint>> getter;

        ScrollScreenType(String title, Function<WeatherData, List<WeatherData.WeatherDataPoint>> getter) {
            this.title = title;
            this.getter = getter;
        }
    }

}
