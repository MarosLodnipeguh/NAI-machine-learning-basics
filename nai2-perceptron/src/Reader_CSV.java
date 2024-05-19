import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader_CSV {

    public List<List<String>> read(String path) {

        List<List<String>> rows = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {

                    // split line by ; and add to list
                    String[] set = line.split(";");
                    rows.add(Arrays.asList(set));

                    // count read lines
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

        // return list of rows, each row is a list of strings
        return rows;
    }


}