package gui;

import javax.swing.*;
import java.awt.*;

public class MonthYearMenu extends JPanel {
    private JComboBox<String> startMonthComboBox;
    private JComboBox<Integer> startYearComboBox;
    private JComboBox<String> endMonthComboBox;
    private JComboBox<Integer> endYearComboBox;

    public MonthYearMenu() {
        setLayout(new GridLayout(2, 2));

        startMonthComboBox = createComboBox(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}, 100);
        startYearComboBox = createComboBox(generateYears(2018, 7), 60);
        endMonthComboBox = createComboBox(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},100);
        endYearComboBox = createComboBox(generateYears(2018, 7),60);

        add(createPanel("Start Month:", startMonthComboBox, startYearComboBox));
        add(createPanel("End Month:", endMonthComboBox, endYearComboBox));
    }

    private JComboBox<String> createComboBox(String[] options, int width) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        customizeComboBox(comboBox,width);
        return comboBox;
    }

    private JComboBox<Integer> createComboBox(Integer[] options, int width) {
        JComboBox<Integer> comboBox = new JComboBox<>(options);
        customizeComboBox(comboBox,width);
        return comboBox;
    }

    private void customizeComboBox(JComboBox<?> comboBox, int width) {
        comboBox.setPreferredSize(new Dimension(width, 25));
        comboBox.setFont(new Font("Arial", Font.BOLD, 12));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(new Color(21, 192, 192));
    }

    private JPanel createPanel(String label, JComboBox<?> monthComboBox, JComboBox<?> yearComboBox) {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel jLabel = new JLabel(label);
        panel.add(jLabel);
        panel.add(monthComboBox);
        panel.add(yearComboBox);
        return panel;
    }

    private Integer[] generateYears(int startYear, int numYears) {
        Integer[] years = new Integer[numYears];
        for (int i = 0; i < numYears; i++) {
            years[i] = startYear + i;
        }
        return years;
    }

    public int getStartMonthIndex() {
        return startMonthComboBox.getSelectedIndex() + 1; // Months are zero-based, so add 1
    }
    public int getStartYear() {
        return startYearComboBox.getItemAt(startYearComboBox.getSelectedIndex());
    }

    public int getEndMonthIndex() {
        return endMonthComboBox.getSelectedIndex() + 1; // Months are zero-based, so add 1
    }

    public int getEndYear() {
        return endYearComboBox.getItemAt(endYearComboBox.getSelectedIndex());
    }

    public boolean validateEndDate(Component componentParent) {
        int startYear = getStartYear();
        int startMonth = getStartMonthIndex();
        int endYear = getEndYear();
        int endMonth = getEndMonthIndex();

        if (endYear < startYear || (endYear == startYear && endMonth < startMonth)) {
            JOptionPane.showMessageDialog(componentParent, "End date must be later than or equal to the start date.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (endYear == 2024 && endMonth > 4) {
            JOptionPane.showMessageDialog(componentParent, "End date cannot exceed April 2024.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
}
