package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
//import my_enum.sort_filter.Filter;
//import my_enum.sort_filter.Sort;
import my_enum.sort_filter.SortFilterOptions.Filter.FilterType;
import my_enum.sort_filter.SortFilterOptions.Filter.FilterYear;
import my_enum.sort_filter.SortFilterOptions.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@SuppressWarnings("serial")
public class HeaderPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JPopupMenu settings;
    private JButton trendDetection;
    private JMenuItem sortNewest;
    private JMenuItem sortOldest;
    private JMenuItem sortRelevance;

    private JMenuItem typeBlog;
    private JMenuItem typeNews;
    private Sort selectedSort = null;
    private FilterType selectedFilterType = null;
    private FilterYear selectedFilterYear = null;
    private List<JMenuItem> year;

//    private Filter selectedFilter = null;

    public HeaderPanel() {
        setSize(50, 10);
        setBackground(new Color(255, 255, 255, 255));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        JPanel headPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        headPanel.setBackground(new Color(238, 155, 171));
        headPanel.setLayout(new BorderLayout());
        headPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Load the left image
        ImageIcon leftImageIcon = getScaledIcon("image/hust.png", 80); // Ensure this path is correct
        JLabel leftImageLabel = new JLabel(leftImageIcon);

        // Create the search field
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        searchField.setOpaque(false);
        searchField.setForeground(new Color(54, 140, 27)); // FIXME
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        ImageIcon searchIcon = new ImageIcon("image/search.png");

        searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(48, 48));
        searchButton.setContentAreaFilled(false);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setOpaque(false);
        searchButton.setMargin(new Insets(0, 0, 0, 0));

        // TODO print the final button
        settings = new JPopupMenu();

        JMenu sort = createCustomMenu("Sort");
        JMenu filter = createCustomMenu("Filter");
        JMenu filterYear = createCustomMenu("Year");
        JMenu selectedFilterType = createCustomMenu("Type");

        sortRelevance = createCustomMenuItem("By Relevance");
        sortNewest = createCustomMenuItem("By Date Newest");
        sortOldest = createCustomMenuItem("By Date Oldest");

        year = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            int yearItem = i + 2018;
            JMenuItem menuItem = createCustomMenuItem(String.valueOf(yearItem));
            year.add(menuItem);
            filterYear.add(menuItem);
        }



        typeBlog = createCustomMenuItem("Blog");
        typeNews = createCustomMenuItem("News");
        selectedFilterType.add(typeBlog);
        selectedFilterType.add(typeNews);



        sort.add(sortNewest);
        sort.add(sortOldest);
        sort.add(sortRelevance);

        filter.add(filterYear);
        filter.add(selectedFilterType);

//        filter.add(noFilter);
//        filter.add(filterByDate);
//        filter.add(filterByType);
//        filter.add(filterBySource);

        settings.add(sort);
        settings.add(filter);

        ImageIcon menuIcon = getScaledIcon("image/icon.png", 30);
        JButton btnOpenMenu = new JButton();
        btnOpenMenu.setBackground(new Color(140, 17, 126, 11));
        btnOpenMenu.setBounds(122, 71, 109, 23);
        btnOpenMenu.setAlignmentX(RIGHT_ALIGNMENT);
        btnOpenMenu.setIcon(menuIcon);
        btnOpenMenu.setPreferredSize(new Dimension(48, 48));
        btnOpenMenu.setContentAreaFilled(false);
        btnOpenMenu.setBorderPainted(false);

        btnOpenMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settings.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        // TODO END
        trendDetection = new JButton();
        ImageIcon trendIcon = getScaledIcon("image/trend.png", 50);
        trendDetection.setIcon(trendIcon);
        trendDetection.setBounds(122, 71, 109, 23);
        trendDetection.setAlignmentX(RIGHT_ALIGNMENT);
        trendDetection.setPreferredSize(new Dimension(48, 48));
        trendDetection.setContentAreaFilled(false);
        trendDetection.setBorderPainted(false);
        trendDetection.setOpaque(false);
        trendDetection.addActionListener(e -> {
            //TrendFrame frameTrend = new TrendFrame();
            //frameTrend.setVisible(true);
        });

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        searchPanel.setOpaque(false);
        searchPanel.add(trendDetection);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        headPanel.add(leftImageLabel, BorderLayout.WEST);
        headPanel.add(searchPanel, BorderLayout.CENTER);
        headPanel.add(btnOpenMenu, BorderLayout.EAST);
        add(headPanel);
    }

    public Sort getSelectedSort() {
        System.out.println("get selected sort called from other class: " + selectedSort);
        return selectedSort;
    }
    public FilterYear getSelectedFilterYear(){
        return selectedFilterYear;
    }
    public FilterType getSelectedFilterType(){
        return selectedFilterType;
    }

