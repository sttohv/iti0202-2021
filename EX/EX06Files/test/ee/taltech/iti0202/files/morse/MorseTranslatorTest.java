package ee.taltech.iti0202.files.morse;

import ee.taltech.iti0202.files.input.InputFilesScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MorseTranslatorTest {
    @Test
    public void testMorseAdded(){
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> lines = scanner.readTextFromFile("morse.txt");

        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines);

        Assertions.assertEquals(".-", codes.get("a"));

    }

    @Test
    public void testTranslateLinesToMorse(){
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> lines = scanner.readTextFromFile("morse.txt");

        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines);

        List<String> lines2 = scanner.readTextFromFile("input.txt");
        Assertions.assertEquals(translator.translateLinesToMorse(lines2).get(1), "-. . .. -..");
    }

    @Test
    public void testTranslateLineFromMorse(){
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> lines = scanner.readTextFromFile("morse.txt");

        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines);

        List<String> lines2 = translator.translateLinesToMorse(scanner.readTextFromFile("input.txt"));
        Assertions.assertEquals("eks ma siis kirjutan,", translator.translateLinesFromMorse(lines2).get(0));
    }

}