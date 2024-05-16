package json;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class InitializeJson {
    public JSONArray getJsonArray(){
        JSONArray jsonArray = new JSONArray();
        try{
            JSONParser jsonParser = new JSONParser();
            String jsonFile = "sortDateAndSouce.json";
            System.out.println("There is a file here"); // FIXME
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(jsonFile));
            System.out.println("The size of jsonArray is: " + jsonArray.size());
        } catch (IOException | ParseException e) {
            System.out.println("There is an error"); // FIXME
            throw new RuntimeException(e);
        }
        return jsonArray;
    }
}
