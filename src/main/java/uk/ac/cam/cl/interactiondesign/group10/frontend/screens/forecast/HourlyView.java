package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import uk.ac.cam.cl.interactiondesign.group10.backend.Location;

/**
 * Hourly forecast screen
 */
public class HourlyView extends ForecastView {

    public HourlyView(Location currentLocation) {
        controller = new HourlyController(this, currentLocation);
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    @Override
    protected String getTitleString() {
        return "Hourly weather forecast for";
    }
}
