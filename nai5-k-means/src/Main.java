import java.util.List;

public class Main {

    public static void main (String[] args) {
        String testSetPath = "resources/test.csv";
        List<Double[]> testSet = ReaderCSV.read(testSetPath);
        int k = 3;

        Algorithm algorithm = new Algorithm();

        algorithm.init(k, testSet);
        algorithm.group();
    }


}
