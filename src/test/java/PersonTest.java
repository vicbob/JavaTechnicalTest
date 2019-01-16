import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PersonTest {
    Person person1 = new Person("Justin Timberlake","123456789Z");
    Person person2 = new Person("Chris Brown","123456789Z");

    /*
    To check if Persons with the same ID are equal
    This makes IDs unique
     */
    @Test
    public void testOverwrittenEquals(){
        assertEquals(person1,person2);
    }


    /*
    Check if hashCode is assigned based on Id
     */
    @Test
    public void testOverwrittenHashCode(){
        assertEquals(person1.hashCode(),"123456789Z".hashCode());
    }
}
