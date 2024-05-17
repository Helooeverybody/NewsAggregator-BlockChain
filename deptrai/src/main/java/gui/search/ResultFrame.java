package gui.search;
import dto.SearchResult;
import constant.function.UIFunction;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class ResultFrame extends JFrame{
    private SearchResult searchResult;
    private final Font titleFont = new Font("Serif", Font.BOLD, 24);
    private final Font shortFont = new Font("Arial", Font.BOLD, 18);
    private final Color LIGHT_BLUE = new Color(159, 211, 207);
    private JPanel mainPanel;
    public ResultFrame(SearchResult searchResult){
        init(searchResult);
    }
    private void init(SearchResult searchResult){
        this.searchResult = searchResult;
        setTitle("Newspaper Display");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon titleBarIcon = UIFunction.getScaledIcon("image/titleDisplay.png", 24);
        setIconImage(titleBarIcon.getImage());
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        addPanel();
        add(mainPanel);
    }

    private JPanel getTitlePanel(){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        ImageIcon titleIcon = UIFunction.getScaledIcon("image/blockchainTitle.png", 100);
        titlePanel.setBackground(Color.LIGHT_GRAY);

        JPanel titleLabelPanel = getTitleLabelPanel();

        JLabel titleIconLabel = new JLabel(titleIcon);
        JPanel titleIconPanel = new JPanel();
        titleIconPanel.add(titleIconLabel);

        titlePanel.add(titleIconPanel, BorderLayout.WEST);
        titlePanel.add(titleLabelPanel, BorderLayout.EAST);

        return titlePanel;
    }

    private JPanel getTitleLabelPanel() {
        JTextArea titleArea = new JTextArea(searchResult.getTitle(), 3, 60);
        titleArea.setFont(titleFont);
        titleArea.setWrapStyleWord(true);
        titleArea.setLineWrap(true);
        titleArea.setOpaque(false);
        titleArea.setEditable(false);
        titleArea.setFocusable(false);
        titleArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        JPanel titleLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleLabelPanel.add(titleArea);
        titleLabelPanel.setBackground(Color.LIGHT_GRAY);
        return titleLabelPanel;
    }

    private JPanel getShortPanel(){
        JPanel dateAuthorPanel = new JPanel();

        dateAuthorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 0));

        ImageIcon dateIcon = UIFunction.getScaledIcon("image/date.png", 24);
        JLabel textLabel = new JLabel(searchResult.getDate());
        textLabel.setFont(shortFont);
        JPanel datePanel = UIFunction.getLabelWithImage(dateIcon, textLabel);
        datePanel.setBackground(LIGHT_BLUE);


        JLabel authorLabel = new JLabel(searchResult.getAuthor());
        authorLabel.setFont(shortFont);
        ImageIcon authorIcon = UIFunction.getScaledIcon("image/author.png", 24);
        JPanel authorPanel = UIFunction.getLabelWithImage(authorIcon, authorLabel);
        authorPanel.setBackground(LIGHT_BLUE);

        JLabel categoryLabel = new JLabel(searchResult.getCategory());
        categoryLabel.setFont(shortFont);
        ImageIcon categoryIcon = UIFunction.getScaledIcon("image/category.png", 24);
        JPanel categoryPanel = UIFunction.getLabelWithImage(categoryIcon, categoryLabel);
        categoryPanel.setBackground(LIGHT_BLUE);

        JLabel typeLabel = new JLabel(searchResult.getType());
        typeLabel.setFont(shortFont);
        ImageIcon typeIcon = UIFunction.getScaledIcon("image/type.png", 24);
        JPanel typePanel = UIFunction.getLabelWithImage(typeIcon, typeLabel);
        typePanel.setBackground(LIGHT_BLUE);

        dateAuthorPanel.add(datePanel);
        dateAuthorPanel.add(authorPanel);
        dateAuthorPanel.add(categoryPanel);
        dateAuthorPanel.add(typePanel);
        dateAuthorPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        dateAuthorPanel.setBackground(LIGHT_BLUE);
        return dateAuthorPanel;
    }
    private JPanel getContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JLabel contentLabel = new JLabel("Content");
        contentLabel.setFont(shortFont);
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String contentText = searchResult.getContent();
        Border border = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        JTextArea contentArea = UIFunction.getTextArea(contentText, 20, 50, border);
        JScrollPane scrollPane = new JScrollPane(contentArea);

        contentPanel.add(contentLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        return contentPanel;
    }
    private JPanel getSummaryPanel(){
        JPanel summaryPanel = new JPanel();
        JLabel summaryLabel = new JLabel("Summary");
        summaryLabel.setFont(shortFont);
        summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        String summaryText = searchResult.getSummary();
        Border border = BorderFactory.createEmptyBorder(20, 0, 20, 0);
        JTextArea summaryArea = UIFunction.getTextArea(summaryText, 5, 10, border); // Adjust the size as needed
        JScrollPane summaryScrollPane = new JScrollPane(summaryArea);
//        summaryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        summaryPanel.setLayout(new BorderLayout());
        summaryPanel.add(summaryLabel, BorderLayout.NORTH);
        summaryPanel.add(summaryScrollPane, BorderLayout.CENTER);
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        return summaryPanel;
    }
    private JPanel getTagsPanel(){
        JPanel hashtagsPanel = new JPanel();
        ImageIcon tagsIcon = UIFunction.getScaledIcon("image/tags.png", 24);
        JLabel hashtagsLabel = new JLabel(tagsIcon);
        JTextField hashtagsField = new JTextField(50); // Adjust the size as needed
        hashtagsField.setText(searchResult.getHashtags());
        hashtagsPanel.add(hashtagsLabel);
        hashtagsPanel.add(hashtagsField);
        hashtagsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        hashtagsPanel.setBackground(LIGHT_BLUE);

        return hashtagsPanel;
    }

    private JPanel getContentSummaryPanel(){
        JPanel contentSummaryPanel = new JPanel();

        Dimension summaryPreferredSize = new Dimension(200, getSummaryPanel().getPreferredSize().height);
        getSummaryPanel().setPreferredSize(summaryPreferredSize);


        Dimension contentPreferredSize = new Dimension(800, getContentPanel().getPreferredSize().height);
        getContentPanel().setPreferredSize(contentPreferredSize);

        contentSummaryPanel.setLayout(new BoxLayout(contentSummaryPanel, BoxLayout.X_AXIS));
        contentSummaryPanel.add(getSummaryPanel(), BorderLayout.EAST);
        contentSummaryPanel.add(getContentPanel(), BorderLayout.WEST);

        return contentSummaryPanel;
    }
    public void addPanel(){
        mainPanel.add(getTitlePanel());
        mainPanel.add(getShortPanel());
        mainPanel.add(getContentSummaryPanel(), BorderLayout.CENTER);
        mainPanel.add(getTagsPanel(), BorderLayout.SOUTH);
    }
    public void display(){
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

}
