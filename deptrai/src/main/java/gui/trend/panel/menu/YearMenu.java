package gui.trend.panel.menu;

import constant.function.UIFunction;

import javax.swing.*;
import java.awt.*;

public class YearMenu extends JPanel {
    private JComboBox<Integer> startYearComboBox;
    private JComboBox<Integer> endYearComboBox;

    public YearMenu() {
        Integer [] yearOptions = generateYears(2018, 7);
        setLayout(new GridLayout(1, 1));

        startYearComboBox = UIFunction.createComboBox(yearOptions);
        endYearComboBox = UIFunction.createComboBox(yearOptions);

        add(UIFunction.createPanel(startYearComboBox, "Start Year:"));
        add(UIFunction.createPanel(endYearComboBox, "End Year:"));
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
