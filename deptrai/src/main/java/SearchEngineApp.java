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
//            JFrame frame = new JFrame("Search Engine");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(1200, 800);

//            HeaderPanel headerPanel = new HeaderPanel();
//            ContentPanel contentPanel = new ContentPanel();
            ApiService apiService = new SearchAPIService(); // Initialize ApiService
//            SearchFunctionality searchFunctionality = new SearchFunctionality(apiService);
            TrendFrame trendFrame = new TrendFrame();

            SearchAPIService searchService = new SearchAPIService();
//            SearchController controller = new SearchController(headerPanel, searchFunctionality, contentPanel, trendFrame);
//            SearchController controller = new SearchController(searchFrame, searchFunctionality, trendFrame);
            SearchController controller = new SearchController(searchFrame, searchService, trendFrame);


            // Increase the speed, extract a new method TODO
//            JScrollPane scrollPane = new JScrollPane();
//            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
//            verticalScrollBar.setUnitIncrement(16); // Set unit increment to 16 pixels
//            verticalScrollBar.setBlockIncrement(64); // Set block increment to 64 pixels
//            scrollPane.setViewportView(contentPanel);
//
//            frame.add(headerPanel, BorderLayout.NORTH);
//            frame.add(scrollPane, BorderLayout.CENTER);

//            frame.setVisible(true);

            searchFrame.setVisible(true);
        });
    }
}
