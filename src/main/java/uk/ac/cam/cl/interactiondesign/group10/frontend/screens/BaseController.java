package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current.MainView;

public abstract class BaseController {

    private final BaseView view;
    protected final Location currentLocation;

    protected BaseController(BaseView view, Location currentLocation) {
        this.view = view;
        this.currentLocation = currentLocation;
    }

    protected Stage getStage() {
        return ((Stage) view.getScene().getWindow());
    }

    protected void changeScreen(BaseView newView) {
        getStage().setScene(new Scene(newView));
    }

    public void gotoMainMenu(ActionEvent event) {
        changeScreen(new MainView(currentLocation));
    }
}
