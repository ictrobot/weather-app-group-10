package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.scene.Node;
import javafx.scene.text.Text;

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
