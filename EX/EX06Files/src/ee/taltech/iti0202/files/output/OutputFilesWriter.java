package ee.taltech.iti0202.files.output;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OutputFilesWriter {

    /**
     * Writes list elements to a text file.
     * Each element in list is a text line
     *
     * @param lines    list containing text lines
     * @param filename shows where to write everything
     * @return
     */
    public boolean writeLinesToFile(List<String> lines, String filename) {
        Path path = Path.of(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines
            ) {
                writer.write(line + "\n");
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
