package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.event.ActionEvent;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseController;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast.DailyView;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast.HourlyView;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.location.LocationView;

class MainController extends BaseController {

    MainController(MainView view, Location previousLocation) {
        super(view, previousLocation);
    }


    void showActivity(ActionEvent actionEvent) {
        changeScreen(new ActivityView(currentLocation));
    }

    void showHourly(ActionEvent actionEvent) {
        changeScreen(new HourlyView(currentLocation));
    }

    void showDaily(ActionEvent actionEvent) {
        changeScreen(new DailyView(currentLocation));
    }

    void changeLocation(ActionEvent actionEvent) {
        changeScreen(new LocationView(currentLocation));
    }
}
