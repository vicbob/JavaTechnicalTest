import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;


public class FileParserTest {
    FileParser fileParser;

    public FileParserTest() {
    }

    @Before
    public void setup() {
        fileParser = mock(FileParser.class);
    }

    @After
    public void validate() {
        FileParser.idHashMap.clear();
        FileParser.cityHashMap.clear();
    }


    @Test
    public void testParseParsesFormat1WithCityOperationType() {
        fileParser.parse('1', "D Erica Burns,BARCELONA,93654902Y", "CITY", "BARCELONA");
        assertEquals(fileParser.cityHashMap.size(), 1);
    }

    @Test
    public void testParseParsesFormat1WithIdOperationType() {
        fileParser.parse('1', "D Erica Burns,BARCELONA,93654902Y", "ID", "93654902Y");
        assertEquals(fileParser.idHashMap.size(), 1);
    }

    @Test
    public void testParseParsesFormat2WithCityOperationType() {
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        assertEquals(fileParser.cityHashMap.size(), 1);
    }

    @Test
    public void testParseParsesFormat2WithIdOperationType() {
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        assertEquals(1,fileParser.idHashMap.size());
    }

    @Test
    public void testParseWillNotParseWronglyFormattedInput() {
        fileParser.parse('1', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        assertEquals(0, fileParser.idHashMap.size());
    }

}