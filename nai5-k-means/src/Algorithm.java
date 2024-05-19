import java.nio.DoubleBuffer;
import java.util.*;

public class Algorithm {


    private List<Entry> entries; // each entry knows its group
    private Map<String, Double[]> centroids;
    int dimensions;
    int k;

    public Algorithm() {
        entries = new ArrayList<>();
        centroids = new HashMap<>();
    }

    public void init (int k, List<Double[]> input) {
        dimensions = input.get(0).length;
        this.k = k;

        // create Entry objects, each with a random group
        for (Double[] entry : input) {
            entries.add(new Entry("Group " + (int) (Math.random() * k), entry));
        }

        // calculate centroids for each group
        calculateCentroids(k);

        // for each entry, fill distance to its centroid
        for (Entry entry : entries) {
            String group = entry.getGroup();
            Double[] centroid = centroids.get(group);
            double distance = calculateDistance(entry.getValues(), centroid);
            entry.setDistanceToCentroid(distance);
        }
    }

    public void group () {

        // print number of members in each group
        printGroups();

        // calculate and print E
        double E = 0;
        for (Entry entry : entries) {
            E += entry.getDistanceToCentroid();
        }
        System.out.println("E: " + E);
        System.out.println("--------------------");

        // calculate new centroids
        calculateCentroids(k);

        // calculate distance from each entry to each centroid and assign to the closest group
        assignToGroup();

        // calculate new E
        double newE = 0;
        for (Entry entry : entries) {
            newE += entry.getDistanceToCentroid();
        }

        // run next iteration if E changed
        if (newE != E) {
            group();
        } else {
            System.out.println("E did not change");
        }
    }

    private void printGroups () {
        for (int i = 0; i < k; i++) {
            int members = 0;
            for (Entry entry : entries) {
                if (entry.getGroup().equals("Group " + i)) {
                    members++;
                }
            }
            System.out.println("Group " + i + " has " + members + " members");
        }
    }

    private void calculateCentroids (int k) {
        for (int i = 0; i < k; i++) {
            Double[] centroid = new Double[dimensions];
            for (int j = 0; j < dimensions; j++) {
                double sum = 0;
                int groupSize = 0;
                for (Entry entry : entries) {
                    if (entry.getGroup().equals("Group " + i)) {
                        sum += entry.getValues()[j];
                        groupSize++;
                    }
                }
                centroid[j] = sum / groupSize;
            }
            centroids.put("Group " + i, centroid);
        }
    }

    private void assignToGroup () {
        // for each entry
        for (Entry entry : entries) {
            // for each centroid
            for (Map.Entry<String, Double[]> centroid : centroids.entrySet()) {
                double distance = calculateDistance(entry.getValues(), centroid.getValue());

                // if distance is smaller than the current distance to centroid, update distance and group
                if (distance < entry.getDistanceToCentroid()) {
                    entry.setDistanceToCentroid(distance);
                    entry.setGroup(centroid.getKey());
                }
            }
        }
    }

    private double calculateDistance (Double[] entry, Double[] centroid) {
        double distance = 0;
        for (int i = 0; i < dimensions; i++) {
            double diff = entry[i] - centroid[i];
            distance += Math.pow(diff, 2);
        }
        return distance;
    }





}