//    public Filter getSelectedFilter() {
//        return selectedFilter;
//    }

    public void setSortFilterListener(ActionListener listener) {
        sortNewest.addActionListener(listener);
        sortOldest.addActionListener(listener);
        sortRelevance.addActionListener(listener);

        // Add action listeners to menu items
        sortRelevance.addActionListener(new SortActionListener(this, Sort.BY_RELEVANCE));
        sortNewest.addActionListener(new SortActionListener(this, Sort.BY_NEWEST));
        sortOldest.addActionListener(new SortActionListener(this, Sort.BY_OLDEST));

    }
    public void setTrendListener(ActionListener listener){
        trendDetection.addActionListener(listener);
    }
    public void setSortListener(ActionListener listener){
        sortNewest.addActionListener(listener);
        sortOldest.addActionListener(listener);
        sortRelevance.addActionListener(listener);

        // Add action listeners to menu items
        sortRelevance.addActionListener(new SortActionListener(this, Sort.BY_RELEVANCE));
        sortNewest.addActionListener(new SortActionListener(this, Sort.BY_NEWEST));
        sortOldest.addActionListener(new SortActionListener(this, Sort.BY_OLDEST));
    }
    public void setFilterYearListener(ActionListener listener){
        // TODO complete the function
        List<FilterYear> filterYears = new ArrayList<>(Arrays.asList(FilterYear.YEAR_2018, FilterYear.YEAR_2019,
                FilterYear.YEAR_2020, FilterYear.YEAR_2021, FilterYear.YEAR_2022, FilterYear.YEAR_2023, FilterYear.YEAR_2024));
        for (int i = 0; i < year.size(); i++){
            JMenuItem yearItem = year.get(i);
            yearItem.addActionListener(listener);
            yearItem.addActionListener(new FilterYearActionListener(this, filterYears.get(i)));
        }
    }
    public void setFilterTypeListener(ActionListener listener){
        // TODO complete the function
        typeNews.addActionListener(listener);
        typeBlog.addActionListener(listener);

        typeNews.addActionListener(new FilterTypeActionListener(this, FilterType.NEWS));
        typeBlog.addActionListener(new FilterTypeActionListener(this, FilterType.BLOG));

    }

    public void setSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
        searchField.addActionListener(listener);
    }

    public String getSearchKeyword() {
        return searchField.getText();
    }

    private ImageIcon getScaledIcon(String path, int height) {
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

    private JMenu createCustomMenu(String text) {
        JMenu menu = new JMenu(text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(true);
                setBackground(new Color(129, 71, 126));
                setForeground(Color.WHITE);
            }
        };
        return menu;
    }

    private JMenuItem createCustomMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(129, 71, 126));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        menuItem.setForeground(Color.WHITE); // Text color
        menuItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around text
        return menuItem;
    }

    // ActionListener class for sort menu items
    private class SortActionListener implements ActionListener {
        private final HeaderPanel parent;
        private final Sort sort;

        public SortActionListener(HeaderPanel parent, Sort sort) {
            this.parent = parent;
            this.sort = sort;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.selectedSort = sort;
            parent.selectedFilterType = null; // Reset filter
            parent.selectedFilterYear = null;

        }
    }

    // ActionListener class for filter menu items
    private class FilterTypeActionListener implements ActionListener {
        private final HeaderPanel parent;
        private final FilterType selectedFilterType;

        public FilterTypeActionListener(HeaderPanel parent, FilterType selectedFilterType) {
            this.parent = parent;
            this.selectedFilterType = selectedFilterType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.selectedFilterYear = null;
            parent.selectedFilterType = selectedFilterType;
            parent.selectedSort = null; // Reset sort
        }

    }
    private class FilterYearActionListener implements ActionListener {
        private final HeaderPanel parent;
        private final FilterYear selectedFilterYear;

        public FilterYearActionListener(HeaderPanel parent, FilterYear selectedFilterYear) {
            this.parent = parent;
            this.selectedFilterYear = selectedFilterYear;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("The selected year from header panel is: " + selectedFilterYear);
            parent.selectedFilterYear = selectedFilterYear;
            parent.selectedFilterType = null;
            parent.selectedSort = null; // Reset sort
        }

    }
}
