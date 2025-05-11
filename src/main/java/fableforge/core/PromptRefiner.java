package fableforge.core;

public class PromptRefiner {
    public static String createPrompt(String topic) {
        return "Write a short story about this topic maximum 100 words: " + topic;
    }
}
