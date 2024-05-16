package searchengine;

import dto.ApiService;
import dto.SearchResult;
import json.InitializeJson;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SearchAPIService implements ApiService {
    private JSONArray jsonArray;
    public SearchAPIService(){
        InitializeJson initializeJson = new InitializeJson();
        try {
            jsonArray = initializeJson.getJsonArray();
        }catch (Exception e){
            System.out.println("Search API service");
        }
    }
    @Override
    public List<SearchResult> search(String keyword) throws IOException, InterruptedException {
        // Provide the actual implementation to perform the search
        // This could involve making API calls, querying a database, etc.
//        return Collections.emptyList(); // For example, returning an empty list for now
        
        List<SearchResult> searchResults = new ArrayList<>();

        long startTime = System.currentTimeMillis(); // FIXME
        SearchClient client = new SearchClient();

//        String response = client.test();

        List<Integer> responseList = client.test1(keyword);
        //List<Integer> responseList = new ArrayList<>(Arrays.asList(1, 2, 3)); // TODO
        System.out.println("Response is: " + responseList);
        // FIXME
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution time: " + executionTime + " milliseconds");
        for (int response: responseList){
            SearchResult searchResult = new SearchResult();
            searchResult.setDataByIndex(jsonArray, response);
            searchResults.add(searchResult);
        }
        return searchResults;
    }

    @Override
    public String trend(List<String> trendList) throws IOException, InterruptedException {
        SearchClient client = new SearchClient();
        System.out.println("The search API service is: " + trendList);
        return client.trend(trendList);
    }

}