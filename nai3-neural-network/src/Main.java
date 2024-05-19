import java.util.*;

public class Main {

    public static void main (String[] args) {

        String trainingFolderPath = "training";
        // train set (entries)
        List<Entry> trainSet = new ArrayList<>();
        // classes names (folders)
        List<String> trainClassNames = new ArrayList<>();

        // create train set & fill classNames from classes folders
        DataSetCreator.createDataSet(trainingFolderPath, trainSet, trainClassNames);
        System.out.println("Classes initiated: " + trainClassNames);
        System.out.println("Train set size: " + trainSet.size() + "\n");



        // create network
        int inputSize = trainSet.get(0).getVector().length;
        Network network = new Network(trainClassNames, inputSize);

        double learningRate = 0.1;
        int epoch = 1;

        // train network for the first time
        network.train(trainSet, learningRate);
        // test network
        System.out.println("Epoch: " + epoch);
        // discrete testing
        network.testDiscrete(trainSet);
        // maximum selection testing
        network.testMax(trainSet);


        // create test set
//        String testFolderPath = "testing";
//        List<Entry> testSet = new ArrayList<>();
//        List<String> testClassNames = new ArrayList<>();
//        DataSetCreator.createDataSet(testFolderPath, testSet, testClassNames);


        // prompt user input
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the file you want to classify (or press Enter to run next training epoch): ");

            String input = scanner.nextLine();

            if (input.isEmpty()) {
                // run next epoch
                network.train(trainSet, learningRate);
                epoch++;
                System.out.println("Epoch: " + epoch);
                network.testDiscrete(trainSet);
                network.testMax(trainSet);
                continue;
            }

            // classify input file
            String text = ReaderTXT.read(input);
            text = text.replaceAll("[^a-zA-Z]", "");
            text = text.toLowerCase();

            Map<Character, Integer> letterMap = LetterMapper.getLetterMap(text);
//            System.out.println("Letter map: " + letterMap);
            double[] vector = VectorCreator.createVector(letterMap);
            Entry entry = new Entry(vector, "unknown");
            String output = network.classifyMax(entry);


//            System.out.println();
            System.out.println("The text is classified as: " + ANSI_BLUE + output + ANSI_RESET);
            System.out.println();
        }

    }







    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static  final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
}
