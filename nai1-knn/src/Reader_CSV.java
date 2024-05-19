import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader_CSV {

    private List<String> lines;
    private List<List<String>> rows;


    public Reader_CSV() {
        lines = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public List<List<String>> read(String path) {


        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                    i++;
                }
            }
            System.out.println("Read " + i + " lines from " + path);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Error reading file");
        }

        for (String line : lines) {
            String[] set = line.split(";");
            rows.add(Arrays.asList(set));
        }


        return rows;
    }


}
