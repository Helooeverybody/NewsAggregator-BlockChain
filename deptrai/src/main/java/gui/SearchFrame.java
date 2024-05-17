package gui;

import javax.swing.*;
import java.awt.*;

public class SearchFrame extends JFrame {
    private final HeaderPanel headerPanel;
    private final ContentPanel contentPanel;

    public SearchFrame (String title){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        this.headerPanel = new HeaderPanel();
        this.contentPanel = new ContentPanel();
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel);
        addScrollPane(contentPanel);

    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public ContentPanel getContentPanel() {
        return contentPanel;
    }
    public void addScrollPane(ContentPanel contentPanel){
            JScrollPane scrollPane = new JScrollPane();
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setUnitIncrement(16); // Set unit increment to 16 pixels
            verticalScrollBar.setBlockIncrement(64); // Set block increment to 64 pixels
            scrollPane.setViewportView(contentPanel);
            add(scrollPane, BorderLayout.CENTER);
    }
}
