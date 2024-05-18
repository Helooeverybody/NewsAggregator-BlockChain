package api;

import dto.SearchResult;
import constant.function.InitializeJson;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import constant.function.SortFilterFunctions;
import constant.option.SortFilterOptions.Sort;
import constant.option.SortFilterOptions.Filter.*;

public class APIService implements ApiServiceInterface {
    private JSONArray jsonArray;
    private Client client;
    private List<SearchResult> searchResults;


    public APIService(){
        InitializeJson initializeJson = new InitializeJson("sortDateAndSouce.json");
        client = new Client();
        try {
            jsonArray = initializeJson.getJsonArray();
        }catch (Exception e){
            System.out.println("Search API service");
        }
    }
    @Override
    public List<SearchResult> search(String keyword) throws IOException, InterruptedException {
        List<SearchResult> searchResults = new ArrayList<>();

        List<Integer> responseList = client.search(keyword);

        for (int response: responseList){
            SearchResult searchResult = new SearchResult();
            searchResult.setDataByIndex(jsonArray, response);
            searchResults.add(searchResult);
        }
        setSearchResults(searchResults);
        return searchResults;
    }
    @Override
    public List<SearchResult> function(Sort sort, FilterType filterType, FilterYear filterYear){
        return SortFilterFunctions.function(getSearchResults(), sort, filterType, filterYear);
    }

    @Override
    public String trend(List<String> trendList) throws IOException, InterruptedException {

        System.out.println("The search API service is: " + trendList);
        return client.trend(trendList);
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }
}