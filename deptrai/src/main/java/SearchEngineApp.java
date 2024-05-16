import dto.ApiService;
import searchengine.SearchAPIService;
import searchengine.SearchFunctionality;
import gui.*;
import javax.swing.*;
import java.awt.*;

public class SearchEngineApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Search Engine");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);

            HeaderPanel headerPanel = new HeaderPanel();
            ContentPanel contentPanel = new ContentPanel();
            ApiService apiService = new SearchAPIService(); // Initialize ApiService
            SearchFunctionality searchFunctionality = new SearchFunctionality(apiService);
            TrendFrame trendFrame = new TrendFrame();

            SearchController controller = new SearchController(headerPanel, searchFunctionality, contentPanel, trendFrame);

            JScrollPane scrollPane = new JScrollPane();
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setUnitIncrement(16); // Set unit increment to 16 pixels
            verticalScrollBar.setBlockIncrement(64); // Set block increment to 64 pixels
            scrollPane.setViewportView(contentPanel);

            frame.add(headerPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);



            frame.setVisible(true);
        });
    }
}
