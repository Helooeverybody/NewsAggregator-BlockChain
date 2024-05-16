package searchengine;

import dto.ApiService;
import dto.SearchResult;
import json.InitializeJson;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import gui.utilityFunction;
import my_enum.sort_filter.SortFilterOptions.Sort;
import my_enum.sort_filter.SortFilterOptions.Filter.*;

public class SearchAPIService implements ApiService {
    private JSONArray jsonArray;
    private SearchClient client;
    private utilityFunction _utilityFunction = new utilityFunction();
    private List<SearchResult> searchResults;


    public SearchAPIService(){
        InitializeJson initializeJson = new InitializeJson();
        client = new SearchClient();
        try {
            jsonArray = initializeJson.getJsonArray();
        }catch (Exception e){
            System.out.println("Search API service");
        }
    }
    @Override
    public List<SearchResult> search(String keyword) throws IOException, InterruptedException {
        List<SearchResult> searchResults = new ArrayList<>();

        List<Integer> responseList = client.test1(keyword);
        //List<Integer> responseList = new ArrayList<>(Arrays.asList(1, 2, 3)); // TODO

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
        return _utilityFunction.search(getSearchResults(), sort, filterType, filterYear);
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