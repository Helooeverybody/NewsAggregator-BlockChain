package api;

import dto.SearchResult;
import json.InitializeJson;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import constant.function.SortFilterFunctions;
import constant.option.SortFilterOptions.Sort;
import constant.option.SortFilterOptions.Filter.*;
public class SearchAPIService implements ApiServiceInterface<SearchResult>  {
    private JSONArray jsonArray;
    private Client client;
    private List<SearchResult> searchResults;
    public SearchAPIService(){
        InitializeJson initializeJson = new InitializeJson("sortDateAndSouce.json");
        client = new Client();
        try {
            jsonArray = initializeJson.getJsonArray();
        }catch (Exception e){
            System.out.println("Search API service");
        }
    }
    @Override
    public List<SearchResult> function(List<String> keyword) throws IOException, InterruptedException {
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
    //@Override
    public List<SearchResult> additionalFunction(Sort sort, FilterType filterType, FilterYear filterYear){
        return SortFilterFunctions.function(getSearchResults(), sort, filterType, filterYear);
    }

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }
}
