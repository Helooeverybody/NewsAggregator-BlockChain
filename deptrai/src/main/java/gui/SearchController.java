package gui;

import dto.SearchResult;
import my_enum.sort_filter.SortFilterOptions;
import searchengine.SearchAPIService;
//import searchengine.SearchFunctionality;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class SearchController {
    // TODO replace the searchFunctionality by the searchService
//    private  HeaderPanel headerPanel;
//    private  SearchFunctionality searchFunctionality;
//    private  ContentPanel contentPanel;
    private SearchFrame searchFrame;
    private SearchAPIService searchService;

    //    public SearchController(SearchFrame searchFrame, SearchFunctionality searchFunctionality, TrendFrame trendFrame){
    public SearchController(SearchFrame searchFrameSearch, SearchAPIService searchService) {
        this.searchFrame = searchFrameSearch;
        this.searchService = searchService;

        searchFrame.getHeaderPanel().setSearchListener(e -> {
            String keyword = searchFrame.getHeaderPanel().getSearchKeyword();
            searchFrame.getContentPanel().showLoadingAnimation();
            new Thread(() -> {
                try {
                    List<SearchResult> searchResults = searchService.search(keyword);
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
                List<SearchResult> filteredResults = searchService.function(sort, filterType, filterYear);
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
                List<SearchResult> filteredResults = searchService.function(sort, filterType, filterYear);
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
                List<SearchResult> filteredResults = searchService.function(sort, filterType, filterYear);
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
                    List<SearchResult> searchResults = searchService.search(keyword);
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





//    public SearchController(HeaderPanel headerPanel, SearchFunctionality searchFunctionality, ContentPanel contentPanel, TrendFrame trendFrame) {
//        this.headerPanel = headerPanel;
//        this.searchFunctionality = searchFunctionality;
//        this.contentPanel = contentPanel;
//        this.trendFrame = trendFrame;
//
//        headerPanel.setSearchListener(e -> {
//            String keyword = headerPanel.getSearchKeyword();
//            contentPanel.showLoadingAnimation();
//            new Thread(() -> {
//                try {
//                    List<SearchResult> searchResults = searchFunctionality.performSearch(keyword);
//                    SwingUtilities.invokeLater(() -> {
//                        contentPanel.hideLoadingAnimation();
//                        contentPanel.displaySearchResults(searchResults);
//                    });
//                } catch (IOException | InterruptedException ex) {
//                    ex.printStackTrace();
//                    SwingUtilities.invokeLater(contentPanel::hideLoadingAnimation);
//                }
//            }).start();
//        });
//
//        headerPanel.setTrendListener(e -> {
//            trendFrame.setVisible(true);
//            trendFrame.getHeaderTrend().setSelectionListener(a -> {
//                boolean checkCondition = trendFrame.getHeaderTrend().checkConditions();
//                if (checkCondition) {
//                    List<String> trendList = trendFrame.getHeaderTrend().getTrendKeyword();
//                    contentPanel.showLoadingAnimation();
//                    new Thread(() -> {
//                        try {
//                            String trendResult = searchFunctionality.performTrend(trendList);
//                            SwingUtilities.invokeLater(() -> {
//                                contentPanel.hideLoadingAnimation();
//                                contentTrend = new ContentTrend();
//                                contentTrend.displayImageFromBase64(trendResult);
//                                if (trendFrame != null) {
//                                    trendFrame.setContentTrend(contentTrend);
//                                }
//                            });
//                        } catch (IOException | InterruptedException ex) {
//                            ex.printStackTrace();
//                            SwingUtilities.invokeLater(contentPanel::hideLoadingAnimation);
//                        }
//                    }).start();
//                }
//            });
//        });
//
//        headerPanel.setSortListener(e -> {
//            SortFilterOptions.Sort sort = headerPanel.getSelectedSort();
//            SortFilterOptions.Filter.FilterYear filterYear = headerPanel.getSelectedFilterYear();
//            SortFilterOptions.Filter.FilterType filterType = headerPanel.getSelectedFilterType();
//            contentPanel.showLoadingAnimation();
//            new Thread(() -> {
//                List<SearchResult> filteredResults = searchFunctionality.performFunction(sort, filterType, filterYear);
//                SwingUtilities.invokeLater(() -> {
//                    contentPanel.hideLoadingAnimation();
//                    contentPanel.displaySearchResults(filteredResults);
//                });
//            }).start();
//        });
//
//        headerPanel.setFilterTypeListener(e -> {
//            SortFilterOptions.Sort sort = headerPanel.getSelectedSort();
//            SortFilterOptions.Filter.FilterYear filterYear = headerPanel.getSelectedFilterYear();
//            SortFilterOptions.Filter.FilterType filterType = headerPanel.getSelectedFilterType();
//            contentPanel.showLoadingAnimation();
//            new Thread(() -> {
//                List<SearchResult> filteredResults = searchFunctionality.performFunction(sort, filterType, filterYear);
//                SwingUtilities.invokeLater(() -> {
//                    contentPanel.hideLoadingAnimation();
//                    contentPanel.displaySearchResults(filteredResults);
//                });
//            }).start();
//        });
//
//        headerPanel.setFilterYearListener(e -> {
//            SortFilterOptions.Sort sort = headerPanel.getSelectedSort();
//            SortFilterOptions.Filter.FilterYear filterYear = headerPanel.getSelectedFilterYear();
//            SortFilterOptions.Filter.FilterType filterType = headerPanel.getSelectedFilterType();
//            contentPanel.showLoadingAnimation();
//            new Thread(() -> {
//                List<SearchResult> filteredResults = searchFunctionality.performFunction(sort, filterType, filterYear);
//                SwingUtilities.invokeLater(() -> {
//                    contentPanel.hideLoadingAnimation();
//                    contentPanel.displaySearchResults(filteredResults);
//                });
//            }).start();
//        });
//
//        contentPanel.setSearchListener(e -> {
//            String keyword = contentPanel.getSearchKeyword();
//            contentPanel.showLoadingAnimation();
//            new Thread(() -> {
//                try {
//                    List<SearchResult> searchResults = searchFunctionality.performSearch(keyword);
//                    SwingUtilities.invokeLater(() -> {
//                        contentPanel.hideLoadingAnimation();
//                        contentPanel.displaySearchResults(searchResults);
//                    });
//                } catch (IOException | InterruptedException ex) {
//                    ex.printStackTrace();
//                    SwingUtilities.invokeLater(contentPanel::hideLoadingAnimation);
//                }
//            }).start();
//        });
//    }

}
