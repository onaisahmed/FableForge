package fableforge.core;

import fableforge.core.model.Suggestion;
import org.languagetool.rules.RuleMatch;
import java.util.List;

public class SuggestionManager implements Suggestion {
    private final RuleMatch match;
    private final String originalText;

    public SuggestionManager(RuleMatch match, String originalText) {
        this.match = match;
        this.originalText = originalText;
    }

    @Override
    public String getMessage() {
        return match.getMessage();
    }

    @Override
    public String getIncorrectText() {
        return originalText.substring(match.getFromPos(), match.getToPos());
    }

    @Override
    public int getStartIndex() {
        return match.getFromPos();
    }

    @Override
    public int getEndIndex() {
        return match.getToPos();
    }

    @Override
    public List<String> getReplacements() {
        return match.getSuggestedReplacements();
    }
}
