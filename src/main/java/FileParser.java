import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class FileParser {

    static char formatType = '0';
    static String splitStrings[] = {","," ; "};
    static HashMap<String,HashSet<Person>> cityHashMap = new HashMap();
    static HashMap<String,HashSet<String>> idHashMap = new HashMap();


    /*
    Reads a file line by line, sets the format, calls parse on each Data line
     and finally calls the search method
     */
    public static void searchFile(String filename,String operationType, String searchKey){
        String line = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){

            while ((line = bufferedReader.readLine())!=null){
                if(line.startsWith("F")){
                    formatType = line.charAt(1);
                    continue;
                }
                parse(line,operationType,searchKey);
            }

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
            System.exit(0);
        }
        catch (IOException ex){
            System.out.println("An error occured while reading the file");
        }
        finally{
            search(operationType,searchKey);
        }
    }


    /*
        Accepts a string, splits it based on format and stores the neccessary data
        in a hashmap based on the operationType
     */
    static void parse(String line,String operationType,String searchKey){
        try {
            String splitString;
            if (formatType == '1') {
                splitString = splitStrings[0];
            } else if(formatType == '2'){
                splitString = splitStrings[1];
            }else {
                splitString  = null;
                System.out.println("Invalid operation");
                System.exit(0);
            }

            String data[] = line.split(splitString);
            String name = data[0].substring(2);
            String city = data[1];
            String id = data[2].replace("-", "");

            if (operationType.equals("CITY")) {
                if (city.equals(searchKey)) {
                    if (cityHashMap.containsKey(searchKey)) {
                        cityHashMap.get(searchKey).add(new Person(name, id.toUpperCase()));
                    } else {
                        HashSet<Person> set = new HashSet();
                        set.add(new Person(name, id.toUpperCase()));
                        cityHashMap.put(searchKey.toUpperCase(), set);
                    }
                }
            }

            if (operationType.equals("ID")) {
                if (id.equals(searchKey)) {
                    if (idHashMap.containsKey(searchKey)) {
                        idHashMap.get(searchKey).add(city.toUpperCase());
                    } else {
                        HashSet<String> set = new HashSet();
                        set.add(city.toUpperCase());
                        idHashMap.put(searchKey.toUpperCase(), set);
                    }
                }
            }
        }catch (Exception e){
            System.out.println("File was not fully parsed as something went wrong");
        }
    }

    /*
    Calls searchById or SearchByCity based on operation type
     */
    static  void search(String operationType,String searchKey){
        if(operationType.equals("CITY")){
            searchByCity(searchKey);
        }
        else if(operationType.equals("ID")){
            searchById(searchKey);
        }
        else{
            System.out.println("Unknown operation \nEnter as 2nd argument'CITY' to search by city, 'ID' to search by ID" );
        }
    }

    /*
    Outputs a list of cities associated with the searchkey id
     */
    static void searchById(String searchKey){
        try {
            for (String city : idHashMap.get(searchKey)) {
                System.out.println(city);
            }
        }catch (NullPointerException e){
            System.out.printf("ID %s not found",searchKey);
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
    }

    /*
    Outputs a list of people associated with the searchkey city
     */
    static void searchByCity(String searchKey){
        System.out.println(searchKey);
        try{
            for(Person person:cityHashMap.get((searchKey))){
                System.out.println(person);
            }
        }catch (NullPointerException e){
            System.out.printf("City %s not found",searchKey);
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
    }
}
