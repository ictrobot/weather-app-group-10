package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.scroll;

import uk.ac.cam.cl.interactiondesign.group10.backend.Location;

public class DailyView extends ScrollView {

    public DailyView(Location currentLocation) {
        controller = new DailyController(this, currentLocation);
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    @Override
    protected String getTitleString() {
        return "Daily weather forecast for";
    }
}
