package gui.search.panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
//import my_enum.sort_filter.Filter;
//import my_enum.sort_filter.Sort;
import constant.option.SortFilterOptions.Filter.FilterType;
import constant.option.SortFilterOptions.Filter.FilterYear;
import constant.option.SortFilterOptions.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import dto.TrendButtonListener;
import constant.function.UIFunction;

@SuppressWarnings("serial")
public class HeaderSearchPanel extends JPanel {
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
    private List<TrendButtonListener> trendButtonListeners = new ArrayList<>();


    public HeaderSearchPanel() {
        setSize(50, 10);
        setBackground(new Color(255, 255, 255, 255));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        JPanel headPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        headPanel.setBackground(new Color(238, 155, 171));
        headPanel.setLayout(new BorderLayout());
        headPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Load the left image
        ImageIcon leftImageIcon = UIFunction.getScaledIcon("image/hust.png", 80); // Ensure this path is correct
        JLabel leftImageLabel = new JLabel(leftImageIcon);

        // Create the search field
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        searchField.setOpaque(false);
        searchField.setForeground(Color.GREEN);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        ImageIcon searchIcon = new ImageIcon("image/searchTitle.png");

        searchButton = new JButton(searchIcon);
        searchButton.setPreferredSize(new Dimension(48, 48));
        searchButton.setContentAreaFilled(false);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setOpaque(false);
        searchButton.setMargin(new Insets(0, 0, 0, 0));


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


        settings.add(sort);
        settings.add(filter);

        ImageIcon menuIcon = UIFunction.getScaledIcon("image/icon.png", 30);
        JButton btnOpenMenu = getOpenButtonMenu(menuIcon);


        trendDetection = new JButton();
        ImageIcon trendIcon = UIFunction.getScaledIcon("image/trend.png", 50);
        trendDetection.setIcon(trendIcon);
        trendDetection.setBounds(122, 71, 109, 23);
        trendDetection.setAlignmentX(RIGHT_ALIGNMENT);
        trendDetection.setPreferredSize(new Dimension(48, 48));
        trendDetection.setContentAreaFilled(false);
        trendDetection.setBorderPainted(false);
        trendDetection.setOpaque(false);
        trendDetection.addActionListener(e -> notifyTrendButtonListeners());


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

    private JButton getOpenButtonMenu(ImageIcon menuIcon) {
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
        return btnOpenMenu;
    }

    public void addTrendButtonListener(TrendButtonListener listener) {
        trendButtonListeners.add(listener);
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

        List<FilterYear> filterYears = new ArrayList<>(Arrays.asList(FilterYear.YEAR_2018, FilterYear.YEAR_2019,
                FilterYear.YEAR_2020, FilterYear.YEAR_2021, FilterYear.YEAR_2022, FilterYear.YEAR_2023, FilterYear.YEAR_2024));
        for (int i = 0; i < year.size(); i++){
            JMenuItem yearItem = year.get(i);
            yearItem.addActionListener(listener);
            yearItem.addActionListener(new FilterYearActionListener(this, filterYears.get(i)));
        }
    }
    public void setFilterTypeListener(ActionListener listener){

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

    private void notifyTrendButtonListeners() {
        for (TrendButtonListener listener : trendButtonListeners) {
            listener.onTrendButtonClick();
        }
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
        private final HeaderSearchPanel parent;
        private final Sort sort;

        public SortActionListener(HeaderSearchPanel parent, Sort sort) {
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

    // ActionListener class for filter Type menu items
    private class FilterTypeActionListener implements ActionListener {
        private final HeaderSearchPanel parent;
        private final FilterType selectedFilterType;

        public FilterTypeActionListener(HeaderSearchPanel parent, FilterType selectedFilterType) {
            this.parent = parent;
            this.selectedFilterType = selectedFilterType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.selectedFilterYear = null;
            parent.selectedFilterType = selectedFilterType;
            parent.selectedSort = null; // Reset sort
            System.out.println("Selected Sort: " + null);
        }

    }
    // ActionListener class for filter Year menu items
    private class FilterYearActionListener implements ActionListener {
        private final HeaderSearchPanel parent;
        private final FilterYear selectedFilterYear;

        public FilterYearActionListener(HeaderSearchPanel parent, FilterYear selectedFilterYear) {
            this.parent = parent;
            this.selectedFilterYear = selectedFilterYear;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.selectedFilterYear = selectedFilterYear;
            parent.selectedFilterType = null;
            parent.selectedSort = null; // Reset sort

        }

    }
}
