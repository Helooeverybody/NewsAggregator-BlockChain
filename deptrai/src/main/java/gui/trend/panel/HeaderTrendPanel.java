package gui.trend.panel;

import gui.trend.panel.menu.MonthYearMenu;
import gui.trend.panel.menu.YearMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class HeaderTrendPanel extends JPanel {
    private JComboBox<String> stepComboBox;
    private JComboBox<String> purposeComboBox;
    private JButton getSelectionButton;
    private JPanel buttonPanel;

    public HeaderTrendPanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        JPanel titlePanel = createTitlePanel();
        add(titlePanel, BorderLayout.NORTH);

        stepComboBox = createComboBox(new String[]{"Month", "Year"}, "Step");
        purposeComboBox = createComboBox(new String[]{"Plot Trend", "Hot Topic"}, "Purpose");

        getSelectionButton = createButton("Get Result");

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(stepComboBox);
        buttonPanel.add(purposeComboBox);
        buttonPanel.add(getSelectionButton);
        add(buttonPanel, BorderLayout.CENTER);

        stepComboBox.addActionListener(e -> showStepMenu());
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(21, 192, 192));
        ImageIcon originalIcon = new ImageIcon("image/symbol_trend.png"); // Replace with the path to your image
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        titlePanel.add(imageLabel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Trend Analysis");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 38));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(titleLabel);

        return titlePanel;
    }

    private JComboBox<String> createComboBox(String[] options, String defaultOption) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(100, 25));
        comboBox.setFont(new Font("Arial", Font.BOLD, 12));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(new Color(21, 192, 192));
        comboBox.setFocusable(false);
        comboBox.setSelectedItem(defaultOption);
        return comboBox;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(21, 192, 192));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 25));
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    private void showStepMenu() {
        // Manually remove existing MonthYearMenu or YearMenu components
        Component[] components = buttonPanel.getComponents();
        for (Component component : components) {
            if (component instanceof MonthYearMenu || component instanceof YearMenu) {
                buttonPanel.remove(component);
            }
        }
        // Add new menu based on the selected step
        if (stepComboBox.getSelectedItem().equals("Month")) {
            buttonPanel.add(new MonthYearMenu());
        } else if (stepComboBox.getSelectedItem().equals("Year")) {
            buttonPanel.add(new YearMenu());
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public void setSelectionListener(ActionListener listener) {
        getSelectionButton.addActionListener(listener);
    }

    public boolean checkConditions(Component parent) {
        Component[] components = buttonPanel.getComponents();
        boolean hasYearMenu = false;
        boolean hasMonthYearMenu = false;
        boolean validYearMenu = true;
        boolean validMonthYearMenu = true;

        for (Component component : components) {
            if (component instanceof YearMenu) {
                YearMenu yearMenu = (YearMenu) component;
                validYearMenu = yearMenu.validateYearRange(parent);
                hasYearMenu = true;
            } else if (component instanceof MonthYearMenu) {
                MonthYearMenu monthYearMenu = (MonthYearMenu) component;
                validMonthYearMenu = monthYearMenu.validateEndDate(parent);
                hasMonthYearMenu = true;
                if ("Hot Topic".equals(purposeComboBox.getSelectedItem()) && "Month".equals(stepComboBox.getSelectedItem())) {
                    showUnsupportedFeatureMessage(parent);
                    return false;
                }
            }
        }

        if (!hasYearMenu && !hasMonthYearMenu) {
            showMessage(parent, "Please fill in the information", "Error");
            return false;
        }
        return validYearMenu && validMonthYearMenu;
    }

    private void showUnsupportedFeatureMessage(Component parent) {
        JOptionPane.showMessageDialog(parent, "This application does not support this feature! We will update soon!");
    }

    private void showMessage(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public List<String> getTrendKeyword() {
        String step = (String) stepComboBox.getSelectedItem();
        String purpose = (String) purposeComboBox.getSelectedItem();
        int startMonth = 1, endMonth = 1, startYear = 2018, endYear = 2018;

        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof MonthYearMenu) {
                MonthYearMenu menu = (MonthYearMenu) component;
                startMonth = menu.getStartMonthIndex();
                endMonth = menu.getEndMonthIndex();
                startYear = menu.getStartYear();
                endYear = menu.getEndYear();
            } else if (component instanceof YearMenu) {
                YearMenu menu = (YearMenu) component;
                startYear = menu.getStartYear();
                endYear = menu.getEndYear();
            }
        }
        List<String> result = new ArrayList<>();
        result.add(step);
        result.add(purpose);
        result.add(String.valueOf(startYear));
        result.add(String.valueOf(endYear));
        result.add(String.valueOf(startMonth));
        result.add(String.valueOf(endMonth));
        return result;
    }
}
