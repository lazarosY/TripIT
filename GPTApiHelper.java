import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class GPTApiHelper {

    public static double getMinimumBudgetForDestination(String destination) {
        String apiKey = "Your_OpenAI_API_Key"; // Replace with your actual API key
        String prompt = "What is the minimum required budget for a trip to " + destination + "?";
        String model = "text-davinci-003"; // or any other suitable model

        try {
            URL url = new URL("https://api.openai.com/v1/engines/" + model + "/completions");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setDoOutput(true);

            String jsonInputString = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);           
            }

            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONObject jsonObject = new JSONObject(response.toString());
                String textResponse = jsonObject.getJSONArray("choices").getJSONObject(0).getString("text");
                return parseBudgetFromResponse(textResponse);
            }
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("JSON parsing error: " + e.getMessage());
        }
        return -1; // Return an error indicator, e.g., -1
    }

    private static double parseBudgetFromResponse(String response) {
        // Implement logic to parse the budget value from the response text
        // This depends on how GPT formats its response
        return 0; // Placeholder
    }
}
