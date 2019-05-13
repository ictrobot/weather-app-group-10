package uk.ac.cam.cl.interactiondesign.group10.backend;

import java.io.InputStream;
import java.util.Properties;

public class APIKeys {

    private static final String PLACEHOLDER = "[PUT API KEY HERE]";

    static String GOOGLE_GEOCODING;
    static String DARKSKY_WEATHER;

    public static void loadAPIKeys() throws APIException {
        String google = null, darkSky = null;

        try (InputStream is = APIKeys.class.getResourceAsStream("/secrets.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            google = properties.getProperty("GOOGLE_GEOCODING_API_KEY");
            darkSky = properties.getProperty("DARK_SKY_API_KEY");
        } catch (Exception e) {
            throw new APIException("Failed to read API Keys", e);
        }
        if (checkInvalid(google) || checkInvalid(darkSky)) {
            throw new APIException("Invalid/Missing API Keys");
        }
        GOOGLE_GEOCODING = google;
        DARKSKY_WEATHER = darkSky;
    }

    private static boolean checkInvalid(String key) {
        return key == null || PLACEHOLDER.equals(key) || key.isEmpty();
    }

}
