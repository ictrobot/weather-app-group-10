package uk.ac.cam.cl.interactiondesign.group10.backend;

import java.io.InputStream;
import java.util.Properties;

public final class APIKeys {

    static String GOOGLE_GEOCODING;
    static String DARKSKY_WEATHER;

    public static void loadAPIKeys() throws APIException {
        String google, darkSky;
        // load the needed API keys from the secrets file where they are stored
        // (which is not checked into Git)
        try (InputStream is = APIKeys.class.getResourceAsStream("/secrets.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            google = properties.getProperty("GOOGLE_GEOCODING_API_KEY");
            darkSky = properties.getProperty("DARK_SKY_API_KEY");
        } catch (Exception e) {
            throw new APIException("Failed to read API Keys", e);
        }
        // check the API keys are not obviously invalid
        if (checkIfKeyInvalid(google) || checkIfKeyInvalid(darkSky)) {
            throw new APIException("Invalid/Missing API Keys");
        }
        GOOGLE_GEOCODING = google;
        DARKSKY_WEATHER = darkSky;
    }

    private static boolean checkIfKeyInvalid(String key) {
        // check supplied API key is not empty or the placeholder file from the template file
        return key == null || key.isEmpty() || key.equals("[PUT API KEY HERE]");
    }

}
