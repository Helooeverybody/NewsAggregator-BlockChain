package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class HeaderTrend extends JPanel {
    private JComboBox<String> stepComboBox;
    private JComboBox<String> purposeComboBox;
    private JButton getSelectionButton;
    private JPanel buttonPanel;
    public HeaderTrend() {
        setSize(50,10);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());


        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(21,192,192));
        ImageIcon originalIcon = new ImageIcon("image/symbol_trend.png"); // Replace with the path to your image
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        titlePanel.add(imageLabel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Trend Analysis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 38));
        titleLabel.setForeground(new Color(255, 255, 255));
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(titleLabel);
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);


        stepComboBox = createComboBox(new String[]{"Month", "Year"}, "Step");
        purposeComboBox = createComboBox(new String[]{"Plot Trend", "Hot Topic"}, "Purpose");

        getSelectionButton = new JButton("Get Result");
        getSelectionButton.setBackground(new Color(21, 192, 192, 255));
        getSelectionButton.setForeground(Color.WHITE);
        getSelectionButton.setSize(100,25);
        getSelectionButton.setFont(new Font("Arial", Font.BOLD, 12));
       //getSelectionButton.addActionListener(e ->{
         //   checkConditions();

        //});

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(stepComboBox);
        buttonPanel.add(purposeComboBox);
        buttonPanel.add(getSelectionButton);
        add(buttonPanel, BorderLayout.CENTER);

        stepComboBox.addActionListener(e -> {showStepMenu(buttonPanel);});
        purposeComboBox.addActionListener(e -> {showPurposeMenu();});
    }
    public void setSelectionListener(ActionListener listener){
        this.getSelectionButton.addActionListener(listener);
    }
    private JComboBox<String> createComboBox(String[] options, String defaultOption) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(100, 25));
        comboBox.setFont(new Font("Arial", Font.BOLD, 12));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(new Color(21, 192, 192));
        comboBox.setFocusable(false);
        comboBox.setSelectedItem(defaultOption); // Set default selected item
        return comboBox;
    }

    private void showStepMenu(JPanel buttonPanel) {
        Component[] components = buttonPanel.getComponents();
        for (Component component : components) {
            if (component instanceof MonthYearMenu || component instanceof YearMenu) {
                buttonPanel.remove(component);
            }
        }
        if (stepComboBox.getSelectedItem().equals("Month")) {
            MonthYearMenu monthYearMenu = new MonthYearMenu();
            buttonPanel.add(monthYearMenu);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        } else if (stepComboBox.getSelectedItem().equals("Year")) {
            YearMenu yearMenu = new YearMenu();
            buttonPanel.add(yearMenu);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }
    }
    public void showPurposeMenu() {

    }
    public boolean checkConditions() {
        Component[] components = this.buttonPanel.getComponents(); // Use buttonPanel here instead of getComponents()
        boolean hasYearMenu = false;
        boolean hasMonthYearMenu = false;
        boolean checkYearMenu = true;
        boolean checkMonthYearMenu = true;

        for (Component component : components) {
            if (component instanceof YearMenu) {
                YearMenu yearMenu = (YearMenu) component;
                checkYearMenu = yearMenu.validateYearRange(this.buttonPanel);
                hasYearMenu = true;
            } else if (component instanceof MonthYearMenu) {
                MonthYearMenu monthYearMenu = (MonthYearMenu) component;
                checkMonthYearMenu = monthYearMenu.validateEndDate(this.buttonPanel);
                hasMonthYearMenu = true;
                if (purposeComboBox.getSelectedItem().equals("Hot Topic") && stepComboBox.getSelectedItem().equals("Month")){
                    JOptionPane.showMessageDialog(this.buttonPanel, "This application do not support this feature! We would update soon!");
                    return false;
                }
            }
        }
        if (!hasYearMenu && !hasMonthYearMenu) {
            JOptionPane.showMessageDialog(this.buttonPanel, "Please fill in the information", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!checkYearMenu || !checkMonthYearMenu){
            return false;
        }
        else return true;
    }
    public List<String> getTrendKeyword(){
        String step = (String) stepComboBox.getSelectedItem();
        String purpose = (String) purposeComboBox.getSelectedItem();
        int startMonth = 1;
        int endMonth = 1;
        int startYear = 2018;
        int endYear = 2018;
        if (step.equals("Month")) {
            for (Component component : buttonPanel.getComponents()) {
                if (component instanceof MonthYearMenu) {
                    MonthYearMenu monthYearMenu = (MonthYearMenu) component;
                    startMonth = monthYearMenu.getStartMonthIndex();
                    endMonth = monthYearMenu.getEndMonthIndex();
                    startYear = monthYearMenu.getStartYear();
                    endYear = monthYearMenu.getEndYear();
                }
            }
        } else if (step.equals("Year")) {
            for (Component component : buttonPanel.getComponents()) {
                if (component instanceof YearMenu) {
                    YearMenu yearMenu = (YearMenu) component;
                    startYear = yearMenu.getStartYear();
                    endYear = yearMenu.getEndYear();
                }
            }
        }
        List<String> result = new ArrayList<>();
        result.add(step);
        result.add(purpose);
        result.add(String.valueOf(startYear));
        result.add(String.valueOf(endYear));
        result.add(String.valueOf(startMonth));
        result.add(String.valueOf(endMonth));

        System.out.println(result);
        return result;
    }
}

