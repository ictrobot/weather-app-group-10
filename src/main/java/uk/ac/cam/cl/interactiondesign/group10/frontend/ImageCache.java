package uk.ac.cam.cl.interactiondesign.group10.frontend;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {

  private static Map<String, Image> CACHE = new HashMap<>();

  public static Image loadImage(String imagePath) {
    if (CACHE.containsKey(imagePath)) {
      return CACHE.get(imagePath);
    }
    try (InputStream imageStream = ImageCache.class.getResourceAsStream("/images/" + imagePath)) {
      Image image = new Image(imageStream);
      CACHE.put(imagePath, image);
      return image;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NullPointerException ignored) {

    }
    return null;
  }

  public static Image weatherImage(String weatherIcon) {
    Image icon = loadImage("weather/" + weatherIcon + ".png");
    if (icon == null) {
      icon = loadImage("weather/unknown.png");
    }
    return icon;
  }
}
