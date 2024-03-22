package utils;

import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class common {

    public static String ReadPropFile(String key) throws IOException {

        FileReader fr = new FileReader("src/main/java/Files/config.properties");
        Properties prop = new Properties();
        prop.load(fr);
        String name = prop.getProperty(key);
        return name;
    }

    public static String ReadJsonFile(String path) throws IOException, ParseException {
        FileReader fr = new FileReader(path);
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        return requestBody;

    }
}
