package controller;

import dto.SearchResult;
import constant.option.SortFilterOptions;
import searchengine.APIService;
import gui.search.SearchFrame;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class SearchController extends BaseController{
    private SearchFrame searchFrame;
    private APIService apiService;


    public SearchController(SearchFrame searchFrameSearch, APIService apiService) {
        this.searchFrame = searchFrameSearch;
        this.apiService = apiService;

        searchFrame.getHeaderPanel().setSearchListener(e -> {
            String keyword = searchFrame.getHeaderPanel().getSearchKeyword();
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
        });
        searchFrame.getHeaderPanel().setSortListener(e -> {
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
        });
        searchFrame.getHeaderPanel().setFilterTypeListener(e -> {
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
        });
        searchFrame.getHeaderPanel().setFilterYearListener(e -> {
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
        });
        searchFrame.getContentPanel().setSearchListener(e -> {
            String keyword = searchFrame.getContentPanel().getSearchKeyword();
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
        });

    }

}