import java.io.File;
import java.util.Scanner;

public class ReaderTXT {

    public static String read (String pathFile) {

        StringBuilder text = new StringBuilder();

        try {
            File file = new File(pathFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Remove all non-alphabetic characters
                line = line.replaceAll("[^a-zA-Z]", "");
                // Convert to lowercase
                line = line.toLowerCase();
                text.append(line);
            }

            scanner.close();

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        return text.toString();
    }
}
