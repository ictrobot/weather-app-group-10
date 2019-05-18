package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.scene.control.Button;

/**
 * WButton = Weather App Button
 * Customizes default button class for our weather app, avoids having to set the same properties repeatedly
 * Ensures the button uses the correct font and that all buttons are the same size
 */
public class WButton extends Button {

  public WButton(String text) {
    super(text);
    setFont(WFont.MAIN);
    setMinWidth(200);
  }

}
