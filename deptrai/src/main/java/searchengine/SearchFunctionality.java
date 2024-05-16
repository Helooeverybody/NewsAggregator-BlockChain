package searchengine;

import dto.ApiService;
import dto.SearchResult;
import gui.utilityFunction;

import java.io.IOException;
import java.util.List;

import my_enum.sort_filter.SortFilterOptions.Sort;
import my_enum.sort_filter.SortFilterOptions.Filter.*;

public class SearchFunctionality {
    private final ApiService apiService;
    private List<SearchResult> searchResults;
    private utilityFunction _utilityFunction = new utilityFunction();

    public SearchFunctionality(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<SearchResult> performSearch(String keyword) throws IOException, InterruptedException {
        System.out.println("Keyword is: " + keyword);
        // Get search results from ApiService
        searchResults = apiService.search(keyword);
        return searchResults;
    }

    public List<SearchResult> performFunction(Sort sort, FilterType filterType, FilterYear filterYear) {
        return _utilityFunction.search(searchResults, sort, filterType, filterYear);
    }

    public String performTrend(List<String> trendList) throws IOException, InterruptedException {
        System.out.println("The search functionality is " + trendList);
        return apiService.trend(trendList);
    }
}
