import api.SearchAPIService;
import api.TrendAPIService;
import controller.SearchController;
import controller.TrendController;
import gui.search.SearchFrame;
import gui.trend.TrendFrame;

import javax.swing.*;

public class SearchEngineApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchFrame searchFrame = new SearchFrame("Search Engine");
            SearchAPIService searchApiService = new SearchAPIService();
            TrendAPIService trendApiService = new TrendAPIService();
            SearchController searchController = new SearchController(searchFrame, searchApiService);
            TrendFrame trendFrame = new TrendFrame();
            TrendController trendController = new TrendController(trendFrame, trendApiService);
            searchFrame.getHeaderPanel().addTrendButtonListener(trendController);
            searchFrame.setVisible(true);
        });
    }
}