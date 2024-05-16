package gui;

import dto.LoadingAnimationHandler;
import dto.SearchResult;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ContentPanel extends JPanel implements LoadingAnimationHandler {
    private static final int RESULTS_PER_PAGE = 10; // Number of results per page
    private List<SearchResult> searchResults;
    private int currentPage;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchBar;


    public ContentPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        add(Box.createVerticalGlue());
        // Initialize with an image centered
        initializeWithImage("image/homePage5.png", 150); //TODO DELETE THE IMAGE Specify the path and desired height of the image
        // Add the search bar below the image
        addSearchBar();
        add(Box.createVerticalGlue());
    }

    private void initializeWithImage(String imagePath, int imageHeight) {
        ImageIcon scaledIcon = getScaledIcon(imagePath, imageHeight);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(imageLabel);
    }

    private void addSearchBar() {
        // Create search bar components
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(500, 40));
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setOpaque(false);
        searchField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.PINK));

        ImageIcon searchIcon = new ImageIcon("image/search2.png"); // TODO DELETE THE IMAGE
        searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(48, 48));
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setOpaque(false);

        // Create a panel to hold the search bar components
        JPanel searchBarPanel = new JPanel();
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        // Position the search bar below the image
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 20, 0); // Add some space below the search bar

        add(searchBarPanel, gbc);
        this.searchBar = searchBarPanel;

        // Add action listener to the search button
    }
    public void setSearchListener(ActionListener listener){
        searchButton.addActionListener(listener);
        searchField.addActionListener(listener);
    }
    public String getSearchKeyword() {
        return searchField.getText();
    }

    @Override
    public void showLoadingAnimation() {
        removeAll();
        add(new JLabel("Loading..."), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    public void hideLoadingAnimation() {
        removeAll();
        revalidate();
        repaint();
    }

    public void displaySearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
        currentPage = 0; // Reset to the first page
        showCurrentPage();
    }

    private void showCurrentPage() {
        removeAll();
        int startIndex = currentPage * RESULTS_PER_PAGE;
        int endIndex = Math.min(startIndex + RESULTS_PER_PAGE, searchResults.size());
        for (int i = startIndex; i < endIndex; i++) {
            JPanel resultPanel = createSearchResultPanel(searchResults.get(i));
            add(resultPanel);
            add(Box.createVerticalStrut(10));
        }
        add(createPaginationPanel(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private JPanel createPaginationPanel() {
        JPanel paginationPanel = new JPanel(new FlowLayout());

        // Previous Button
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    showCurrentPage();
                }
            }
        });
        paginationPanel.add(previousButton);

        // Page Number Label
        JLabel pageLabel = new JLabel("Page " + (currentPage + 1) + " / " + getNumberOfPages());
        paginationPanel.add(pageLabel);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalPages = (int) Math.ceil((double) searchResults.size() / RESULTS_PER_PAGE);
                if (currentPage < totalPages - 1) {
                    currentPage++;
                    showCurrentPage();
                }
            }
        });
        paginationPanel.add(nextButton);

        // Page Jump TextField
        JTextField pageJumpField = new JTextField(5);
        JButton jumpButton = getJumpButton(pageJumpField);
        paginationPanel.add(pageJumpField);
        paginationPanel.add(jumpButton);

        return paginationPanel;
    }

    private JButton getJumpButton(JTextField pageJumpField) {
        JButton jumpButton = new JButton("Jump");
        jumpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jumpToPage(pageJumpField.getText());
            }
        });
        pageJumpField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jumpToPage(pageJumpField.getText());
            }
        });
        return jumpButton;
    }

    private int getNumberOfPages() {
        return (int) Math.ceil((double) searchResults.size() / RESULTS_PER_PAGE);
    }

    private JPanel createSearchResultPanel(SearchResult result) {
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createLineBorder(new Color(204, 180, 180, 255), 1));
        resultPanel.setPreferredSize(new Dimension(600, 150));

        JPanel upperPanel = new JPanel(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(12, 16, 10, 10));
        contentPanel.setBackground(new Color(255, 255, 255));

        // Load and scale the image icon
        ImageIcon imageIcon = getScaledIcon("image/searchResult.png", 150); // TODO DELETE THE IMAGE
        JLabel imageLabel = new JLabel(imageIcon);

        // Set the preferred size of the image label to match the icon size
        imageLabel.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        upperPanel.add(imageLabel, BorderLayout.WEST);

        JLabel typePanel = getjLinkLabel(result);
        contentPanel.add(typePanel, BorderLayout.NORTH);

        // Content
        JTextArea titleArea = new JTextArea(result.getTitle());
        titleArea.setFont(new Font("Arial", Font.BOLD, 20));
        titleArea.setEditable(false);
        titleArea.setLineWrap(true);
        titleArea.setWrapStyleWord(true);
        titleArea.setForeground(new Color(64, 7, 162));
        titleArea.setBorder(new EmptyBorder(2, 6, 5, 0));
        titleArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ResultFrame resultFrame = new ResultFrame(result);
                resultFrame.display();
            }
        });

        contentPanel.add(titleArea, BorderLayout.CENTER);

        JPanel metadataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 0));
        metadataPanel.setBackground(new Color(255, 255, 255));

        // Author
        JLabel authorLabel = new JLabel("Author: " + result.getAuthor());
        authorLabel.setFont(new Font("Arial", Font.BOLD, 15));
        authorLabel.setForeground(new Color(154, 160, 166));
        metadataPanel.add(authorLabel);

        // Date
        JLabel dateLabel = new JLabel("Date: " + result.getDate());
        dateLabel.setFont(new Font("Arial", Font.BOLD, 15));
        dateLabel.setForeground(new Color(154, 160, 166));
        metadataPanel.add(dateLabel);

        // Category
        JLabel categoryLabel = new JLabel("Category: " + result.getCategory());
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 15));
        categoryLabel.setForeground(new Color(154, 160, 166));
        metadataPanel.add(categoryLabel);

        contentPanel.add(metadataPanel, BorderLayout.SOUTH);
        upperPanel.add(contentPanel, BorderLayout.CENTER);

        resultPanel.add(upperPanel, BorderLayout.NORTH);

        resultPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!typePanel.getBounds().contains(e.getPoint())) {
                    ResultFrame resultFrame = new ResultFrame(result);
                    resultFrame.display(); // FIXME
                }
            }
        });

        return resultPanel;
    }

    private static JLabel getjLinkLabel(SearchResult result) {
        String titleWithLink = "<html><span style='font-size: 10px;'>" + result.getType() + "</span><br>"
                + "<font color='#006000' style='font-size: 10px; padding-left: 12px'><u><a  href='" + result.getLink() + "'>" + result.getLink() + "</a></u></font></html>";
        JLabel typePanel = new JLabel(titleWithLink);
        typePanel.setBorder(new EmptyBorder(2, 6, 5, 0));
        typePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(result.getLink())); // Open link in default browser
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return typePanel;
    }

    private void jumpToPage(String pageText) {
        try {
            int page = Integer.parseInt(pageText) - 1;
            if (page >= 0 && page < getNumberOfPages()) {
                currentPage = page;
                showCurrentPage();
            } else {
                JOptionPane.showMessageDialog(ContentPanel.this, "Invalid page number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ContentPanel.this, "Invalid page number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ImageIcon getScaledIcon(String path, int height){
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        int desiredHeight = height;
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);
        int scaledWidth = (int) ((double) originalWidth / originalHeight * desiredHeight);
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
}
