import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void askIfExtraLearn (Trainer trainer, List<Entry> testSet) {
        while (true) {
            System.out.println("Do you want to train one more time? (y/n):");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                trainer.train();
                trainer.test(testSet);
            } else {
                break;
            }
        }
    }

    public static void beginUserInput (int attributeCount, Perceptron perceptron, String exit0, String exit1) {
        while (true) {
            System.out.println("-----------------------");
            System.out.println("Enter " + ANSI_PURPLE + attributeCount + ANSI_RESET + " attributes of the entry you want to classify: ");

            List<Double> userVectors = new ArrayList<>();

            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < attributeCount; i++) {
                System.out.print("Value " + (i+1) + ": ");
                userVectors.add(Double.valueOf(scanner.nextLine()));
            }

            int output = perceptron.compute(userVectors);

            String classification = output == 0 ? exit0 : exit1;

            System.out.println(ANSI_PURPLE + "Calculated classification: " + ANSI_RESET + classification);
        }
    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";


}
