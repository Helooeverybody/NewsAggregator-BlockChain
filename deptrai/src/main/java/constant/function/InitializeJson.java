package constant.function;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class InitializeJson {
    private final String jsonFile;
    public InitializeJson(String jsonFile){
        this.jsonFile = jsonFile;
    }
    public JSONArray getJsonArray(){
        JSONArray jsonArray = new JSONArray();
        try{
            JSONParser jsonParser = new JSONParser();
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(jsonFile));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return jsonArray;
    }
}
