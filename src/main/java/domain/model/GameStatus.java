package domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;

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
        this.phraseState = new GameStatusHelper().preparePhraseState(phrase);
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

    public void incrementFailureCounter() {
        failedAttempts++;
    }

    public void incrementSuccessCounter() {
        successAttemps++;
    }

    public void updateHistory(char letter) {
        history.add(letter);
    }

    public int getTotalAttemts() {
        return  successAttemps + failedAttempts;
    }

    public boolean historyContains(char letter) {
        return history.contains(letter);
    }

    public static  class GameStatusHelper {
        public Character[] preparePhraseState(String phrase){
            char[] chars = phrase.toCharArray();
            Character[] result = new Character[chars.length];

            for (int i = 0; i < chars.length; i++) {
                if (!Character.isLetter(chars[i])){
                    result[i] = chars[i];
                }
            }

            return  result;

        }
    }
}


