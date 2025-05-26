package fableforge.core.model;
import java.util.List;

public interface Suggestion {
    String getMessage();
    String getIncorrectText();
    int getStartIndex();
    int getEndIndex();
    List<String> getReplacements();
}
