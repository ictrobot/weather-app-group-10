package uk.ac.cam.cl.interactiondesign.group10.screens;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ScreenBase {

  @FXML
  private Node root;

  Stage getStage() {
    return (Stage) root.getScene().getWindow();
  }

}
