package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current.MainView;

/**
 * Every screen is made up of a View and Controller class.
 * The screen's Controller class will extend BaseController and is responsible for any logic such as the
 * action on a button press, and setting any dynamic images or text to the correct values
 */
public abstract class BaseController {

    private final BaseView view;
    protected final Location currentLocation;

    protected BaseController(BaseView view, Location currentLocation) {
        this.view = view;
        this.currentLocation = currentLocation;

        // change the screen's background colour to match the temperature
        setupBackgroundColour();
    }

    /**
     * @return JavaFX stage, needed for low level manipulation such as changing the screen
     */
    protected Stage getStage() {
        return ((Stage) view.getScene().getWindow());
    }

    /**
     * Helper method to change the scene
     */
    protected void changeScreen(BaseView newView) {
        getStage().setScene(new Scene(newView));
    }

    /**
     * Helper button event handler which changes the screen to the main menu
     * Common to all screens excluding the main menu itself
     */
    public void gotoMainMenu(ActionEvent event) {
        changeScreen(new MainView(currentLocation));
    }

    /**
     * Uses a colour gradient stored in a PNG file to dynamically set the background colour of
     * the screen based on the current temperature
     */
    private void setupBackgroundColour() {
        // Load the image and get a pixel reader to enable reading an individual pixel's colour
        Image backgroundGradient = ImageCache.loadImage("other/colourgradient.png");
        PixelReader pixelReader = backgroundGradient.getPixelReader();
        // use the current temperature to choose which pixel to read
        double currentTemp = currentLocation.getWeatherData().current.temperature;
        int pixelX;
        if (currentTemp <= -2) {
            pixelX = 0;
        } else if (currentTemp >= 28) {
            pixelX = (int) (backgroundGradient.getWidth() - 1);
        } else {
            pixelX = (int) (((currentTemp + 2) / 30) * backgroundGradient.getWidth());
        }
        // use helper in BaseView to set the colour
        Color colour = pixelReader.getColor(pixelX, 0);
        this.view.setBackgroundColor(colour);
    }
}
