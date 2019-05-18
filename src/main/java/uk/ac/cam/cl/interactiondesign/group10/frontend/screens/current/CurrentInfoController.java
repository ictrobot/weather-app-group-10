package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

class CurrentInfoController {

    private final Location location;

    StringProperty textLocation;
    StringProperty textConditions;
    StringProperty textTemperature;
    StringProperty textPrecipitation;
    ObjectProperty<Image> imageProperty;
    double tempretureValue;
    double precipitationValue;

    CurrentInfoController(Location location) {
        this.location = location;
    }

    void initialize() {
        WeatherData.WeatherDataPoint current = location.getWeatherData().current;
        textLocation.setValue(location.getName());
        textConditions.setValue(current.darkSkySummary);
        textTemperature.setValue(current.getTemperatureString());
        textPrecipitation.setValue(current.getPrecipitationString());
        imageProperty.setValue(ImageCache.weatherImage(current.darkSkyIcon));

        tempretureValue = current.temperature;
        precipitationValue = current.precipitationProbability;
    }

}
