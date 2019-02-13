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




}
