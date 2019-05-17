package uk.ac.cam.cl.interactiondesign.group10.backend;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

/**
 * A location is made up of the latitude and longitude, which can be used to look up weather data
 * and the place name
 */
public final class Location {

    // Lazily initialize Google's geocoding API
    private static GeoApiContext GEOCODING_CONTEXT = null;

    private final double latitude;
    private final double longitude;
    private final String name;
    private WeatherData weatherData;

    private Location(String name, double latitude, double longitude) throws APIException {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;

        System.out.println("Location: " + toString());
        refreshWeatherData();
    }

    public void refreshWeatherData() throws APIException {
        this.weatherData = WeatherData.getWeatherData(latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    @Override
    public String toString() {
        return "(\"" + name + "\", " + latitude + ", " + longitude + ")";
    }

    // STATIC

    public static Location detectLocation() throws APIException {
        // Hi-Fi prototype is hard coded to presume Cambridge
        // as it would be to difficult to use location data
        System.out.println("Location: User attempting to use geolocation (hard coded to Cambridge)");
        return new Location("Cambridge, UK", 52.20533700, 0.12181700);
    }

    public static Location searchForLocation(String searchString) throws APIException {
        // Lazily initialize Google's geocoding API
        if (GEOCODING_CONTEXT == null)
            GEOCODING_CONTEXT = new GeoApiContext.Builder().apiKey(APIKeys.GOOGLE_GEOCODING).build();
        System.out.println("Location: User attempting to search for \"" + searchString + "\"");

        // Try fetching results from Google's API
        GeocodingResult[] results;
        try {
            results = GeocodingApi.geocode(GEOCODING_CONTEXT, searchString).await();
        } catch (InterruptedException | IOException | com.google.maps.errors.ApiException e) {
            throw new APIException("Geocoding request failed", e);
        }
        if (results == null) return null;

        // Return the first response (if any) that is a "LOCALITY" which roughly limits it to towns etc
        // prevents being able to type in something too general (e.g. United Kingdom)
        // or too exact (e.g. Computer Lab University of Cambridge)
        for (GeocodingResult result : results) {
            for (AddressType type : result.types) {
                // limit to towns
                if (type == AddressType.LOCALITY) {
                    return new Location(result.formattedAddress, result.geometry.location.lat, result.geometry.location.lng);
                }
            }
        }

        return null;
    }

}
