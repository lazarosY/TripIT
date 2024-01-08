import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
public class MAPS{
    public static void main (String[] args) {
        
        String x = "37.9686";
        String y = "23.7294";
        PlacesAPIgoogle ghelp = new PlacesAPIgoogle();
        String place_id=ghelp.getplaceid(x, y);
        System.out.println("----------------------------:     " + place_id);

        MAPS help = new MAPS();
        JSONObject json = new JSONObject(help.GoogleReviews(place_id));
        JSONArray jsonarr = json.getJSONObject("result").getJSONArray("reviews");
        
        for (int i=0;i<jsonarr.length();i++){
            JSONObject review = jsonarr.getJSONObject(i);
            String sreview = review.toString();
            sreview = GetSubstring2(sreview);
            System.out.println(sreview);
        }
       
        System.out.print("Overall"+help.GoogleRating(place_id));
    }
    
    
    public static String GoogleRating(String placeid) {
    //HTTP REQUEST for the API
    String gapiKey = "AIzaSyBz32QZHuvccsYTCsAe9Wxd0RCWO2hXDas";
    String gurl=("https://maps.googleapis.com/maps/api/place/details/json?fields=rating&place_id="+placeid+"&key="+gapiKey);//instead of reviews I can add rating, for a general rating
    try{ 
        // HTTP REQUEST
        URL gmaps = new URL(gurl);
        HttpURLConnection mapscon = (HttpURLConnection) gmaps.openConnection();
        mapscon.setRequestMethod("GET");
        // HTTP REQUEST
        
        //HTTP RESPONSE
        BufferedReader mapsin = new BufferedReader(new InputStreamReader(mapscon.getInputStream()));
        String mapsinputline;
        StringBuffer mresponse = new StringBuffer();
        while((mapsinputline=mapsin.readLine()) != null) {
            mresponse.append(mapsinputline);
        }
        mapsin.close();
        String resp = mresponse.toString();
        resp = GetSubstring(resp);
        return resp;
        
    }catch(IOException E3){
        throw new RuntimeException(E3);
    }
}
    
    
    
    
    
    public String GoogleReviews(String placeid) {
    //HTTP REQUEST for the API
    String gapiKey = "AIzaSyBz32QZHuvccsYTCsAe9Wxd0RCWO2hXDas";
    String gurl=("https://maps.googleapis.com/maps/api/place/details/json?fields=reviews&place_id="+placeid+"&key="+gapiKey);//instead of reviews I can add rating, for a general rating
    try{ 
        // HTTP REQUEST
        URL gmaps = new URL(gurl);
        HttpURLConnection mapscon = (HttpURLConnection) gmaps.openConnection();
        mapscon.setRequestMethod("GET");
        // HTTP REQUEST
        
        //HTTP RESPONSE
        BufferedReader mapsin = new BufferedReader(new InputStreamReader(mapscon.getInputStream()));
        String mapsinputline;
        StringBuffer mresponse = new StringBuffer();
        while((mapsinputline=mapsin.readLine()) != null) {
            mresponse.append(mapsinputline);
        }
        mapsin.close();
        String resp = mresponse.toString();
        return resp;
    
    }catch(IOException E2){
        throw new RuntimeException(E2);
    }
}

public static String GetSubstring(String response) {
    int startMarker = response.indexOf("rating")-1; // Marks the start of the response
    int endMarker = response.indexOf("}", startMarker); // Marks the end of the response
    return response.substring(startMarker, endMarker); // Returns the response string
}

public static String GetSubstring2(String response) {
    int startMarker = response.indexOf("reviews"); // Marks the start of the response
    int endMarker = response.indexOf("}", startMarker); // Marks the end of the response
    return response.substring(startMarker, endMarker); // Returns the response string
}


}