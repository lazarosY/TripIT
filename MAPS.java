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
        String place_id="ChIJ8UNwBh-9oRQR3Y1mdkU1Nic";
        MAPS help = new MAPS();
       System.out.print(help.GoogleMaps(place_id));
    }
    public static String GoogleMaps(String placeid) {
    //HTTP REQUEST for the API
    String gapiKey = "AIzaSyDUBn3ug6L3u4mIsSR6Tgkl9_6_x6BmZMI";
    String gurl=("https://maps.googleapis.com/maps/api/place/details/json?fields=rating&reviews&place_id="+placeid+"&key="+gapiKey);
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