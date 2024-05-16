package dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SearchResult {
    private String title;
    private String content;
    private String link;
    private String source;
    private String author;
    private String date;
    private String category;
    private String summary;
    private String hashtags;
    private String type;
    private JSONArray jsonArray;

    public SearchResult(String title, String description, String link, String linkOrigin, String author, String date, String category, String summary) {
        this.title = title;
        this.content = description;
        this.link = link;
        this.source = linkOrigin;
        this.author = author;
        this.date = date;
        this.category = category;
        this.summary = summary;
    }
    public SearchResult(){

    }

    // Getters and setters for the fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public JSONArray getJsonArray() {
//        return jsonArray;
//    }
//
//    public void setJsonArray(JSONArray jsonArray) {
//        this.jsonArray = jsonArray;
//    }
    public void setDataByIndex(JSONArray jsonArray1, int index){
        Object obj = jsonArray1.get(index);
        JSONObject jsonObject = (JSONObject) obj;
        setAuthor(getStringByColumn(jsonObject, "author"));
        setContent(getStringByColumn(jsonObject, "content"));
        setDate(getStringByColumn(jsonObject, "date"));
        setLink(getStringByColumn(jsonObject, "link"));
        setTitle(getStringByColumn(jsonObject, "title"));
        setSummary(getStringByColumn(jsonObject, "summary"));
        setHashtags(getStringByColumn(jsonObject, "tags"));
        setSource(getStringByColumn(jsonObject, "source"));
        setType(getStringByColumn(jsonObject, "type"));
        setCategory(getStringByColumn(jsonObject, "category"));

    }
    public String getStringByColumn(JSONObject jsonObject, String columnName){
        String result = "";
        try{
            return (String) jsonObject.get(columnName);
        }catch (Exception e){
            return result;
        }
    }
}
