import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class LetterMapper {

    // k=letter v=appearances
    public static Map<Character, Integer> getLetterMap (String text) {

        // k=letter v=appearances
        Map<Character, Integer> letterMap = new LinkedHashMap<>();

        // initialize the map with 0 appearances for each letter
        for (char c = 'a'; c <= 'z'; c++) {
            letterMap.put(c, 0);
        }

        char[] characters = text.toCharArray();

        for (char letter : characters) {
            letterMap.put(letter, letterMap.getOrDefault(letter, 0) + 1);
        }

        return letterMap;
    }


}
