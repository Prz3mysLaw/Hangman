package domain;

import domain.model.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class HangmanGameService {

    public List<Integer> performCharacter(char c, String phrase){
        char[] charsFromPhrase = phrase.toCharArray();
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < charsFromPhrase.length; i++) {
            if (equalsIgnoreCase(c, charsFromPhrase[i])){
                results.add(i);
            }
        }

      return results;
    }

    private boolean equalsIgnoreCase(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }

    public GameStatus createGameStatus(String phrase){
        return new GameStatus(phrase, 7);
    }

    public void processNextLatter(char letter, GameStatus gameStatus){
        String phrase = gameStatus.getPhrase();

        boolean letterAlreadyUsed = gameStatus.historyContains(letter);
        if (letterAlreadyUsed){
            gameStatus.incrementFailureCounter();
        } else {
            List<Integer> letterIds = performCharacter(letter, phrase);
            final Character[] phraseState = gameStatus.getPhraseState();
            letterIds.forEach(index ->
            {phraseState[index] = gameStatus.getPhrase().charAt(index);});
            performCharacterIncrement(letterIds.size() > 0, gameStatus);
        }
        gameStatus.updateHistory(letter);
    }

    private void performCharacterIncrement(boolean success, GameStatus gameStatus) {
        if (success){
            gameStatus.incrementSuccessCounter();
        } else {
            gameStatus.incrementFailureCounter();
        }

    }


}
