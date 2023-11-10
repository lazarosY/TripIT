import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class MAPS{
    public static void main (String[] args){
        string dest= "athens greece";
        string place_id="ChIJ8UNwBh-9oRQR3Y1mdkU1Nic";
        Googlemaps(dest,place_id);
    }
    public static String GoogleMaps(string destination, string placeid){
    //HTTP REQUEST for the API
    String gapiKey = "AIzaSyDHZEhrtwLryi4OezyeSGNcwsOtOTGD2E0";
    String gurl=("https://maps.googleapis.com/maps/api/place/details/json?fields=rating&reviews&place_id="+placeid+"&key="+gapiKey);
    try{ 
        // HTTP REQUEST
        URL gmaps = new URL(gurl);
        HttpURLConnection mapscon = (HttpURLConnection) url.openConnection();
        mapscon.setRequestMethod("GET");
        // HTTP REQUEST
        OutputStreamWriter mapswriter = new OutputStreamWriter(mapscon.getOutputStream());
        //HTTP RESPONSE
        BufferedReader mapsin = new BufferedReader(new InputStreamReader(mapscon.getInputStream()));
        String mapsinputline;
        StringBuffer mresponse = new StringBuffer();
        while((mapsinputline=mapsin.readLine()) != null) {
            mresponse.append(mapsinputline);
        }
        mapsin.close();
        System.out.println(mresponse);
    }catch(IOException E2){
        throw new RuntimeException(E2);
    }
}
}