package controller;

import dto.SearchResult;
import constant.option.SortFilterOptions;
import api.APIService;
import gui.search.SearchFrame;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class SearchController{
    private SearchFrame searchFrame;
    private APIService apiService;

    public SearchController(SearchFrame searchFrameSearch, APIService apiService) {
        this.searchFrame = searchFrameSearch;
        this.apiService = apiService;

        searchFrame.getHeaderPanel().setSearchListener(e -> {
            String keyword = searchFrame.getHeaderPanel().getSearchKeyword();
            search(keyword);
        });
        searchFrame.getContentPanel().setSearchListener(e -> {
            String keyword = searchFrame.getContentPanel().getSearchKeyword();
            search(keyword);
        });
        searchFrame.getHeaderPanel().setSortListener(e -> {
            function();
        });
        searchFrame.getHeaderPanel().setFilterTypeListener(e -> {
            function();
        });
        searchFrame.getHeaderPanel().setFilterYearListener(e -> {
            function();
        });
    }

    public void function(){
        SortFilterOptions.Sort sort = searchFrame.getHeaderPanel().getSelectedSort();
        SortFilterOptions.Filter.FilterYear filterYear = searchFrame.getHeaderPanel().getSelectedFilterYear();
        SortFilterOptions.Filter.FilterType filterType = searchFrame.getHeaderPanel().getSelectedFilterType();
        searchFrame.getContentPanel().showLoadingAnimation();
        new Thread(() -> {
            List<SearchResult> filteredResults = apiService.function(sort, filterType, filterYear);
            SwingUtilities.invokeLater(() -> {
                searchFrame.getContentPanel().hideLoadingAnimation();
                searchFrame.getContentPanel().displaySearchResults(filteredResults);
            });
        }).start();
    }
    public void search(String keyword){
        searchFrame.getContentPanel().showLoadingAnimation();
        new Thread(() -> {
            try {
                List<SearchResult> searchResults = apiService.search(keyword);
                SwingUtilities.invokeLater(() -> {
                    searchFrame.getContentPanel().hideLoadingAnimation();
                    searchFrame.getContentPanel().displaySearchResults(searchResults);
                });
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                SwingUtilities.invokeLater(searchFrame.getContentPanel()::hideLoadingAnimation);
            }
        }).start();
    }

}