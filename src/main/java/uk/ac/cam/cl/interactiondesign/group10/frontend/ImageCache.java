package uk.ac.cam.cl.interactiondesign.group10.frontend;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple cache of all images used which makes the application much more response (after the needed images
 * have been cached the first time) by only loading each image once. Images are never removed from the cache
 * however this project does not have too many that this is a concern
 */
public class ImageCache {

  private static Map<String, Image> CACHE = new HashMap<>();

  public static Image loadImage(String imagePath) {
    // Check if the image is already in the cache, and return it if it is
    if (CACHE.containsKey(imagePath)) {
      return CACHE.get(imagePath);
    }
    // otherwise load the image in from disk
    try (InputStream imageStream = ImageCache.class.getResourceAsStream("/images/" + imagePath)) {
      Image image = new Image(imageStream);
      // and cache it before returning it
      CACHE.put(imagePath, image);
      return image;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NullPointerException ignored) {
      // happens when the image doesn't exist as the input stream is null
      // simply return null to indicate image doesn't exist
    }
    return null;
  }

  public static Image weatherImage(String weatherIcon) {
    // helper method to load a weather icon from the correct path
    Image icon = loadImage("weather/" + weatherIcon + ".png");
    // fall back to the unknown icon if we don't have the right icon
    if (icon == null) {
      return loadImage("weather/unknown.png");
    } else {
      return icon;
    }
  }
}
