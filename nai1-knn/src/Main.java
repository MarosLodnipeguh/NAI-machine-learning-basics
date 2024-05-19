import java.util.List;


public class Main {

    public static void main (String[] args) {

        String trainsetPath = "Resources/train-set.csv";
        String testsetPath = "Resources/test-set.csv";
        String attributesPath = "Resources/Attribute Information.txt";

        List<List<String>> trainset;
        List<List<String>> testset;

        Reader_CSV reader_csv = new Reader_CSV();
        trainset = reader_csv.read(trainsetPath);

        reader_csv = new Reader_CSV();
        testset = reader_csv.read(testsetPath);

//        for (List row : trainset) {
//            System.out.println(row);
//        }
//
//        for (List row : testset) {
//            System.out.println(row);
//        }

        int k = 20;

        KNN knn = new KNN(k, trainset, testset);
        knn.knnstart();


        // chart purposes

//        Map<Integer, Double> results = new HashMap<>();
//        for (int i = 1; i < k+1; i++) {
//            KNN knn = new KNN(i, trainset, testset, attributes);
//            double res = knn.knnChart();
//
//            results.put(i, res);
//        }
//
//        DecimalFormat df = new DecimalFormat("#.##");
//        for (Map.Entry<Integer, Double> entry : results.entrySet()) {
////            System.out.println("K: " + entry.getKey() + " | Accuracy: " + df.format(entry.getValue()) + "%");
//            System.out.println(entry.getKey() + ";" + df.format(entry.getValue()));
//        }


    }


}
