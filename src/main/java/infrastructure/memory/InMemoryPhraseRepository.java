package infrastructure.memory;

import domain.port.PhraseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InMemoryPhraseRepository implements PhraseRepository {

    private List<String> phrases = Arrays.asList("Ala ma kota");

    public String getPhrase(){
        Random random = new Random();
        return phrases.get(random.nextInt(phrases.size()));
    }
}
