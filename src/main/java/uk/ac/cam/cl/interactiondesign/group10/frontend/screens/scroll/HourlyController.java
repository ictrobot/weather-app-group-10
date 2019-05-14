package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.scroll;

import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;

class HourlyController extends ScrollController {

    HourlyController(ScrollView view, Location currentLocation) {
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
