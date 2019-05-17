package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.scene.text.Text;

/**
 * WText = Weather App Text
 * Customizes default text class for our weather app, avoids having to set the same properties repeatedly
 * Ensures text uses the correct font
 */
public class WText extends Text {

  public WText() {
    setFont(WFont.MAIN);
  }

  public WText(String text) {
    super(text);
    setFont(WFont.MAIN);
  }

  public WText(String text, boolean isTitle) {
    super(text);
    if (isTitle) {
      setFont(WFont.TITLE);
    } else {
      setFont(WFont.MAIN);
    }
  }

}
