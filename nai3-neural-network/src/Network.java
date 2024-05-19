import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;

public class Network {


    private List<String> classNames;
    private int inputSize; // size of the input vector

    // perceptrons map k=className, v=perceptron
    private Map<String, Perceptron> perceptronsMap;

    public Network(List<String> classNames, int inputSize) {
        this.classNames = classNames;
        this.inputSize = inputSize;
        perceptronsMap = new HashMap<>();

        initializePerceptrons();
    }

    // initialize perceptrons with random weights and threshold
    public void initializePerceptrons () {
        // for each class create a perceptron
        for (String className : classNames) {

            // random threshold between -5.0 and 5.0
//            double threshold = randomDouble();

            // random threshold between 0.01 and 0.1
            Random random = new Random();
            double threshold = 0.01 + (0.1-0.01) * random.nextDouble();

            // random weight vectors
            double[] weightVectors = new double[inputSize];
            for (int i = 0; i < inputSize; i++) {

                // random weight between -5.0 and 5.0
//                weightVectors[i] = randomDouble();

                // random weight between 0.5 and 1.0
                Random random2 = new Random();
                weightVectors[i] = 0.5 + (1.0-0.5) * random2.nextDouble();
            }

            // create perceptron
            Perceptron perceptron = new Perceptron(weightVectors, threshold);

            // add perceptron to perceptrons map
            perceptronsMap.put(className, perceptron);
        }
    }


    public void train(List<Entry> trainSet, double learningRate) {

        Collections.shuffle(trainSet);

        // train each perceptron
        for (Map.Entry<String, Perceptron> e : perceptronsMap.entrySet()) {

            int correct = 0;
            int wrong = 0;

            String correctClassName = e.getKey();
            Perceptron perceptron = e.getValue();

            for (Entry entry : trainSet) {
                // calculate discrete {0,1} output
                int output = perceptron.computeY(entry.getVector());

                // 1 if the entry className belongs to perceptron, 0 otherwise
                int expectedOutput = entry.getClassName().equals(correctClassName) ? 1 : 0;

                if (output == expectedOutput) {
//                        System.out.println("Expected: " + ANSI_PURPLE + expectedOutput + ANSI_GREEN + " | Output: " + output + ANSI_RESET);
                    correct++;
                } else {
//                        System.out.println("Expected: " + ANSI_PURPLE + expectedOutput + ANSI_RED + " | Output: " + output + ANSI_RESET);
                    wrong++;
                }

                // learn
                perceptron.learn(entry.getVector(), learningRate, output, expectedOutput);

            }
//                double accuracy = (double) correct / (correct + wrong);
//                Format format = new DecimalFormat("#0.00");
//                System.out.println("Training Perceptron accuracy: " +ANSI_RED + e.getKey() + " " + ANSI_BLUE + format.format(accuracy * 100) + "%" + ANSI_RESET);
        }


    }

    public void testDiscrete (List<Entry> testSet) {
        Collections.shuffle(testSet);

        int correct = 0;
        int wrong = 0;

        for (Map.Entry<String, Perceptron> e : perceptronsMap.entrySet()) {
            int correctL = 0;
            int wrongL = 0;

            for (Entry entry : testSet) {

                String className = e.getKey();
                Perceptron perceptron = e.getValue();

                // classify entry
                int output = perceptron.computeY(entry.getVector());
                int expectedOutput = entry.getClassName().equals(className) ? 1 : 0;

                if (output == expectedOutput) {
                    correctL++;
                    correct++;
                } else {
                    wrongL++;
                    wrong++;
                }
            }
//            double accuracy = (double) correctL / (correctL + wrongL);
//            Format format = new DecimalFormat("#0.00");
//            System.out.println("Discrete testing accuracy: " +ANSI_RED + e.getKey() + " " + ANSI_BLUE + format.format(accuracy * 100) + "%" + ANSI_RESET);
        }

        double accuracy = (double) correct / (correct + wrong);
        Format format = new DecimalFormat("#0.00");
        System.out.println("Discrete testing accuracy: " + ANSI_BLUE + format.format(accuracy * 100) + "%" + ANSI_RESET);


    }

    // test the network using maximum selection
    public void testMax (List<Entry> testSet) {
        Collections.shuffle(testSet);

        int correct = 0;
        int wrong = 0;

        // test each entry
        for (Entry entry : testSet) {
            // classify entry
            String predicted = classifyMax(entry);
            if (predicted.equals(entry.getClassName())) {
                correct++;
            } else {
                wrong++;
            }
        }

        double accuracy = (double) correct / (correct + wrong);
        Format format = new DecimalFormat("#0.00");
        System.out.println(ANSI_RED + "Maximum selector" +ANSI_RESET + " testing accuracy: " + ANSI_BLUE + format.format(accuracy * 100) + "%" + ANSI_RESET);

    }

    // compute the output of the network for a given entry using maximum selection
    public String classifyMax (Entry entry) {
        Map<String, Double> outputsMap = new HashMap<>();

        // for each perceptron compute raw NET
        for (Map.Entry<String, Perceptron> e : perceptronsMap.entrySet()) {
            String className = e.getKey();
            Perceptron perceptron = e.getValue();

            double output = perceptron.computeRawNET(entry.getVector());
            outputsMap.put(className, output);

//            System.out.println("raw NET: " + output + " " + className);
        }

        // maximum selection
        Map.Entry<String, Double> maxEntry = outputsMap.entrySet().stream().max(Map.Entry.comparingByValue()).get();

        return maxEntry.getKey();
    }




    // a random double between -5.0 and 5.0
    public double randomDouble () {
        Random random = new Random();
        return random.nextDouble() * 10.0 - 5.0;
    }



    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_GREEN = "\u001B[32m";
    public  final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_CYAN = "\u001B[36m";


}
