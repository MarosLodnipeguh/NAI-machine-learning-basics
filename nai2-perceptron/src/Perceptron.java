import java.util.List;

public class Perceptron {


//    private List<Double> entryVectors;
    private List<Double> weightVectors;
    private double threshold; // próg

    public Perceptron(List<Double> weightVectors, double threshold) {
        this.weightVectors = weightVectors;
        this.threshold = threshold;
    }

    public int compute(List<Double> entryVectors) {
        double NET = 0.0;

        for (int i = 0; i < entryVectors.size(); i++) {
            NET += entryVectors.get(i) * weightVectors.get(i);
        }
        NET -= threshold;

//        System.out.println("NET: " + NET);
        
        if (NET >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void learn(List<Double> entryVectors, double learningRate, int output, int expectedOutput) {

        int dy = expectedOutput - output;

        // new weight vectors:  w' = w + (d-y) * learningRate * x
        for (int i = 0; i < weightVectors.size(); i++) {
            double newWeight = weightVectors.get(i) + dy * learningRate * entryVectors.get(i);
            weightVectors.set(i, newWeight);
        }

        // new threshold:   t' = t - (d-y) * learningRate
        threshold = threshold - dy * learningRate;

    }
}
