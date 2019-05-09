package uk.ac.cam.cl.interactiondesign.group10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("screens/StartScreen.fxml"));
    primaryStage.setTitle("WeatherData App");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
