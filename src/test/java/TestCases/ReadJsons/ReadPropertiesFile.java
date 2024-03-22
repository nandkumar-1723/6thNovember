package TestCases.ReadJsons;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class ReadPropertiesFile {
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("src/main/java/Files/config.properties");
        Properties prop = new Properties();
        prop.load(fr);

        String name = prop.getProperty("url");
        System.out.println(name);


    }
}
