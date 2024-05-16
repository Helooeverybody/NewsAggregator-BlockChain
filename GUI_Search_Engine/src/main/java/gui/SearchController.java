package gui;

import dto.SearchCallback;
import dto.SearchResult;
import my_enum.sort_filter.SortFilterOptions;
import searchengine.SearchFunctionality;
import my_enum.sort_filter.SortFilterOptions.Sort;
import my_enum.sort_filter.SortFilterOptions.Filter.*;

import java.util.List;

public class SearchController {
    private final HeaderPanel headerPanel;
    private final SearchFunctionality searchFunctionality;
    private final ContentPanel contentPanel;
    private List<SearchResult>searchResults;

    public SearchController(HeaderPanel headerPanel, SearchFunctionality searchFunctionality, ContentPanel contentPanel) {
        this.headerPanel = headerPanel;
        this.searchFunctionality = searchFunctionality;
        this.contentPanel = contentPanel;

        // Create a SearchCallback implementation inline
        SearchCallback callback = new SearchCallback() {
            @Override
            public void showLoadingAnimation() {
                contentPanel.showLoadingAnimation();
            }

            @Override
            public void hideLoadingAnimation() {
                contentPanel.hideLoadingAnimation();
            }

            @Override
            public void displaySearchResults(List<SearchResult> searchResults) {
                //utilityFunction u = new utilityFunction();
                //List<SearchResult> newSearchResults = u.sortByYearAsc(searchResults);
                contentPanel.displaySearchResults(searchResults);
            }
        };


        headerPanel.setSearchListener(e -> {

            String keyword = headerPanel.getSearchKeyword();
            searchFunctionality.performSearch(keyword, callback);

        });
        headerPanel.setSortListener(e -> {
            Sort sort = headerPanel.getSelectedSort();
            FilterYear filterYear = headerPanel.getSelectedFilterYear();
            FilterType filterType = headerPanel.getSelectedFilterType();
            searchFunctionality.performFunction(searchFunctionality.getSearchResults(), sort, filterType, filterYear, callback);

        });
        headerPanel.setFilterTypeListener(e -> {
            Sort sort = headerPanel.getSelectedSort();
            FilterYear filterYear = headerPanel.getSelectedFilterYear();
            FilterType filterType = headerPanel.getSelectedFilterType();
            searchFunctionality.performFunction(searchFunctionality.getSearchResults(), sort, filterType, filterYear, callback);

        });
        headerPanel.setFilterYearListener(e -> {
            Sort sort = headerPanel.getSelectedSort();
            FilterYear filterYear = headerPanel.getSelectedFilterYear();
            FilterType filterType = headerPanel.getSelectedFilterType();
            searchFunctionality.performFunction(searchFunctionality.getSearchResults(), sort, filterType, filterYear, callback);

        });

        contentPanel.setSearchListener(e -> {
            String keyword = contentPanel.getSearchKeyword();
            searchFunctionality.performSearch(keyword, callback);
        });

    }
}
