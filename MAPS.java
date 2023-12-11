import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class MAPS{
    public static void main (String[] args){
        String dest= "athens greece";
        String place_id="ChIJgSMyRUu9oRQRS9UFR1xmJrE";
        MAPS help = new MAPS();
       System.out.print(help.GoogleMaps(place_id));
    }
    public static String GoogleMaps(String placeid) {
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
        return mresponse.toString();
    }catch(IOException E2){
        throw new RuntimeException(E2);
    }
}
}