package ee.taltech.iti0202.files.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFilesBufferReader implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        Path path = Path.of(filename);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new FileReaderException("No such file", e);
        }

    }
}
