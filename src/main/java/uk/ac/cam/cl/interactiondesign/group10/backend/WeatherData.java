package uk.ac.cam.cl.interactiondesign.group10.backend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class WeatherData {

    public double temperature;

    private WeatherData(JsonNode body) {
        JSONObject root = body.getObject();
        JSONObject currently = root.getJSONObject("currently");
        temperature = currently.getDouble("temperature");
    }

    static WeatherData getWeatherData(double latitude, double longitude) throws APIException {
        HttpResponse<JsonNode> response;
        String url = "https://api.darksky.net/forecast/" + APIKeys.DARKSKY_WEATHER
                + "/" + latitude + "," + longitude
                + "?lang=en&units=uk2";
        System.out.println("WeatherData: requesting data for " + latitude + ", " + longitude);

        try {
            response = Unirest.get(url).asJson();
        } catch (UnirestException e) {
            throw new APIException("Weather data request failed ", e);
        }
        return new WeatherData(response.getBody());
    }
}
