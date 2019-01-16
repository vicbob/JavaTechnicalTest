import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;


public class FileParserTest {
    FileParser fileParser;
    Person person1 = new Person("Erica Burns", "93654902Y");
    Person person2 = new Person("Eren Barney", "93654902Z");

    @Before
    public void setup() {
        fileParser = mock(FileParser.class);
    }

    @After
    public void validate() {
        FileParser.idHashMap.clear();
        FileParser.cityHashMap.clear();
    }

    /*
        TO test whether the Parse function parses format 1 correctly with the city operation
        */
    @Test
    public void testParseParsesFormat1WithCityOperationType() {
        fileParser.parse('1', "D Erica Burns,BARCELONA,93654902Y", "CITY", "BARCELONA");

        //Check that the data is added to the correct data structure
        assertEquals(1, fileParser.cityHashMap.get("BARCELONA").size());
        assertEquals(0, fileParser.idHashMap.size());

        //Check the data in the structure is correct and well formatted
        assertTrue(fileParser.cityHashMap.containsKey("BARCELONA"));
        assertTrue(fileParser.cityHashMap.get("BARCELONA").contains(person1));
    }

    /*
         To test whether the Parse function parses format 1 correctly with the ID operation
         */
    @Test
    public void testParseParsesFormat1WithIdOperationType() {
        fileParser.parse('1', "D Erica Burns,BARCELONA,93654902Y", "ID", "93654902Y");
        fileParser.parse('1', "D Erica Burns,LISBON,93654902Y", "ID", "93654902Y");

        //Check that the data is added to the correct data structure
        assertEquals(2, fileParser.idHashMap.get("93654902Y").size());
        assertEquals(0, fileParser.cityHashMap.size());

        //Check the data in the structure is correct and well formatted
        assertTrue(fileParser.idHashMap.containsKey("93654902Y"));
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("BARCELONA"));
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("LISBON"));
    }

    /*
         TO test whether the Parse function parses format 2 correctly with the City operation
         */
    @Test
    public void testParseParsesFormat2WithCityOperationType() {
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");

        //Check that the data is added to the correct data structure
        assertEquals(fileParser.cityHashMap.get("BARCELONA").size(), 1);

        //Check the data in the structure is correct and well formatted
        assertTrue(fileParser.cityHashMap.containsKey("BARCELONA"));
        assertTrue(fileParser.cityHashMap.get("BARCELONA").contains(person1));
    }

    /*
         To test whether the Parse function parses format 2 correctly with the ID operation
         */
    @Test
    public void testParseParsesFormat2WithIdOperationType() {
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");

        //Check that the data is added to the correct data structure
        assertEquals(1, fileParser.idHashMap.get("93654902Y").size());

        //Check the data in the structure is correct and well formatted
        assertTrue(fileParser.idHashMap.containsKey("93654902Y"));
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("BARCELONA"));
    }

    /*
        To check that the Parse function does not parse wrongly formatted line
        */
    @Test
    public void testParseWillNotParseWronglyFormattedInput() {
        fileParser.parse('1', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");

        assertEquals(0, fileParser.idHashMap.size());
        assertEquals(0, fileParser.cityHashMap.size());
    }

    /*
        To check that the parse function parses both formats into the same data structure
        */
    @Test
    public void testParseParsesBothFormats() {
        fileParser.parse('2', "D Erica Burns ; OVIEDO ; 93654902-Y", "ID", "93654902Y");
        fileParser.parse('1', "D Erica Burns,BARCELONA,93654902Y", "ID", "93654902Y");
        assertEquals(2, fileParser.idHashMap.get("93654902Y").size());

        //Check the content is correct
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("OVIEDO"));
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("BARCELONA"));
    }

    /*
    To check that result has no duplicate users
    */
    @Test
    public void testParseDoesNotAcceptDuplicateUsers() {
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");
        fileParser.parse('2', "D Eren Barney ; BARCELONA ; 93654902-Z", "CITY", "BARCELONA");
        fileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "CITY", "BARCELONA");

        assertEquals(2, fileParser.cityHashMap.get("BARCELONA").size());

        //Check the content is correct

        assertTrue(fileParser.cityHashMap.get("BARCELONA").contains(person1));
        assertTrue(fileParser.cityHashMap.get("BARCELONA").contains(person2));
    }

    /*
    To check that result has no duplicate cities
    */
    @Test
    public void testParseDoesNotAcceptDuplicateCities() {
        FileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; LISBON ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; LONDON ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Erica Burns ; BARCELONA ; 93654902-Y", "ID", "93654902Y");
        FileParser.parse('2', "D Dan Brown ; NEWYORK ; 23254902-A", "ID", "93654902Y");

        assertEquals(3, FileParser.idHashMap.get("93654902Y").size());

        //Check the content is correct
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("BARCELONA"));
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("LISBON"));
        assertTrue(fileParser.idHashMap.get("93654902Y").contains("LONDON"));
        assertFalse(fileParser.idHashMap.get("93654902Y").contains("NEWYORK"));
    }

}