package uk.ac.cam.cl.interactiondesign.group10.backend;

import com.google.maps.model.LatLng;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetWeather {

  public static WeatherData getWeather(LatLng location) {
    HttpResponse<JsonNode> response;
    String url = "https://api.darksky.net/forecast/" + APIKeys.DARKSKY_WEATHER + "/" + location.lat + "," + location.lng;
    System.out.println(url);
    try {
       response = Unirest.get(url)
               .header("lang", "en")
               .header("units", "uk2")
              .asJson();
    } catch (UnirestException e) {
      throw new RuntimeException("Failed to fetch weather", e);
    }
    return new WeatherData(response.getBody());
  }
}
