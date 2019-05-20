package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import javafx.scene.image.Image;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

class DailyController extends ForecastController {

    DailyController(ForecastView view, Location currentLocation) {
        super(view, currentLocation);
    }

    @Override
    protected void initialize() {
        super.initialize();

        // add EntryView instances for each of the daily data points
        for (WeatherData.WeatherDataPoint data : currentLocation.getWeatherData().daily) {
            scrollChildren.add(new EntryView(new EntryController(data) {
                @Override
                protected Image getWeatherImage() {
                    // always show daytime icons on the daily forecast screen
                    String iconName = data.darkSkyIcon;
                    iconName = iconName.replace("-night", "-day");
                    return ImageCache.weatherImage(iconName);
                }
            }));
        }
    }
}
