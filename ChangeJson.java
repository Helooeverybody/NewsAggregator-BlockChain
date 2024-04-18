package json;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ChangeJson {
    public void changeJsonFile(String fileInput, String outputFile){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(fileInput)) {
            // Read JSON file
            Object obj = parser.parse(reader);

            JSONArray userList = (JSONArray) obj;
            userList.forEach(user -> parseUserObject((JSONObject) user));

            // Write JSON file
            try (FileWriter file = new FileWriter(outputFile)) {
                file.write(userList.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseUserObject(JSONObject user) {
        // Get name string
        String name = (String) user.get("tags");

        // Replace '#' character
        name = name.replace("#", "");

        // Put back into name field
        user.put("tags", name);
    }
    public void removeDuplicate(String jsonFile, String outputFile){
        try{
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(jsonFile));
            System.out.println("The size before remove duplicate: " + jsonArray.size());
            Set<JSONObject> jsonObjectSet = new LinkedHashSet<>();
            Set<String> linkSet = new LinkedHashSet<>();
            for (Object obj: jsonArray){
                JSONObject temp = (JSONObject)obj;
                String link = (String)temp.get("link");
                if (!linkSet.contains(link)){
                    linkSet.add(link);
                    jsonObjectSet.add((JSONObject) obj);
                }
            }
            JSONArray uniqueJsonArray = new JSONArray();
            uniqueJsonArray.addAll(jsonObjectSet);
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(uniqueJsonArray.toJSONString());
            System.out.println("The size of Output file is: " + uniqueJsonArray.size());
            fileWriter.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
