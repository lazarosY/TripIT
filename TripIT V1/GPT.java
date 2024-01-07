import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GPT {
public static Scanner sc = new Scanner(System.in); 

    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; 
        String model = "gpt-3.5-turbo";

        try {
            // REQUEST FORM
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();//connects to url
            con.setRequestMethod("POST");//POST:creates,GET:READS,DELETE:deletes
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");//json: a  file that stores data stractures in java script (in text form) in a way that is readable for the everyday man that's used to transmit data between applications 
            //  HTTP REQUEST 
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());//writes to url
            writer.write(body);//writes the specified object to the url
            writer.flush();//flushes it
            writer.close();//closes it so no exceptions are created 

            // HTTP RESPONSE
            BufferedReader gptin = new BufferedReader(new InputStreamReader(con.getInputStream()));//returns an input from the connection to the url
            String gptinputLine;
            StringBuffer gptresponse = new StringBuffer();
            while ((gptinputLine = gptin.readLine()) != null) {
                gptresponse.append(gptinputLine);
            }
            gptin.close();
            String answer = gptresponse.toString();
            answer = answer.replaceAll("\\s+", " ").replaceAll("\\\\n", " ").replaceAll("\\\\r", " ").replaceAll("\\\\t", " ").trim();
            answer = extractContentFromResponse(answer);
            return answer;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Extracts a substring from the original response
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11; // Marks the start of the response
        int endMarker = response.indexOf("\"", startMarker); // Marks the end of the response
        return response.substring(startMarker, endMarker); // Returns the response string
    }

}