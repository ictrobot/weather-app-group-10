package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class WButton extends Button {

  public WButton() {
    setFont(WFont.MAIN);
  }

  public WButton(String text) {
    super(text);
    setFont(WFont.MAIN);
  }

  public WButton(String text, Node graphic) {
    super(text, graphic);
    setFont(WFont.MAIN);
  }


}
