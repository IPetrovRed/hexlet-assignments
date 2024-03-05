package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String letters, String word) {
        letters = letters.toLowerCase();
        word = word.toLowerCase();

        List<String> lettersList = new ArrayList<>(Arrays.asList(letters.split("")));
        List<String> wordList = new ArrayList<>(Arrays.asList(word.split("")));

        for (String letter : wordList) {
            if (!lettersList.contains(letter)) {
                return false;
            }
            lettersList.remove(letter);
        }

        return true;
    }
}
//END
