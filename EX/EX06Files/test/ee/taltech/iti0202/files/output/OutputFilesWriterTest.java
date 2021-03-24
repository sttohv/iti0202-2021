package ee.taltech.iti0202.files.output;

import ee.taltech.iti0202.files.input.InputFilesScanner;
import ee.taltech.iti0202.files.morse.MorseTranslator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OutputFilesWriterTest {
    @Test
    public void testOutputCorrectFilename(){

        List<String> lines = new ArrayList<>();
        lines.add("Testid");
        lines.add("on");
        lines.add("toredad");

        OutputFilesWriter outputFilesWriter = new OutputFilesWriter();
        boolean a = outputFilesWriter.writeLinesToFile(lines, "output.txt");

        Assertions.assertTrue(a);

    }

    @Test
    public void testOutputInCorrectFilename(){
        List<String> lines = new ArrayList<>();
        lines.add("Testid");
        OutputFilesWriter outputFilesWriter = new OutputFilesWriter();

        boolean a = outputFilesWriter.writeLinesToFile(lines, "outpt.txt");

        Assertions.assertTrue(a);


    }

}