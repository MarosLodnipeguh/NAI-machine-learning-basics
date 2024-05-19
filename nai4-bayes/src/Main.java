import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        String trainSetPath = "resources/trainingset.csv";
        List<String[]> trainSet = new ReaderCSV().read(trainSetPath);

        Classificator classificator = new Classificator(trainSet);

        String testSetPath = "resources/testset.csv";
        List<String[]> testSet = new ReaderCSV().read(testSetPath);

        for (String[] row : testSet) {

            // Skip empty rows
            if (row.length == 0) {
                continue;
            }

            String classification = classificator.classify(row);
            System.out.println(Arrays.toString(row) + " - Decision: " + classification);
        }

        // prompt for user input
        while (true) {
            System.out.println("--------------------");
            System.out.println("Enter data to classify: ");
            int inputLength = trainSet.get(0).length - 1;

            String[] inputWords = new String[inputLength];

            for (int i = 0; i < inputLength; i++) {
                System.out.print("word " + (i + 1) + ": ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                inputWords[i] = input;
            }

            String classification = classificator.classify(inputWords);
            System.out.println("Classification: " + classification);
        }
    }


}
