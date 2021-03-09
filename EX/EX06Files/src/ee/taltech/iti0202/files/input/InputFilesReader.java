package ee.taltech.iti0202.files.input;

import java.util.List;

public interface InputFilesReader {
    /**
     * List where every element is a line from a text
     *
     * @param filename where to get the file
     * @return list of Strings
     */
    List<String> readTextFromFile(String filename);
}
