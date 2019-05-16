package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.scene.text.Font;

public class WFont {

  static final Font MAIN;
  static final Font TITLE;

  static {
    String url = WFont.class.getResource("/other/KGNeatlyPrinted.ttf").toExternalForm();

    MAIN = Font.loadFont(url, 24);

    TITLE = Font.loadFont(url, 32);
  }

}
