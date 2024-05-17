import dto.ApiService;
import searchengine.SearchAPIService;
//import searchengine.SearchFunctionality;
import gui.*;
import javax.swing.*;
import java.awt.*;

public class SearchEngineApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchFrame searchFrame = new SearchFrame("Search Engine");
//            ApiService apiService = new SearchAPIService(); // Initialize ApiService
            TrendFrame trendFrame = new TrendFrame();

            SearchAPIService searchService = new SearchAPIService();
            SearchController controller = new SearchController(searchFrame, searchService);

            searchFrame.setVisible(true);
        });
    }
}
