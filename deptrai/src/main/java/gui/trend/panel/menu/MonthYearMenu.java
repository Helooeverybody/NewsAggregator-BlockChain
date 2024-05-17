package gui.trend.panel.menu;

import constant.function.UIFunction;

import javax.swing.*;
import java.awt.*;

public class MonthYearMenu extends JPanel {
    private JComboBox<String> startMonthComboBox;
    private JComboBox<Integer> startYearComboBox;
    private JComboBox<String> endMonthComboBox;
    private JComboBox<Integer> endYearComboBox;

    public MonthYearMenu() {
        setLayout(new GridLayout(2, 2));

        startMonthComboBox = UIFunction.createComboBox(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}, 100);
        startYearComboBox = UIFunction.createComboBox(generateYears(2018, 7), 60);
        endMonthComboBox = UIFunction.createComboBox(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},100);
        endYearComboBox = UIFunction.createComboBox(generateYears(2018, 7),60);

        add(UIFunction.createPanel("Start Month:", startMonthComboBox, startYearComboBox));
        add(UIFunction.createPanel("End Month:", endMonthComboBox, endYearComboBox));
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
