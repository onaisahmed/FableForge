package fableforge.services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AiService {

    private static final String API_KEY = "AIzaSyB1ZtcYzNnTCT799UPpltKLn3sMOCOFpBM";
    private static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;

    public static String callGemini(String prompt) {
        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String body = "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt + "\"}]}]}";
            conn.getOutputStream().write(body.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                response.append(line);

            return response.toString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
