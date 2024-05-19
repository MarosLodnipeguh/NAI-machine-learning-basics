import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSetCreator {

    public static void createDataSet (String classesFolderPath, List<Entry> DataSet, List<String> classNames) {

        // get classes (folders)
        File[] classFolders = new File(classesFolderPath).listFiles(File::isDirectory);

        // for each class get files
        for (File folder : classFolders) {
            String className = folder.getName();
            classNames.add(className);

            File[] classFiles = folder.listFiles();

            for (File file : classFiles) {

                // check if file is a directory
                if (file.isDirectory()) {
                    System.out.println("Error: file is a directory: " + file.getPath());
                    continue;
                }

                // check if file is empty
                if (file.length() == 0) {
                    throw new IllegalArgumentException("File is empty: " + file.getPath());
                }

                // read text from file
                String text = ReaderTXT.read(file.getPath());

                // get letterMap from text
                Map<Character, Integer> letterMap = LetterMapper.getLetterMap(text);

                // create proportions vector from letterMap
                double[] vector = VectorCreator.createVector(letterMap);
                // create entry
                Entry entry = new Entry(vector, className);
                // add entry to trainSet
                DataSet.add(entry);
            }
        }
    }
}
