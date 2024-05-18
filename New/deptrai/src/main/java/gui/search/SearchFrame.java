package gui.search;

import constant.function.UIFunction;
import gui.search.panel.ContentSearchPanel;
import gui.search.panel.HeaderSearchPanel;

import javax.swing.*;
import java.awt.*;

public class SearchFrame extends JFrame {
    private final HeaderSearchPanel headerSearchPanel;
    private final ContentSearchPanel contentSearchPanel;

    public SearchFrame (String title){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        ImageIcon titleBarIcon = UIFunction.getScaledIcon("image/titleDisplay.png", 24);
        setIconImage(titleBarIcon.getImage());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        headerSearchPanel = new HeaderSearchPanel();
        contentSearchPanel = new ContentSearchPanel();
        add(headerSearchPanel, BorderLayout.NORTH);
        add(contentSearchPanel);
        addScrollPane(contentSearchPanel);

    }

    public HeaderSearchPanel getHeaderPanel() {
        return headerSearchPanel;
    }

    public ContentSearchPanel getContentPanel() {
        return contentSearchPanel;
    }
    public void addScrollPane(ContentSearchPanel contentSearchPanel){
        JScrollPane scrollPane = new JScrollPane();
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16); // Set unit increment to 16 pixels
        verticalScrollBar.setBlockIncrement(64); // Set block increment to 64 pixels
        scrollPane.setViewportView(contentSearchPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
}