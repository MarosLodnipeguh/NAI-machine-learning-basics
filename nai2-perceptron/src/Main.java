import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main (String[] args) {
        String trainsSetPath = "Resources/train-set.csv";
        String testSetPath = "Resources/test-set.csv";
        double learningRate = 0.01; // a

        Reader_CSV reader = new Reader_CSV();

        // read csv files, init Entry objects and add them to lists
        List<Entry> trainSet = new ArrayList<>(reader.read(trainsSetPath).stream().map(Entry::new).toList());
        List<Entry> testSet = new ArrayList<>(reader.read(testSetPath).stream().map(Entry::new).toList());

        // init weight vectors with random doubles
        List<Double> weightVectors = new ArrayList<>();
        int attributeCount = trainSet.get(0).getVectors().size();
        for (int i = 0; i < attributeCount; i++) {
            weightVectors.add(randomDouble());
        }
        System.out.println("Initial weight vectors: " + weightVectors);

        Perceptron perceptron = new Perceptron(weightVectors, randomDouble());
        
        String exit0;
        String exit1;
        
        // get 2 possible exits
        List<String> possibleExits = trainSet.stream().map(Entry::getName).distinct().toList();

        // assign possible exit (y) values to 0 or 1
        if (possibleExits.size() == 2) {
            exit0 = possibleExits.get(0);
            exit1 = possibleExits.get(1);
        } else {
            throw new IllegalArgumentException("There are more than 2 possible exits");
        }

        Trainer trainer = new Trainer(perceptron, trainSet, learningRate, exit0, exit1);
        // train
        trainer.train();
        // test
        trainer.test(testSet);

        // ask user for extra learning
        UI.askIfExtraLearn(trainer, testSet);

        // prompt user for input
        UI.beginUserInput(attributeCount, perceptron, exit0, exit1);
    }

    // a random double between -5.0 and 5.0
    public static double randomDouble () {
        Random random = new Random();
        return random.nextDouble() * 10.0 - 5.0;
    }


}
