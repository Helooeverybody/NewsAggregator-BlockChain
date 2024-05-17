import controller.SearchController;
import controller.TrendController;
import gui.search.SearchFrame;
import gui.trend.TrendFrame;
import searchengine.APIService;

import javax.swing.*;

public class SearchEngineApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            SearchFrame searchFrame = new SearchFrame("Search Engine");
            APIService apiService = new APIService();
            SearchController searchController = new SearchController(searchFrame, apiService);

            TrendFrame trendFrame = new TrendFrame();
            TrendController trendController = new TrendController(trendFrame,apiService);
            searchFrame.getHeaderPanel().addTrendButtonListener(trendController);

            searchFrame.setVisible(true);
        });
    }
}