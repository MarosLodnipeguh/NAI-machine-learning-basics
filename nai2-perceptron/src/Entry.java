import java.util.ArrayList;
import java.util.List;

public class Entry {

    private List<Double> vectors;
    private String name;

    public Entry(List<String> csvRow) {
        vectors = new ArrayList<>();

        for (int i = 0; i < csvRow.size()-1; i++) {
            Double newVector = Double.parseDouble(csvRow.get(i));
            vectors.add(newVector);
        }

        this.name = csvRow.get(csvRow.size() - 1);

    }

    public List<Double> getVectors() {
        return vectors;
    }

    public String getName() {
        return name;
    }

}
