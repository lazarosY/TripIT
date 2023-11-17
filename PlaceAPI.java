import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import netscape.javascript.JSObject;
public class PlacesAPI {
    public static void main (String[] args){

    }
    public static string getplaceid(string x,string y) {
        strig lat = x;
        string lng = y;
        string gapikey = "AIzaSyDHZEhrtwLryi4OezyeSGNcwsOtOTGD2E0";
       string purl= ("http://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lng+"&key="+gapikey);
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
            connection = (HttpURLConnection)url.openConnection();
        } catch (ClassCastException e) {
            System.err.println("Specified protocol is not HTTP");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Connection error: " + e);
            System.exit(1);
        }

        try {
            connection.setRequestMethod("GET");

            // Get the response from the server
            int status = connection.getResponseCode();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
            int c;
            StringBuilder content = new StringBuilder();
            while ((c = in.read()) != -1) {
                content.append((char)c);
            }
            in.close();

        JSONObject extract = new JSObject(content) ;
            string place_id = extract.get("place_id");
            return place_id;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

