package uk.ac.cam.cl.interactiondesign.group10.backend;

import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

public class WeatherData {

  public double temperature;

  public WeatherData(JsonNode body) {
    JSONObject root = body.getObject();
    JSONObject currently = root.getJSONObject("currently");
    temperature = currently.getDouble("temperature");
  }
}
