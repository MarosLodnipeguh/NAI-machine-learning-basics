import java.util.Map;

public class VectorCreator {

    public static double[] createVector(Map<Character, Integer> letterMap) {

        double[] vector = new double[26];
        double[] appearances = new double[26];
        double sum = 0;

        // get sum of all appearances
        for (int i = 0; i < 26; i++) {
            int value = letterMap.getOrDefault((char) ('a' + i), 0);
            appearances[i] = value;
            sum += value;
        }

        // get proportions
        for (int i = 0; i < 26; i++) {
            if (sum == 0 ) {
                throw new IllegalArgumentException("number of letter appearances is 0");
            }
            vector[i] = appearances[i] / sum;
        }

//        Arrays.stream(vector).forEach(System.out::println);

        return vector;
    }
}
