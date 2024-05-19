import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderCSV {

    public static List<Double[]> read (String path) {

        List<Double[]> rows = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {

                    // split line by ; and add to list
                    String[] entries = line.split(","); // , not ;

                    // convert strings to doubles
                    Double[] entriesDouble = new Double[entries.length];
                    for (int j = 0; j < entries.length; j++) {
                        entriesDouble[j] = Double.parseDouble(entries[j]);
                    }

                    // add row to list of rows
                    rows.add(entriesDouble);

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