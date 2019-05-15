package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;

class HourlyController extends ForecastController {

    HourlyController(ForecastView view, Location currentLocation) {
        super(view, currentLocation);
    }

    @Override
    protected void initialize() {
        super.initialize();

        for (WeatherData.WeatherDataPoint data : currentLocation.getWeatherData().hourly) {
            scrollChildren.add(new EntryView(new EntryController(data)));
        }
    }
}
