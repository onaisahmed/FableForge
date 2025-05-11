package fableforge.core;

import fableforge.services.AiService;

import org.json.JSONArray;
import org.json.JSONObject;

public class StoreGenerator {
    public static String generateStory(String topic) {
        String prompt = PromptRefiner.createPrompt(topic);
        String response = AiService.callGemini(prompt);
        return extractStoryFromJson(response);
    }

    private static String extractStoryFromJson(String json) {
        JSONObject obj = new JSONObject(json);
        JSONArray candidates = obj.getJSONArray("candidates");
        JSONObject firstCandidate = candidates.getJSONObject(0);
        JSONObject content = firstCandidate.getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        JSONObject part = parts.getJSONObject(0);
        return part.getString("text");
    }
}

