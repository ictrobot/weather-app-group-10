package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.scene.text.Font;

public class WFont {

  static final Font MAIN;
  static final Font TITLE;

  static {
    MAIN = Font.loadFont(WFont.class.getResourceAsStream("/other/KGNeatlyPrinted.ttf"), 24);
    TITLE = Font.loadFont(WFont.class.getResourceAsStream("/other/KGNeatlyPrinted.ttf"), 32);
  }

}
