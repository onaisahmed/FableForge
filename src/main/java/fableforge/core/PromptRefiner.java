package fableforge.core;

public class PromptRefiner {
    public static String createPrompt(String topic) {
        return "You are a master storyteller with a deep understanding of narrative structure, character development," +
                " and imaginative writing. Write a short, engaging fictional story in exactly 100–150 words. " +
                "Use vivid language, strong imagery, and emotional appeal. The story should feel complete with a clear beginning, middle, and end. " +
                "Focus on creativity, coherence, and originality. " +
                "Base the story on the following user prompt or keywords: " + topic;
    }
}
