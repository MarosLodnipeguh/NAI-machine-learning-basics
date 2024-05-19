import java.util.Arrays;

public class Perceptron {

    private double[] weightVector;
    private double threshold; // próg

    public Perceptron (double[] weightVectors, double threshold) {
        this.weightVector = weightVectors;
        this.threshold = threshold;
    }

    public int computeY (double[] entryVector) {
        double NET = 0.0;

        for (int i = 0; i < entryVector.length; i++) {
            NET += entryVector[i] * weightVector[i];
        }
        NET -= threshold;

//        System.out.println("NET: " + NET);

        if (NET >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public double computeRawNET (double[] entryVectors) {

        // normalize vectors
        double[] normalizedEntryVectors = normalizeVector(entryVectors);
        double[] normalizedWeightVectors = normalizeVector(weightVector);

//        System.out.println("Normalized entry vectors: " + Arrays.toString(normalizedEntryVectors));
//        System.out.println("Normalized weight vectors: " + Arrays.toString(normalizedWeightVectors));

        double NET = 0.0;
        for (int i = 0; i < entryVectors.length; i++) {
            NET += normalizedEntryVectors[i] * normalizedWeightVectors[i];
        }

        return NET;
    }

    public double[] normalizeVector(double[] vector) {

        // x / sqrt ( x1^2 + x2^2 + ... + xn^2)
        // kazda składowa wektora / jego dlugosc

        double[] normalizedVector = new double[vector.length];
        double vectorLength = 0.0;
        for (int i = 0; i < vector.length; i++) {
            vectorLength += Math.pow(vector[i], 2);
        }
        vectorLength = Math.sqrt(vectorLength);

        for (int i = 0; i < vector.length; i++) {
            normalizedVector[i] = vector[i] / vectorLength;
        }

        return normalizedVector;
    }

    public void learn(double[] entryVectors, double learningRate, int previousOutput, int expectedOutput) {

        // d - y
        int dy = expectedOutput - previousOutput;

        // new weight vectors:  w' = w + (d-y) * learningRate * x
        for (int i = 0; i < weightVector.length; i++) {
            double newWeight = weightVector[i] + dy * learningRate * entryVectors[i];
            weightVector[i] = newWeight;
        }

        // new threshold:   t' = t - (d-y) * learningRate
        threshold = threshold - dy * learningRate;

    }
}