import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class application {
    private static application ourInstance = new application();
    private Object put;

    public static application getInstance() {
        return ourInstance;
    }


    public static void main(String[] args){
        String filename = args[0];
        String operationType = args[1];
        String searchKey = args[2];
        FileParser.searchFile(filename,operationType.toUpperCase(),searchKey.toUpperCase());
    }
}
