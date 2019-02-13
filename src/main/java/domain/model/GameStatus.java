package domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class GameStatus {
    private String phrase;
    private Character[] phraseState;
    private Integer maxAttempts;
    private Integer successAttemps;
    private Integer failedAttempts;
    private List<Character> history;

    public GameStatus(String phrase, Integer maxAttempts) {
        this.phrase = phrase;
        this.phraseState = phraseState;
        this.maxAttempts = maxAttempts;
        this.successAttemps = 0;
        this.failedAttempts = 0;
        this.history = new ArrayList<Character>();
    }

    public boolean isGameFinished(){
        if (failedAttempts >= maxAttempts){
            return true;
        }
        for (Character character : phraseState) {
            if (character == null) {
                return false;
            }
        }
        return true;
    }

    private String leftAttemptsAsString() {
        int leftAttempts = maxAttempts - failedAttempts;
        return "(" + leftAttempts + ")";
    }

    private String phraseStateAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : phraseState) {
            if (c == null) {
                stringBuilder.append('_');
            } else {
                stringBuilder.append(c);
            }
        }
     return stringBuilder.toString();
    }
}


