import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlacesAPIgoogle {
    public static void main(String[] args) {
        String x = "40.7128";
        String y = "-74.0060";
        String placeId = getplaceid(x, y);
        System.out.println("Place ID: " + placeId);
    }

    public static String getplaceid(String x, String y) {
        String lat = x;
        String lng = y;
        String gapikey = "AIzaSyBz32QZHuvccsYTCsAe9Wxd0RCWO2hXDas";
        String purl = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=" + gapikey;

        URL url = null;
        try {
            // Make a request to the specified URL
            url = new URL(purl);
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e);
            System.exit(1);
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response from the server
            int status = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            int c;
            StringBuilder content = new StringBuilder();
            while ((c = in.read()) != -1) {
                content.append((char) c);
            }
            in.close();

            // Parse the JSON response
            JSONObject googlejson = new JSONObject(content.toString());

            // Check if the "results" array is present
            if (googlejson.has("results")) {
                JSONArray resultsArray = googlejson.getJSONArray("results");

                // Check if there are any results in the array
                if (resultsArray.length() > 0) {
                    // Get the first result
                    JSONObject firstResult = resultsArray.getJSONObject(0);

                    // Check if the "place_id" is present in the first result
                    if (firstResult.has("place_id")) {
                        String placeId = firstResult.getString("place_id");
                        return placeId;
                    } else {
                        System.out.println("Error: 'place_id' not found in the first result");
                    }
                } else {
                    System.out.println("Error: No results found in the array");
                }
            } else {
                System.out.println("Error: 'results' array not found in the JSON response");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null; // Return null if an error occurs
    }
}