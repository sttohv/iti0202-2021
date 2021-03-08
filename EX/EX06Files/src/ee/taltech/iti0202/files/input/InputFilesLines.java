package ee.taltech.iti0202.files.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFilesLines implements InputFilesReader{
    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        Path path = Path.of(filename);
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new FileReaderException("No such file", e.getCause());
        }
        return lines;
    }
}
