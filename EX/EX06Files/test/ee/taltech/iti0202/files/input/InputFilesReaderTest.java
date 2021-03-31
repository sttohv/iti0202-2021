package ee.taltech.iti0202.files.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputFilesBufferReaderTest {

    @Test
    public void testInputFilesBufferReaderCorrectFile(){
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        System.out.println(System.getProperty("user.dir"));
        List<String> result = bufferReader.readTextFromFile("input.txt");
        Assertions.assertEquals("eks ma siis kirjutan,", result.get(0));
    }

    @Test
    public void testInputFilesBufferReaderInCorrectFilename(){
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        String a= "";
        try {

        List<String> result = bufferReader.readTextFromFile("iinput.txt");}
        catch (Exception e){
            a = e.getMessage();
        }
        Assertions.assertEquals("No such file", a);
    }

}
class InputFilesScannerTest {
    @Test
    public void testInputFilesScannerCorrectFilename(){
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> result = scanner.readTextFromFile("input.txt");
        Assertions.assertEquals("neid", result.get(1));
    }

    @Test
    public void testInputFilesScannerInCorrectFilename(){
        InputFilesScanner scanner = new InputFilesScanner();
        String a= "";
        try {
            List<String> result = scanner.readTextFromFile("aanput.txt");}
        catch (Exception e){
            a = e.getMessage();
        }
        Assertions.assertEquals("No such file", a);
    }

}
class InputFilesLinesTest {
    @Test
    public void testInputFilesLinesCorrectFilename(){
        InputFilesLines lines = new InputFilesLines();
        List<String> result = lines.readTextFromFile("input.txt");
        Assertions.assertEquals("toredaid teste.", result.get(2));
    }
    @Test
    public void testInputFilesLinesInCorrectFilename(){
        InputFilesLines lines = new InputFilesLines();
        String a= "";
        try {

            List<String> result = lines.readTextFromFile("aanput.txt");}
        catch (Exception e){
            a = e.getMessage();
        }
        Assertions.assertEquals("No such file", a);
    }

}
