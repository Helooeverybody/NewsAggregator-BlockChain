package gui;

import dto.SearchResult;
import my_enum.sort_filter.SortFilterOptions;
import searchengine.SearchFunctionality;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class SearchController {
    private final HeaderPanel headerPanel;
    private final SearchFunctionality searchFunctionality;
    private final ContentPanel contentPanel;
    private ContentTrend contentTrend;
    private TrendFrame trendFrame;

    public SearchController(HeaderPanel headerPanel, SearchFunctionality searchFunctionality, ContentPanel contentPanel, TrendFrame trendFrame) {
        this.headerPanel = headerPanel;
        this.searchFunctionality = searchFunctionality;
        this.contentPanel = contentPanel;
        this.trendFrame = trendFrame;

        headerPanel.setSearchListener(e -> {
            String keyword = headerPanel.getSearchKeyword();
            contentPanel.showLoadingAnimation();
            new Thread(() -> {
                try {
                    List<SearchResult> searchResults = searchFunctionality.performSearch(keyword);
                    SwingUtilities.invokeLater(() -> {
                        contentPanel.hideLoadingAnimation();
                        contentPanel.displaySearchResults(searchResults);
                    });
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(contentPanel::hideLoadingAnimation);
                }
            }).start();
        });

        headerPanel.setTrendListener(e -> {
            trendFrame.setVisible(true);
            trendFrame.getHeaderTrend().setSelectionListener(a -> {
                boolean checkCondition = trendFrame.getHeaderTrend().checkConditions();
                if (checkCondition) {
                    List<String> trendList = trendFrame.getHeaderTrend().getTrendKeyword();
                    contentPanel.showLoadingAnimation();
                    new Thread(() -> {
                        try {
                            String trendResult = searchFunctionality.performTrend(trendList);
                            SwingUtilities.invokeLater(() -> {
                                contentPanel.hideLoadingAnimation();
                                contentTrend = new ContentTrend();
                                contentTrend.displayImageFromBase64(trendResult);
                                if (trendFrame != null) {
                                    trendFrame.setContentTrend(contentTrend);
                                }
                            });
                        } catch (IOException | InterruptedException ex) {
                            ex.printStackTrace();
                            SwingUtilities.invokeLater(contentPanel::hideLoadingAnimation);
                        }
                    }).start();
                }
            });
        });

        headerPanel.setSortListener(e -> {
            SortFilterOptions.Sort sort = headerPanel.getSelectedSort();
            SortFilterOptions.Filter.FilterYear filterYear = headerPanel.getSelectedFilterYear();
            SortFilterOptions.Filter.FilterType filterType = headerPanel.getSelectedFilterType();
            contentPanel.showLoadingAnimation();
            new Thread(() -> {
                List<SearchResult> filteredResults = searchFunctionality.performFunction(sort, filterType, filterYear);
                SwingUtilities.invokeLater(() -> {
                    contentPanel.hideLoadingAnimation();
                    contentPanel.displaySearchResults(filteredResults);
                });
            }).start();
        });

        headerPanel.setFilterTypeListener(e -> {
            SortFilterOptions.Sort sort = headerPanel.getSelectedSort();
            SortFilterOptions.Filter.FilterYear filterYear = headerPanel.getSelectedFilterYear();
            SortFilterOptions.Filter.FilterType filterType = headerPanel.getSelectedFilterType();
            contentPanel.showLoadingAnimation();
            new Thread(() -> {
                List<SearchResult> filteredResults = searchFunctionality.performFunction(sort, filterType, filterYear);
                SwingUtilities.invokeLater(() -> {
                    contentPanel.hideLoadingAnimation();
                    contentPanel.displaySearchResults(filteredResults);
                });
            }).start();
        });

        headerPanel.setFilterYearListener(e -> {
            SortFilterOptions.Sort sort = headerPanel.getSelectedSort();
            SortFilterOptions.Filter.FilterYear filterYear = headerPanel.getSelectedFilterYear();
            SortFilterOptions.Filter.FilterType filterType = headerPanel.getSelectedFilterType();
            contentPanel.showLoadingAnimation();
            new Thread(() -> {
                List<SearchResult> filteredResults = searchFunctionality.performFunction(sort, filterType, filterYear);
                SwingUtilities.invokeLater(() -> {
                    contentPanel.hideLoadingAnimation();
                    contentPanel.displaySearchResults(filteredResults);
                });
            }).start();
        });

        contentPanel.setSearchListener(e -> {
            String keyword = contentPanel.getSearchKeyword();
            contentPanel.showLoadingAnimation();
            new Thread(() -> {
                try {
                    List<SearchResult> searchResults = searchFunctionality.performSearch(keyword);
                    SwingUtilities.invokeLater(() -> {
                        contentPanel.hideLoadingAnimation();
                        contentPanel.displaySearchResults(searchResults);
                    });
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(contentPanel::hideLoadingAnimation);
                }
            }).start();
        });
    }
}
