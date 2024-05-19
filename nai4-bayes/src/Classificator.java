import java.util.*;
import java.util.stream.Collectors;


public class Classificator {

    private List<String[]> trainSet;
    private List<String[]> trainingColumns;
    private String[] classificationsColumn;
    private Map<String, Integer> classMap;
    int totalCount;

    public Classificator (List<String[]> trainSet) {
        this.trainSet = trainSet;

        classMap = new HashMap<>();
        // Count the number of appearances of each class
        for (String[] row : trainSet) {
            String classification = row[row.length - 1]; // last column - classification
            classMap.put(classification, classMap.getOrDefault(classification, 0) + 1);
        }

        // Count the total number of entries
        totalCount = trainSet.size();

        prepareTrainSetColumns();
    }

    // Transpose the trainset - one String[] for each column + fill the classifications column
    public void prepareTrainSetColumns () {

        // transposed trainset
        List<String[]> columnList = new ArrayList<>();

        int rows = trainSet.size(); // ->
        int cols = trainSet.getFirst().length; // v

        for (int i = 0; i < cols; i++) {
            String[] tempColumn = new String[rows];

            // Iterate through each row and add the element to the temporary column array
            for (int j = 0; j < rows; j++) {
                String[] tempRow = trainSet.get(j);
                tempColumn[j] = tempRow[i];
            }

            // Add the temporary column array to the columnList
            columnList.add(tempColumn);
        }

        // trim the last column (classifications)
        trainingColumns = columnList.stream().limit(columnList.size() - 1).collect(Collectors.toList());

        // save the classifications column to a separate List
        classificationsColumn =  columnList.getLast();
    }

    public String classify (String[] inputWords) {

        Map<String, Double> probabilities = new HashMap<>();

        // loop through each classification to fill the probabilities map
        for (Map.Entry<String, Integer> entry : classMap.entrySet()) {

            String mapClassification = entry.getKey();
            int classFrequency = entry.getValue();

            // class probability P(Y)
            double wordProbability = (double) classFrequency / totalCount;

            // loop through each column
            for (int i = 0; i < trainingColumns.size(); i++) {

                // count the specific word in the specific column of trainset
                int wordAppearances = 0;

                // get the word from the input for the specific column
                String inputWord = inputWords[i];

                // loop through each word in the column
                String[] column = trainingColumns.get(i);
                for (int j = 0; j < column.length; j++) {
                    String trainingWord = column[j];
                    String correctClassification = classificationsColumn[j];

                    // look for words matching the input word WITH the desired classification
                    if (Objects.equals(inputWord, trainingWord) && Objects.equals(mapClassification, correctClassification)) {
                        wordAppearances++;
                    }
                }

//                System.out.println("Appearances: " + appearances + " for word: " + inputWord + " in column: " + (i+1) + " for classification: " + mapClassification);

                if (wordAppearances > 0) {
                    // P(X|Y) no smoothing
                    wordProbability *= (double) wordAppearances / classFrequency;
                } else {
                    // smoothing
                    long differentWords = Arrays.stream(column).distinct().count();
                    int smoothedClassFrequency = classFrequency + (int) differentWords;
                    // P(X|Y)
                    wordProbability *= (double) 1 / smoothedClassFrequency;
                }
            }
            probabilities.put(mapClassification, wordProbability);
        }

//        System.out.println(probabilities);

        // return the classification with the highest probability
        return probabilities.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}
