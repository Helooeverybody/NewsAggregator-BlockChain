package searchengine;

import dto.ApiService;
import dto.SearchCallback;
import dto.SearchResult;
import gui.utilityFunction;

import java.io.IOException;
import java.util.List;


import my_enum.sort_filter.SortFilterOptions.Sort;
import my_enum.sort_filter.SortFilterOptions.Filter.*;


public class SearchFunctionality {
    private final ApiService apiService;
    private List<SearchResult> searchResults;
    public void setSearchResults(List<SearchResult>searchResults){
        this.searchResults = searchResults;
    }
    public List<SearchResult> getSearchResults(){
        return this.searchResults;
    }
    private utilityFunction _utilityFunction = new utilityFunction();

    public SearchFunctionality(ApiService apiService) {
        this.apiService = apiService;
    }

    public void performSearch(String keyword, SearchCallback callback) {
        new Thread(() -> {
            System.out.println("Keyword is: " + keyword);
            // Show loading animation
            callback.showLoadingAnimation();

            // Get search results from ApiService
            List<SearchResult> searchResults = null;
			try {
				searchResults = apiService.search(keyword);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

            // Hide loading animation and display search results
            callback.hideLoadingAnimation();
            callback.displaySearchResults(searchResults);
            setSearchResults(searchResults);
        }).start();
    }


    // TODO create the function with the different parameter
    public void performSearch(String keyword,  Sort sort, FilterType filterType, FilterYear filterYear, SearchCallback callback){
        List<SearchResult> RESULTS;
        new Thread(() -> {
            System.out.println("Keyword is: " + keyword);
            // Show loading animation
            callback.showLoadingAnimation();

            // Get search results from ApiService
            List<SearchResult> searchResults = null;
            try {
                searchResults = apiService.search(keyword);
                final List<SearchResult> SEARCH_RESULT = searchResults;
                searchResults = _utilityFunction.search(SEARCH_RESULT, sort, filterType, filterYear);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Hide loading animation and display search results
            callback.hideLoadingAnimation();
            callback.displaySearchResults(searchResults);
        }).start();

    }
    public void performFunction(List<SearchResult>searchResults,  Sort sort, FilterType filterType, FilterYear filterYear, SearchCallback callback){
        new Thread(() -> {
//            System.out.println("Keyword is: " + keyword);
//            // Show loading animation
//            callback.showLoadingAnimation();
//
//            // Get search results from ApiService
//            List<SearchResult> searchResults = null;
            //                searchResults = apiService.search(keyword);

             List<SearchResult> RESULT =  _utilityFunction.search(searchResults, sort, filterType, filterYear);

            // Hide loading animation and display search results
            callback.hideLoadingAnimation();
            callback.displaySearchResults(RESULT);
        }).start();
    }
}
