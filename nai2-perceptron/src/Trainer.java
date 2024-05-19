import java.util.*;

public class Trainer {

    private List<Entry> trainSet;
    private Perceptron perceptron;
    private double learningRate;
    private String exit0;
    private String exit1;

    public Trainer(Perceptron perceptron, List<Entry> trainSet, double learningRate, String exit0, String exit1) {
        this.trainSet = trainSet;
        this.learningRate = learningRate;
        this.perceptron = perceptron;
        this.exit0 = exit0;
        this.exit1 = exit1;

        // shuffle trainset
        Collections.shuffle(trainSet);
    }

    public void train() {

        for (Entry entry : trainSet) {

            // assign expected output
            int expectedOutputInt = entry.getName().equals(exit0) ? 0 : 1;

            // compute first output from trainset
            int output = perceptron.compute(entry.getVectors());

//            if (output == expectedOutputInt) {
//                System.out.println("Expected: " + ANSI_PURPLE + expectedOutputInt+ ANSI_GREEN + " | Output: " + output + ANSI_RESET);
//            } else {
//                System.out.println("Expected: " + ANSI_PURPLE + expectedOutputInt + ANSI_RED + " | Output: " + output + ANSI_RESET);
//            }

            // learn
            perceptron.learn(entry.getVectors(), learningRate, output, expectedOutputInt);
        }
    }

    public void test(List<Entry> testSet) {

        int exit0_hits = 0;
        // count how many times exit0 appears in testset
        int exit0_count = testSet.stream().filter(entry -> entry.getName().equals(exit0)).toArray().length;

        int exit1_hits = 0;
        // count how many times exit1 appears in testset
        int exit1_count = testSet.stream().filter(entry -> entry.getName().equals(exit1)).toArray().length;


        for (Entry entry : testSet) {
            // assign expected output
            String expectedOutputString = entry.getName();
            int expectedOutputInt = expectedOutputString.equals(exit0) ? 0 : 1;

            // compute first output from trainset
            int outputInt = perceptron.compute(entry.getVectors());
            String outputString = outputInt == 0 ? exit0 : exit1;

            // if correct classification
            if (outputInt == expectedOutputInt) {
                System.out.println("Expected: " + ANSI_PURPLE + expectedOutputString+ ANSI_GREEN + " | Output: " + outputString + ANSI_RESET);

                // count correct hits for current class
                if (outputInt == 0) {
                    exit0_hits++;
                } else {
                    exit1_hits++;
                }
            } else { // if incorrect classification
                System.out.println("Expected: " + ANSI_PURPLE + expectedOutputString + ANSI_RED + " | Output: " + outputString + ANSI_RESET);
            }
        }

        double accuracy = (exit0_hits + exit1_hits) / (double) testSet.size() * 100.0;

        double exit0_accuracy = exit0_hits / (double) exit0_count * 100.0;
        double exit1_accuracy = exit1_hits / (double) exit1_count * 100.0;

        System.out.println("-----------------------");
        System.out.println("Accuracy: " + ANSI_BLUE + accuracy + "%"+ ANSI_RESET);
        System.out.println(ANSI_CYAN + exit0 + ANSI_RESET + " accuracy: " + ANSI_GREEN + exit0_accuracy + "%" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + exit1 + ANSI_RESET + " accuracy: " + ANSI_GREEN + exit1_accuracy + "%" + ANSI_RESET);


    }



    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public  final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_CYAN = "\u001B[36m";



}
