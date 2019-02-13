package hangman;

import domain.HangmanGameService;
import domain.model.GameStatus;
import domain.port.PhraseRepository;
import infrastructure.memory.InMemoryPhraseRepository;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        HangmanGameService hangmanGameService = new HangmanGameService();
        PhraseRepository phraseRepository = new InMemoryPhraseRepository();

        String phrase = phraseRepository.getPhrase();
        GameStatus gameStatus = hangmanGameService.createGameStatus(phrase);
        Scanner scanner = new Scanner(System.in);
        do {

            System.out.println("Podaj lierę:");
            char letter = scanner.nextLine().charAt(0);
            hangmanGameService.processNextLatter(letter, gameStatus);
            System.out.println(gameStatus.getCurrentPhraseStateWithLeftAttempts());

        } while (!gameStatus.isGameFinished());

        switch (gameStatus.getFinishedGameStatus()) {
            case WON:
                System.out.println("Gratulacje. Wygrales w " + gameStatus.getTotalAttemts() + "krokach");
                break;
            case LOSE:
                System.out.println("Przegrales");
                break;
            case RUNNING:
                System.out.println("Cos poszlo nie tak");

        }

    }


}
