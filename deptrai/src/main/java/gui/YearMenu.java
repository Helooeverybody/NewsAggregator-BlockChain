package gui;

import javax.swing.*;
import java.awt.*;

public class YearMenu extends JPanel {
    private JComboBox<Integer> startYearComboBox;
    private JComboBox<Integer> endYearComboBox;

    public YearMenu() {
        setLayout(new GridLayout(1, 1));

        startYearComboBox = createComboBox();
        endYearComboBox = createComboBox();

        add(createPanel(startYearComboBox, "Start Year:"));
        add(createPanel(endYearComboBox, "End Year:"));
    }

    private JComboBox<Integer> createComboBox() {
        JComboBox<Integer> comboBox = new JComboBox<>(generateYears(2018, 7));
        comboBox.setPreferredSize(new Dimension(60, 25)); // Set preferred size
        comboBox.setFont(new Font("Arial", Font.BOLD, 12)); // Set font
        comboBox.setForeground(Color.WHITE); // Set font color
        comboBox.setBackground(new Color(21, 192, 192)); // Set background color
        return comboBox;
    }

    private JPanel createPanel(JComboBox<Integer> comboBox, String labelText) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label);
        panel.add(comboBox);
        return panel;
    }

    private Integer[] generateYears(int startYear, int numYears) {
        Integer[] years = new Integer[numYears];
        for (int i = 0; i < numYears; i++) {
            years[i] = startYear + i;
        }
        return years;
    }

    public boolean validateYearRange(Component parentComponent) {
        int startYear = (int) startYearComboBox.getSelectedItem();
        int endYear = (int) endYearComboBox.getSelectedItem();

        if (startYear > endYear) {
            JOptionPane.showMessageDialog(parentComponent, "Start year cannot exceed end year.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public int getStartYear() {
        return (int) startYearComboBox.getSelectedItem();
    }

    public int getEndYear() {
        return (int) endYearComboBox.getSelectedItem();
    }
}
