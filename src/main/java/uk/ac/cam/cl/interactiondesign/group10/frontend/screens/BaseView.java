package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

public abstract class BaseView extends GridPane {

    public BaseView() {
        setPadding(new Insets(20f));

        setPrefWidth(9 * 40);
        setPrefHeight(16 * 40);

        setMinWidth(9 * 30);
        setMinHeight(16 * 30);

        setHgap(10);
        setVgap(10);
    }

    void setupBackgroundColour(WeatherData.WeatherDataPoint current) {
        Image backgroundGradient = ImageCache.loadImage("other/colourgradient.png");
        PixelReader pixelReader = backgroundGradient.getPixelReader();
        int x;
        if (current.temperature < -5) {
            x = 0;
        } else if (current.temperature > 30) {
            x = (int) (backgroundGradient.getWidth() - 1);
        } else {
            x = (int) (((current.temperature + 5) / 35) * backgroundGradient.getWidth());
        }
        Color colour = pixelReader.getColor(x, 0);
        BackgroundFill backgroundFill = new BackgroundFill(colour, null, null);
        setBackground(new Background(backgroundFill));
    }

}
