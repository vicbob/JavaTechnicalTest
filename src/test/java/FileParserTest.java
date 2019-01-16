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
        assertEquals(1 , fileParser.cityHashMap.get("BARCELONA").size());
        assertEquals(0, fileParser.idHashMap.size());
    }

    @Test
    public void testParseParsesFormat1WithIdOperationType() {
        fileParser.parse('1', "D Erica Burns,BARCELONA,93654902Y", "ID", "93654902Y");
        fileParser.parse('1', "D Erica Burns,LISBON,93654902Y", "ID", "93654902Y");
        assertEquals(2, fileParser.idHashMap.get("93654902Y").size());
        assertEquals(0, fileParser.cityHashMap.size());
    }

    @Test
    public void testParseParsesFormat2WithCityOperationType() {
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        assertEquals(fileParser.cityHashMap.get("BARCELONA").size(), 1);
    }

    @Test
    public void testParseParsesFormat2WithIdOperationType() {
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        assertEquals(1,fileParser.idHashMap.get("93654902Y").size());
    }

    @Test
    public void testParseWillNotParseWronglyFormattedInput() {
        fileParser.parse('1', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        assertEquals(0, fileParser.idHashMap.size());
        assertEquals(0, fileParser.cityHashMap.size());
    }

    @Test
    public void testParseParsesBothFormats(){
        fileParser.parse('2', "D Erica Burns ; OVIEDO ; 93654902-Y", "ID", "93654902Y");
        fileParser.parse('1', "D Erica Burns,BARCELONA,93654902Y", "ID", "93654902Y");
        assertEquals(2,fileParser.idHashMap.get("93654902Y").size());
    }

    @Test
    public void testParseDoesNotAcceptDuplicateUsers(){
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");        fileParser.parse('1', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        fileParser.parse('2', "D Eren Barney ; BARCELONA ; 93654902-Z", "CITY", "BARCELONA");
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        assertEquals(2,fileParser.cityHashMap.get("BARCELONA").size());
    }

    @Test
    public void testParseDoesNotAcceptDuplicateCities(){
        FileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; LISBON ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; LONDON ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        assertEquals(3,FileParser.idHashMap.get("93654902Y").size());
    }

}